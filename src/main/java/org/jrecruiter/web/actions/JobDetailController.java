package org.jrecruiter.web.actions;

import org.jrecruiter.model.Job;
import org.jrecruiter.service.DataService;
import org.jrecruiter.service.JobService;
import org.jrecruiter.service.impl.DataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private @Autowired DataService dataService;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    /**
     * Logger Declaration.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(DataServiceImpl.class);

    @RequestMapping("/{id}/jobDetail.pdf")
    public String execute(@PathVariable Long id, ModelMap model) {

        if (id == null) {
            LOGGER.error("ID required for job detail.");
            return "redirect:/";
        }

        final Job job = jobService.getJobForId(id);

        if (job == null) {
            LOGGER.error("No job found for ID: " + id);
            return "redirect:/";
        }

        model.addAttribute("job", jobService.getJobForId(id));

        if (job.getUsesMap()){
            model.addAttribute("googleMapImage",
                    dataService.getGoogleMapImage(job.getLatitude(), job.getLongitude(),job.getZoomLevel()));
        }

        return "jobDetailPdfView";
    }

}
