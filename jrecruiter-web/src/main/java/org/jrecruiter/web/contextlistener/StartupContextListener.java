/**
 *
 */
package org.jrecruiter.web.contextlistener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.jrecruiter.common.Apphome;
import org.jrecruiter.common.Constants;
import org.jrecruiter.common.SystemInformationUtils;
import org.jrecruiter.common.Constants.SpringContextMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Prepare the logging infrastructure and select what constitutes the JRecruiter
 * Home directory.
 *
 * The JRECRUITER_HOME directory is either defined by an environment variable or
 * a system property. The main issue is that the logging infrastructure (in this
 * case logback) does not know about environment variables.
 *
 * Lockback (similar also Log4j) can only define a dynamic logging path/location
 * using a system property.
 *
 * An additional benefit of this context listener is that it give you a dynamic
 * choice of where the your home directory.
 *
 * First, it looks whether a JRECRUITER_HOME system property exists, and uses it
 * in case it is present. Otherwise it will look whether an environment variable
 * JRECRUITER_HOME exist, and uses it if present.
 *
 * If neither system property nor environment variable exist, an {@link IllegalStateException}
 * is thrown telling you to setup one or the other.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class StartupContextListener implements ServletContextListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(StartupContextListener.class);

    /**
     * Default constructor.
     */
    public StartupContextListener() {
        super();
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {

    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        final ServletContext servletContext = servletContextEvent.getServletContext();

        final String         contextPath      = servletContext.getContextPath();
        final String         server           = servletContext.getServerInfo();

        final Apphome apphome = SystemInformationUtils.retrieveBasicSystemInformation();

        final String jrecruiterHomeMessage;
        SpringContextMode springContextMode = null;

        switch (apphome.getAppHomeSource()) {

            case SYSTEM_PROPERTY:
                jrecruiterHomeMessage = "System Property '" + Constants.APP_HOME_DIRECTORY
                                      + "' found: " + apphome.getAppHomePath();
                break;

            case ENVIRONMENT_VARIABLE:
                System.setProperty(Constants.APP_HOME_DIRECTORY, apphome.getAppHomePath());

                jrecruiterHomeMessage = "Environment Variable '" + Constants.APP_HOME_DIRECTORY
                                      + "' found: " + apphome.getAppHomePath()
                                      + ". Using it to set system property.";
                break;

            case USER_DIRECTORY:

                jrecruiterHomeMessage = "'" + Constants.APP_HOME_DIRECTORY
                                      + "' not found. Please set '" + Constants.APP_HOME_DIRECTORY
                                      + "' as a system property or as an environment variable. DEMO Mode, using embedded database.";
                break;
            default: throw new IllegalStateException("Was expecting to resolve a home directory.");

        }

        if (SystemInformationUtils.existsConfigFile(apphome.getAppHomePath())) {
            springContextMode = SpringContextMode.ProductionContextConfiguration;
        } else {
            springContextMode = SpringContextMode.DemoContextConfiguration;
        }

        //FIXME: Do the context mode resolution

        System.setProperty("jRecruiterSpringContext", springContextMode.getCode());
        final StringBuilder bootMessage = new StringBuilder();

        bootMessage.append("\n");
        bootMessage.append(jrecruiterHomeMessage);
        bootMessage.append("\n");
        bootMessage.append("Using Spring Context: " + springContextMode);
        bootMessage.append("\n");
        bootMessage.append("Booting jRecruiter...                          ").append("\n");
        bootMessage.append("-----------------------------------------------").append("\n");

        final String contextPathLabel = StringUtils.rightPad("Context Path", 40, '.');
        final String serverLabel = StringUtils.rightPad("Server", 40, '.');

        bootMessage.append(contextPathLabel + ": " + contextPath)     .append("\n");
        bootMessage.append(serverLabel      + ": " + server)          .append("\n");

        bootMessage.append(SystemInformationUtils.getAllSystemProperties());
        bootMessage.append("-----------------------------------------------").append("\n");

        LOGGER.info(bootMessage.toString());
        System.getProperty("jRecruiterSpringContext");
    }

}
