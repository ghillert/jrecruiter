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
import java.util.List;

import org.jrecruiter.common.CollectionUtils;

/**
 * Form for the Logging Action.
 *
 * @author Gunnar Hillert
 *
 */
public class LoggingForm implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 5970927715241338665L;

	/** List of Loggers, simplified for display purposes */
	private List<LoggerInfo> updatedLoggers = CollectionUtils.getArrayList();

	/** The user can enter a new logger to be configured */
	private LoggerInfo newLogger;

	/** Used for requesting a logfile download */
	private String fileName;

	//~~~~~Constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 *
	 */
	public LoggingForm() {
		super();
	}


	//~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


	public List<LoggerInfo> getUpdatedLoggers() {
		return updatedLoggers;
	}

	public void setUpdatedLoggers(List<LoggerInfo> updatedLoggers) {
		this.updatedLoggers = updatedLoggers;
	}

	public LoggerInfo getNewLogger() {
		return newLogger;
	}

	public void setNewLogger(LoggerInfo newLogger) {
		this.newLogger = newLogger;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
