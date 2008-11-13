package org.jrecruiter.web.actions.admin;

import java.util.Date;

/**
 * Log file information.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class LogFileInfo {

    private String fileName;
    private Long fileSize;
    private Date fileLastChanged;
    
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public Date getFileLastChanged() {
		return fileLastChanged;
	}
	public void setFileLastChanged(Date fileLastChanged) {
		this.fileLastChanged = fileLastChanged;
	}

}
