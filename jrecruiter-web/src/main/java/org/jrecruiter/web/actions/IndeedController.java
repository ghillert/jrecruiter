package org.jrecruiter.web.actions;

import org.jrecruiter.model.ServerSettings;
import org.jrecruiter.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller
public class IndeedController {

    private @Autowired JobService jobService;
    private @Autowired ServerSettings serverSettings;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    @RequestMapping("/indeed.xml")
    public String execute(ModelMap model) {

        model.addAttribute("jobs", jobService.getJobs());
        model.addAttribute("serverAddress", serverSettings.getServerAddress());

        return "indeedView";
    }

}
