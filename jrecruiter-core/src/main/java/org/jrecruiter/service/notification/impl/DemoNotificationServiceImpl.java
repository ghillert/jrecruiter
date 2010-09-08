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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.jrecruiter.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gunnar Hillert
 * @version $Id: NotificationServiceImpl.java 605 2010-08-31 05:31:30Z ghillert $
 */
@Service("notificationService")
@Transactional
public class DemoNotificationServiceImpl implements NotificationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoNotificationServiceImpl.class);

    /** {@inheritDoc} */
    @Override
    public void sendEmail(final String email, final String subject, final Map context, final String templateName) {

    	if (subject == null) {
    		throw new IllegalArgumentException("Parameter 'tweet' must NOT be null.");
    	}

    	LOGGER.warn("Running in DEMO Mode - Email with subject '{}' will NOT be send.", subject);

    }

    /** {@inheritDoc} */
    @Override
    public void sendTweetToTwitter(final String tweet) {

    	if (tweet == null) {
    		throw new IllegalArgumentException("Parameter 'tweet' must NOT be null.");
    	}

    	LOGGER.warn("Running in DEMO Mode - Tweet '{}' will NOT be tweeted.", tweet);

    }

    /** {@inheritDoc} */
    @Override
    public URI shortenUrl(final String urlAsString) {

    	if (urlAsString == null) {
    		throw new IllegalArgumentException("Parameter 'urlAsString' must NOT be null.");
    	}

    	LOGGER.warn("Running in DEMO Mode - Url {} will NOT be shortened.", urlAsString);

    	final URI uri;

    	try {
            uri = new URI(urlAsString);
    	} catch (URISyntaxException e) {
    		throw new IllegalArgumentException(
    		String.format("Please provide a valid URI - %s is not valid", urlAsString));
    	}

        return uri;

    }

}
