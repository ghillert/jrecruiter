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
package org.jrecruiter.service.notification.impl;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
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

import com.rosaloves.bitlyj.Url;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
@Service("notificationService")
@Transactional
public class DefaultNotificationServiceImpl implements NotificationService {

    /**
     * Mailsender.
     */
    private @Autowired  JavaMailSender  mailSender;

    private @Autowired Configuration freemarkerConfiguration;

    /**
     * Holder object for access information to external web services such as Twitter.
     */
    private @Autowired ApiKeysHolder apiKeysHolder;

    /** {@inheritDoc} */
    @Override
    public void sendEmail(final String email, final String subject, final Map context, final String templateName) {

        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException {

                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("no_reply@jrecruiter.org");
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

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Override
    public URI shortenUrl(final String urlAsString) {

    	//FIXME Handle this better
        Url url = as(apiKeysHolder.getBitlyUsername(),
        		     apiKeysHolder.getBitlyPassword())
        		 .call(shorten(urlAsString));
        try {
			return new URI(url.getShortUrl());
		} catch (URISyntaxException e) {
    		throw new IllegalArgumentException(
    	    		String.format("Please provide a valid URI - %s is not valid", urlAsString));
		}

    }

}
