package org.jrecruiter.web.actions.util;

import java.io.File;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.web.actions.admin.LogFileInfo;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;

/**
 * 
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class LoggingUtil {

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

		public static LogLevels getLogLevelFromId(int logLevelAsInt) {

			for (LogLevels logLevel : LogLevels.values()) {

				if (logLevelAsInt == logLevel.logLevel) {
					return logLevel;
				}
			}

			throw new IllegalStateException("Loglevel " + logLevelAsInt + " does not exist.");

		}

	}

	/**
	 *
	 * @return
	 */
	public static List<ch.qos.logback.classic.Logger> findNamesOfConfiguredAppenders(boolean showAll) {

		final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		final List<ch.qos.logback.classic.Logger> configuredLoggers = CollectionUtils.getArrayList();

		for (ch.qos.logback.classic.Logger log : lc.getLoggerList()) {
			if(showAll == false) {
				if(log.getLevel() != null || LoggingUtil.hasAppenders(log)) {
					configuredLoggers.add(log);
				}
			} else {
				configuredLoggers.add(log);
			}
		}

		return configuredLoggers;
	}

	/**
	 *
	 * @return
	 */
	public static ch.qos.logback.classic.Logger getLogger(final String loggerName) {

		final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

		return lc.getLogger(loggerName);
		
	}
	
	public static boolean hasAppenders(ch.qos.logback.classic.Logger logger) {
		Iterator<Appender<LoggingEvent>> it = logger.iteratorForAppenders();
		return it.hasNext();
	}

	public static List<LogFileInfo> getLogFileInfos() {
		final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

		final List<LogFileInfo> logFileInfos = CollectionUtils.getArrayList();

		final Logger logger = lc.getLogger(Logger.ROOT_LOGGER_NAME);

		final Iterator<Appender<LoggingEvent>> it = logger.iteratorForAppenders();

		while (it.hasNext()) {

			final Appender<LoggingEvent> appender = it.next();

			if (appender instanceof FileAppender) {

				final FileAppender<LoggingEvent> fileAppender = (FileAppender<LoggingEvent>) appender;

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

	public static File getLogFile(final String logFileName) {

		if (logFileName == null) {
			throw new IllegalArgumentException("logFileName cannot be null.");
		}

		final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

		final Logger logger = lc.getLogger(Logger.ROOT_LOGGER_NAME);

		final Iterator<Appender<LoggingEvent>> it = logger.iteratorForAppenders();

		while (it.hasNext()) {

			final Appender<LoggingEvent> appender = it.next();

			if (appender instanceof FileAppender) {

				final FileAppender<LoggingEvent> fileAppender = (FileAppender<LoggingEvent>) appender;

				final File logFile = new File(fileAppender.getFile());

				if (logFile.getName().equalsIgnoreCase(logFileName)) {
					return logFile;
				}

			}

		}

		return null;
	}

}
