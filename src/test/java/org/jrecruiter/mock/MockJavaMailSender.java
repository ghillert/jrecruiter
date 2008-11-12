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
package org.jrecruiter.mock;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class MockJavaMailSender extends JavaMailSenderImpl {

    public MockJavaMailSender() {
        super();
    }

    /**
     * Logger for this class.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(MockJavaMailSender.class);

    private MimeMessage message;

    @Override
    public void send(MimeMessagePreparator preparator) throws MailException {

        message = new MimeMessage((Session) null);
        try {
            preparator.prepare(message);
        } catch (Exception e) {
            throw new IllegalStateException("Error while preparing message.", e);
        }

        LOGGER.info("send() - Mock message successfully sent.");

    }

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        LOGGER.info("Sending...");
    }

    @Override
    public void send(MimeMessage msg) throws MailException {
        message = msg;
    }

}
