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
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jrecruiter.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Gunnar Hillert
 * @version $Id: DataServiceImpl.java 320 2009-02-16 12:17:45Z ghillert $
 */
@Service("notificationService")
@Transactional
public class NotificationServiceImpl implements NotificationService {

    /**
     * Logger Declaration.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

    /**
     * Mailsender.
     */
    private @Autowired  JavaMailSender  mailSender;

    private @Autowired Configuration freemarkerConfiguration;

 //TODO
    public void sendEmail(final String email, final Map context, final String templateName) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException {

                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("jobs@jrecruiter.org");
                message.setTo("jobs@jrecruiter.org");
                message.setSubject("Jobs");

                Locale locale = LocaleContextHolder.getLocale();

                Template textTemplate = freemarkerConfiguration.getTemplate(templateName + "-text.ftl", locale);

                final StringWriter textWriter = new StringWriter();
                try {
                    textTemplate.process(context, textWriter);
                } catch (TemplateException e) {
                    throw new MailPreparationException("Can't generate email body.", e);
                }

                Template htmlTemplate = freemarkerConfiguration.getTemplate(templateName + "-html.ftl", locale);

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

}
