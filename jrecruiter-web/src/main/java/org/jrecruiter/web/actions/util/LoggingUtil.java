package org.jrecruiter.web.actions.util;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.web.actions.admin.LogFileInfo;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;

/**
 * Contains utility methods to interact with the logback during runtime.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class LoggingUtil {

    /**
     * re-defines the logback logging levels as a Java enumeration. This is quite
     * helpful if you need to render the various log-levels as select-box. I wish
     * logback @see {@link ch.qos.logback.classic.Level} would not use static variables
     * but use enums instead.
     *
     * @author Gunnar Hillert
     */
    public enum LogLevels {

        OFF(Integer.MAX_VALUE, "Off"),
        ERROR_INT(40000, "Error"),
        WARN_INT(30000, "Warn"),
        INFO_INT(20000, "Info"),
        DEBUG_INT(10000, "Debug"),
        TRACE(5000, "Trace"),
        ALL(Integer.MIN_VALUE, "All");

        private int logLevel;
        private String logLevelName;

        LogLevels(final int logLevel, final String logLevelName) {
            this.logLevel = logLevel;
            this.logLevelName = logLevelName;
        }

        public int getLogLevel() {
            return this.logLevel;
        }

        public String getLogLevelName() {
            return this.logLevelName;
        }

        public static LogLevels getLogLevelFromId(final int logLevelAsInt) {

            for (LogLevels logLevel : LogLevels.values()) {

                if (logLevelAsInt == logLevel.logLevel) {
                    return logLevel;
                }
            }

            throw new IllegalStateException("Loglevel " + logLevelAsInt + " does not exist.");

        }

    }

    /**
     * Retrieve all configured logback loggers.
     *
     * @param showAll If set return ALL loggers, not only the configured ones.
     * @return List of Loggers
     */
    public static List<ch.qos.logback.classic.Logger> getLoggers(final boolean showAll) {

        final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        final List<ch.qos.logback.classic.Logger> loggers = CollectionUtils.getArrayList();

        for (ch.qos.logback.classic.Logger log : lc.getLoggerList()) {
            if(showAll == false) {
                if(log.getLevel() != null || LoggingUtil.hasAppenders(log)) {
                    loggers.add(log);
                }
            } else {
                loggers.add(log);
            }
        }

        return loggers;
    }

    /**
     * Get a single logger.
     *
     * @return Logger
     */
    public static ch.qos.logback.classic.Logger getLogger(final String loggerName) {

        final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        return lc.getLogger(loggerName);

    }

    /**
     * Test whether the provided logger has appenders.
     *
     * @param logger The logger to test
     * @return true if the logger has appenders.
     */
    public static boolean hasAppenders(ch.qos.logback.classic.Logger logger) {
        Iterator<Appender<ILoggingEvent>> it = logger.iteratorForAppenders();
        return it.hasNext();
    }

    /**
     * Get the logfile information for the roor logger.
     *
     * @return List of LogFileInfo obejcts
     */
    public static List<LogFileInfo> getLogFileInfos() {

        final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        final List<LogFileInfo> logFileInfos = CollectionUtils.getArrayList();

        final Logger logger = lc.getLogger(Logger.ROOT_LOGGER_NAME);

        final Iterator<Appender<ILoggingEvent>> it = logger.iteratorForAppenders();

        while (it.hasNext()) {

            final Appender<ILoggingEvent> appender = it.next();

            if (appender instanceof FileAppender) {

                final FileAppender<ILoggingEvent> fileAppender = (FileAppender<ILoggingEvent>) appender;

                final File logFile = new File(fileAppender.getFile());
                final LogFileInfo logFileInfo = new LogFileInfo();

                logFileInfo.setFileName(logFile.getName());
                logFileInfo.setFileLastChanged(new Date(logFile.lastModified()));
                logFileInfo.setFileSize(logFile.length());
                logFileInfos.add(logFileInfo);
            }

        }

        return logFileInfos;
    }

    /**
     * Get the log file.
     *
     * @param logFileName The name of the log file
     * @return The actual file
     */
    public static File getLogFile(final String logFileName) {

        if (logFileName == null) {
            throw new IllegalArgumentException("logFileName cannot be null.");
        }

        final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        final Logger logger = lc.getLogger(Logger.ROOT_LOGGER_NAME);

        final Iterator<Appender<ILoggingEvent>> it = logger.iteratorForAppenders();

        while (it.hasNext()) {

            final Appender<ILoggingEvent> appender = it.next();

            if (appender instanceof FileAppender) {

                final FileAppender<ILoggingEvent> fileAppender = (FileAppender<ILoggingEvent>) appender;

                final File logFile = new File(fileAppender.getFile());

                if (logFile.getName().equalsIgnoreCase(logFileName)) {
                    return logFile;
                }

            }

        }

        return null;
    }

}
