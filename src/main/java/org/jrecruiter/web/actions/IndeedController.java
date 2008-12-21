package org.jrecruiter.web.actions;

import org.jrecruiter.model.User;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.interceptor.StoreMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.texturemedia.smarturls.Result;

/**
 * Resets the users passwords and emails it back to the user.
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller
public class IndeedController {

    private @Autowired JobService jobService;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    private final static Logger LOGGER = LoggerFactory.getLogger(IndeedController.class);

    @RequestMapping("/indeed.spring")
    public void execute() {
        
    	//return new ModelAndView()
    }

}
