package org.jrecruiter.web.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.config.Result;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@Result(name=Action.SUCCESS,
		  value="/WEB-INF/jsp/welcome.jsp")
public class GetPasswordAction extends ActionSupport {
    private final Log log = LogFactory.getLog(GetPasswordAction.class);

    public String list() { 
        return SUCCESS; 
    } 
}
