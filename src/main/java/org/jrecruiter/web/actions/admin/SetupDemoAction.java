package org.jrecruiter.web.actions.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.service.DemoService;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.interceptor.StoreMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.texturemedia.smarturls.Result;

import com.opensymphony.xwork2.validator.annotations.Validation;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */

@Validation
@Result(name="success", location="index", type="redirectAction")
public class SetupDemoAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;
    private final Log LOGGER = LogFactory.getLog(SetupDemoAction.class);

    private @Autowired DemoService demoService;

    @StoreMessages
    public String execute() {

        demoService.createDemoJobs(300);

        addActionMessage("300 demo jobs have been created.");
        return SUCCESS;
    }

}
