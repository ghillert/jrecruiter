/*
*	http://www.jrecruiter.org
*
*	Disclaimer of Warranty.
*
*	Unless required by applicable law or agreed to in writing, Licensor provides
*	the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
*	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
*	including, without limitation, any warranties or conditions of TITLE,
*	NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
*	solely responsible for determining the appropriateness of using or
*	redistributing the Work and assume any risks associated with Your exercise of
*	permissions under this License.
*
*/
package org.jrecruiter.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jrecruiter.common.ApiKeysHolder;
import org.jrecruiter.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.http.HttpClient;

import com.rosaloves.net.shorturl.bitly.Bitly;
import com.rosaloves.net.shorturl.bitly.BitlyFactory;
import com.rosaloves.net.shorturl.bitly.url.BitlyUrl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
@Service("notificationService")
@Transactional
public class NotificationServiceImpl implements NotificationService {

    /**
     * Mailsender.
     */
    private @Autowired  JavaMailSender  mailSender;

    private @Autowired Configuration freemarkerConfiguration;

    /**
     * Holder object for access information to external web services such as Twitter.
     */
    private @Autowired ApiKeysHolder apiKeysHolder;

    /**
     *
     */
    public void sendEmail(final String email, final String subject, final Map context, final String templateName) {

        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException {

                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("jobs@jrecruiter.org");
                message.setTo(email);
                message.setSubject(subject);

                final Locale locale = LocaleContextHolder.getLocale();

                final Template textTemplate = freemarkerConfiguration.getTemplate(templateName + "-text.ftl", locale);

                final StringWriter textWriter = new StringWriter();
                try {
                    textTemplate.process(context, textWriter);
                } catch (TemplateException e) {
                    throw new MailPreparationException("Can't generate email body.", e);
                }

                final Template htmlTemplate = freemarkerConfiguration.getTemplate(templateName + "-html.ftl", locale);

                final StringWriter htmlWriter = new StringWriter();
                try {
                    htmlTemplate.process(context, htmlWriter);
                } catch (TemplateException e) {
                    throw new MailPreparationException("Can't generate email body.", e);
                }

                message.setText(textWriter.toString(), htmlWriter.toString());

            }
        };

        mailSender.send(preparator);
    }

    @Override
    public void sendTweetToTwitter(final String tweet) {

        final Twitter twitter = new Twitter(apiKeysHolder.getTwitterUsername(),
                                            apiKeysHolder.getTwitterPassword());
        try {
            twitter.updateStatus(tweet);
        } catch (TwitterException e) {
            throw new IllegalStateException(e);
        }


    }

    /* (non-Javadoc)
     * @see org.jrecruiter.service.NotificationService#shortenUrl(java.lang.String)
     */
    @Override
    public URL shortenUrl(final String string) {

        final Bitly bitly = BitlyFactory.newInstance(apiKeysHolder.getBitlyUsername(),
                                               apiKeysHolder.getBitlyPassword());

        try {
            final BitlyUrl bUrl = bitly.shorten(string);
            return bUrl.getShortUrl();
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
