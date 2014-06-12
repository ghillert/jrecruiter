package org.jrecruiter.web.actions;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistic;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the
 * layout defined by Indeed.com
 *
 * @author Gunnar Hillert
 * @since 1.0
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
	private final static Logger LOGGER = LoggerFactory
			.getLogger(DataServiceImpl.class);

	@RequestMapping("/job-detail.html")
	public String execute(
			@RequestParam(value = "jobId", required=false) Long jobId,
			@RequestParam(value = "id", required=false) String id,
			ModelMap model,
			HttpSession session) {

		if (jobId == null && id == null) {
			//TODOsuper.addActionError("Please provide a valid job id.");
			return "redirect:/show-jobs.html";
		}

		final Job job;

		if (id != null) {
			job = jobService.getJobForUniversalId(id);
		} else {
			job = jobService.getJobForId(jobId);
		}
		if (job == null) {

			String errorMessage = "Requested jobposting with id "
					+ (id == null ? jobId : id)
					+ " was not found.";
			LOGGER.warn(errorMessage);
			//TODO super.addActionMessage("The requested job posting does not exist.");
			return "redirect:/show-jobs.html";
		} else {

			Statistic statistics = job.getStatistic();

			if (statistics == null) {

				statistics = new Statistic();
				statistics.setJob(job);
				statistics.setCounter(Long.valueOf(0));
				job.setStatistic(statistics);
			}

			Set<Long> viewedPostings = CollectionUtils.getHashSet();

			if (session.getAttribute("visited") != null) {

				viewedPostings = (Set<Long>) session.getAttribute("visited");

				if (!viewedPostings.contains(jobId)) {
					long counter = statistics.getCounter().longValue() + 1;
					statistics.setCounter(Long.valueOf(counter));
					viewedPostings.add(jobId);
				}

			} else {

				long counter;

				if (statistics.getCounter() != null) {
					counter = statistics.getCounter().longValue() + 1;
				} else {
					counter = 1;
				}

				statistics.setCounter(Long.valueOf(counter));

				viewedPostings.add(jobId);
				session.setAttribute("visited", viewedPostings);

			}


			statistics.setLastAccess(new Date());
			jobService.updateJobStatistic(statistics);

		}

		model.addAttribute("job", job);
		return "job-detail";
	}

	@RequestMapping("/{id}/jobDetail.pdf")
	public String createPdf(@PathVariable Long id, ModelMap model) {

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

		if (job.getUsesMap()) {
			model.addAttribute(
					"googleMapImage",
					dataService.getGoogleMapImage(job.getLatitude(),
							job.getLongitude(), job.getZoomLevel()));
		}

		return "jobDetailPdfView";
	}

}
