package org.jrecruiter.web.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.config.Result;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Result(name=Action.SUCCESS,
		  value="/WEB-INF/jsp/user/addUser.jsp")
public class SignupAction extends ActionSupport {

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;
	private final Log log = LogFactory.getLog(SignupAction.class);

    public String list() {
        return SUCCESS;
    }

    public String execute() {
        return SUCCESS;
    }
}
