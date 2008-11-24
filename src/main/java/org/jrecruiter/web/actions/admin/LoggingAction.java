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
package org.jrecruiter.web.actions.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.actions.util.LoggingUtil;
import org.jrecruiter.web.actions.util.LoggingUtil.LogLevels;
import org.texturemedia.smarturls.ActionName;
import org.texturemedia.smarturls.ActionNames;
import org.texturemedia.smarturls.Result;
import org.texturemedia.smarturls.Results;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * Show log files and allow to set log levels for the configured loggers (logback)
 * to be changed dynamically at runtime.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@ActionNames({
	@ActionName(name="logging-ajax-log-file-table",    method="executeAjaxLoggingLogFileTable"),
	@ActionName(name="logging-ajax-logger-table",      method="executeAjaxLoggerTable"),
    @ActionName(name="logging",         method="execute"),
    @ActionName(name="downloadLogFile", method="download")
})
@Results({
    @Result(name="ajaxLoggingLogFileTable", location="/WEB-INF/jsp/admin/loggingLogFileTable.jsp"),
    @Result(name="ajaxLoggerTable",         location="/WEB-INF/jsp/admin/loggingLoggerTable.jsp"),
    @Result(name="success",                 location="/WEB-INF/jsp/admin/logging.jsp"),
    @Result(name="successRedirect",         location="logging", type="redirectAction"),
    @Result(name="download",                location="fileToDownLoad", type="stream",
              params={"contentType","text/plain",
                      "inputName","fileToDownLoad",
                      "contentDisposition","attachment; filename=logfile.txt",
                      "bufferSize", "1024"})
    })
public class LoggingAction extends BaseAction implements Preparable, ModelDriven<LoggingForm> {

    /** serialVersionUID. */
	private static final long serialVersionUID = -8382245048461549536L;
	
	/** Show all loggers or only the configured loggers */
    private boolean showAll = false;
    
    /** List of log files and associated information */
    private List<LogFileInfo> logFileInfos = CollectionUtils.getArrayList();
    
    /** List of Loggers, simplified for display purposes */
    private List<LoggerInfo> configuredLoggers = CollectionUtils.getArrayList();
    
    /** Used to stream a logfile back to the client */
    private transient InputStream fileToDownLoad;
    
    private LoggingForm model = new LoggingForm();
    
    //~~~~~Reference Data~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Defines a collection of log levels. Unfortunately logback does not use 
     *  enums to define its log levels. */
    private Set<LogLevels> logLevels = EnumSet.allOf(LogLevels.class); 
    
    //~~~~~Prepare data~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    @Override
	public void prepare() {
	}
	
	public void prepareExecute() {
		loadLoggers();
		loadLogFiles();
	}
    
	public void prepareUpdateLagLevels() {
		loadLoggers();
		loadLogFiles();	
	}
	
	public void prepareAddNewLogger() {
		loadLoggers();
		loadLogFiles();
	}
	
	//~~~~~Helper Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private void loadLoggers() {
		this.configuredLoggers.clear();
		
    	final List<Logger> loggers = LoggingUtil.findNamesOfConfiguredAppenders(this.showAll);
    	
    	for (Logger logger : loggers) {
    		
    		this.configuredLoggers.add(new LoggerInfo(logger.getName(), logger.getEffectiveLevel().levelInt));
    	}
	}
	
	private void loadLogFiles() {
		this.logFileInfos = LoggingUtil.getLogFileInfos();
	}
	
	//~~~~~Ajax Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
    /** Reload the data for table that displays log file data. */
    public String executeAjaxLoggingLogFileTable() {
        loadLogFiles();
        return "ajaxLoggingLogFileTable";
    }

    /** Reload the data for table that displays loggers. */
	public String executeAjaxLoggerTable() {
		loadLoggers();        
        return "ajaxLoggerTable";
    }
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** Loads the page with default parameters. */
	@Override
	public String execute() {
		return SUCCESS;
	}
	
	/** Updates loglevels for loggers */
    public String updateLagLevels() {

        if (this.model.getUpdatedLoggers() != null && !this.model.getUpdatedLoggers().isEmpty()) {
        	
        	for (LoggerInfo loggerInfo : this.model.getUpdatedLoggers()) {
        		
        		if (loggerInfo != null && loggerInfo.getNewLogLevel() != null) {
        			
        			LoggingUtil.getLogger(loggerInfo.getLoggerName())
        			           .setLevel(Level.toLevel(loggerInfo.getNewLogLevel().getLogLevel()));
        			
        		}
        	}
        	
        	//Need to refresh the loggers
        	loadLoggers();
        }
        
        
        return "successRedirect";
        
    }
	
    /** Adds a new logger at runtime.  */
	public String addNewLogger() {
	
        if (this.model.getNewLogger() != null 
        		&& this.model.getNewLogger().getLoggerName() != null 
        		&& this.model.getNewLogger().getNewLogLevel() != null) {
        	final Logger newLogger = LoggingUtil.getLogger(this.model.getNewLogger().getLoggerName());
        	newLogger.setLevel(Level.toLevel(this.model.getNewLogger().getNewLogLevel().getLogLevel()));  
            
        	//Need to refresh the loggers
        	loadLoggers();
        }
		
		return "successRedirect";
	}
	
    /**
     * Retrieve the requested log file.
     * 
     * @return
     * @throws Exception
     */
    public String download() throws Exception {

        if (this.model.getFileName() == null) {
            throw new IllegalArgumentException("FileName must not be null.");
        }
        
        final File logFile = LoggingUtil.getLogFile(this.model.getFileName());
        
        if (logFile != null) {
        	this.fileToDownLoad = new FileInputStream(logFile);
        }

        return "download";
    }

    //~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
	public List<LogFileInfo> getLogFileInfos() {
        return logFileInfos;
    }

    public InputStream getFileToDownLoad() {
        return fileToDownLoad;
    }

    public List<LoggerInfo> getConfiguredLoggers() {
		return configuredLoggers;
	}

	public boolean isShowAll() {
        return showAll;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }
    
	public Set<LogLevels> getLogLevels() {
		return logLevels;
	}

	public LoggingForm getModel() {
		return model;
	}

	public void setModel(LoggingForm model) {
		this.model = model;
	}

}
