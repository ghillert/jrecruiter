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
package org.jrecruiter.spring;

import java.io.Serializable;

import org.jrecruiter.common.Apphome;
import org.jrecruiter.common.SystemInformationUtils;
import org.jrecruiter.common.Constants.SpringContextMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents central jRecruiter configuration settings that can be
 * used by other parts of the application either at startup and or runtime.
 *
 * This classes data is retrieved at application startup and is not stored in the
 * database.
 *
 * @author Gunnar Hillert
 * @version $Id: Configuration.java 422 2009-07-05 04:14:27Z ghillert $
 */
public class ApplicationConfiguration implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 2371390826931311420L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    // Fields
    private SpringContextMode springContextMode;
    private Apphome applicationHome;

    // Constructors

    /** default constructor */
    public ApplicationConfiguration() {

        final Apphome apphome = SystemInformationUtils.retrieveBasicSystemInformation();

        if (apphome == null) {
            throw new IllegalStateException("apphome is null");
        }

        final SpringContextMode springContextMode  = SystemInformationUtils.getSpringContextMode(apphome.getAppHomePath());

        this.applicationHome   = apphome;
        this.springContextMode = springContextMode;

        LOGGER.info("AppHomePath: " + apphome.getAppHomePath());
        LOGGER.info("SpringContextMode: " + springContextMode.name());
    }

    //~~~~~Getters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return the springContextMode
     */
    public SpringContextMode getSpringContextMode() {
        return springContextMode;
    }

    /**
     * @return the applicationHome
     */
    public Apphome getApplicationHome() {
        return applicationHome;
    }

}

