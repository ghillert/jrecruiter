/*
 * Copyright 2006-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrecruiter.web.actions.admin;

import java.io.Serializable;

import org.jrecruiter.web.actions.util.LoggingUtil.LogLevels;

/**
 * Logger information.
 *
 * @author Gunnar Hillert
 *
 */
public class LoggerInfo implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 5542917607467653570L;

	private String    loggerName;
	private LogLevels logLevel;
	private LogLevels newLogLevel;

	//~~~~~Constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 *
	 */
	public LoggerInfo() {
		super();
	}
	/**
	 * @param loggerName
	 * @param logLevel
	 */
	public LoggerInfo(String loggerName, int logLevelAsInt) {
		super();
		this.loggerName = loggerName;
		this.logLevel = LogLevels.getLogLevelFromId(logLevelAsInt);
	}

	//~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public String getLoggerName() {
		return loggerName;
	}
	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}
	public LogLevels getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(LogLevels logLevel) {
		this.logLevel = logLevel;
	}
	public LogLevels getNewLogLevel() {
		return newLogLevel;
	}
	public void setNewLogLevel(LogLevels newLogLevel) {
		this.newLogLevel = newLogLevel;
	}

}
