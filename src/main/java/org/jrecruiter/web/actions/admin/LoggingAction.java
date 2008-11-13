package org.jrecruiter.web.actions.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.actions.util.LoggingUtil;
import org.texturemedia.smarturls.ActionName;
import org.texturemedia.smarturls.ActionNames;
import org.texturemedia.smarturls.Result;
import org.texturemedia.smarturls.Results;

/**
 * Show log files and allow to set log levels for the configured loggers (logback)
 * to be changed dynamically at runtime.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@ActionNames({
      @ActionName(name="logging", method="execute"),
      @ActionName(name="downloadLogFile", method="download")
    })
@Results({
      @Result(name="download", location="fileToDownLoad", type="stream",
              params={"contentType","text/plain",
                      "inputName","fileToDownLoad",
                      "contentDisposition","attachment; filename=logfile.txt",
                      "bufferSize", "1024"})
    })
public class LoggingAction extends BaseAction {

    /** serialVersionUID. */
	private static final long serialVersionUID = -8382245048461549536L;
	
	private String log;
    private Integer level;
    private boolean showAll = false;

    private List<LogFileInfo> logFileInfos = CollectionUtils.getArrayList();
    private List<ch.qos.logback.classic.Logger> configuredLoggers = CollectionUtils.getArrayList();

    private String fileName;
    private InputStream fileToDownLoad;
    
    /**
     *
     */
    @Override
    public String execute() {

        this.configuredLoggers = LoggingUtil.findNamesOfConfiguredAppenders(this.showAll);
        this.logFileInfos = LoggingUtil.getLogFileInfos();

        return SUCCESS;
    }

    /**
     * Retrieve the requested log file.
     * 
     * @return
     * @throws Exception
     */
    public String download() throws Exception {

        if (this.fileName == null) {
            throw new IllegalArgumentException("FileName must not be null.");
        }
        
        final File logFile = LoggingUtil.getLogFile(fileName);
        
        if (logFile != null) {
        	this.fileToDownLoad = new FileInputStream(logFile);
        }

        return "download";
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<LogFileInfo> getLogFileInfos() {
        return logFileInfos;
    }

    public void setLogFileInfos(List<LogFileInfo> logFileInfos) {
        this.logFileInfos = logFileInfos;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getFileToDownLoad() {
        return fileToDownLoad;
    }

    public void setFileToDownLoad(InputStream fileToDownLoad) {
        this.fileToDownLoad = fileToDownLoad;
    }

    public List<ch.qos.logback.classic.Logger> getConfiguredLoggers() {
        return configuredLoggers;
    }

    public void setConfiguredLoggers(
            List<ch.qos.logback.classic.Logger> configuredLoggers) {
        this.configuredLoggers = configuredLoggers;
    }

    public boolean isShowAll() {
        return showAll;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

}
