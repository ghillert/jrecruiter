package org.jrecruiter.web.actions;

import org.jrecruiter.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller
public class JobDetailController {

    private @Autowired JobService jobService;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    @RequestMapping("/{id}/jobDetail.pdf")
    public String execute(@PathVariable Long id, ModelMap model) {

        model.addAttribute("job", jobService.getJobForId(id));
        return "jobDetailPdfView";
    }

}
