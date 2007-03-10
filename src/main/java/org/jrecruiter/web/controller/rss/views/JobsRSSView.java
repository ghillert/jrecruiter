/**
 * 
 */
package org.jrecruiter.web.controller.rss.views;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jrecruiter.model.Job;
import org.springmodules.web.servlet.view.AbstractRssView;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;


/**
 * @author hillert
 *
 */
public class JobsRSSView extends AbstractRssView {

	/**
	 * 
	 */
	public JobsRSSView() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.springmodules.web.servlet.view.AbstractRssView#buildFeed(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.sun.syndication.feed.synd.SyndFeed)
	 */
	@Override
	protected void buildFeed(Map model, HttpServletRequest request, HttpServletResponse respons, SyndFeed feed) throws Exception {
		feed.setTitle("AJUG Job Postings");
        feed.setLink("http://www.ajug.org/jobs");
        feed.setDescription("RSS feed of the Atlanta Java User Group's job posting service.");
        this.setDefaultFeedType("rss_2.0");
        List <SyndEntry>entries = new ArrayList<SyndEntry>();
        SyndEntry entry;
        SyndContent description;

        List <Job> jobs = (List<Job>)model.get("jobs");
        
        for (Job job : jobs) {
            entry = new SyndEntryImpl();
            entry.setTitle(job.getJobTitle());
            //entry.setLink("http://wiki.java.net/bin/view/Javawsxml/Rome01");
            entry.setPublishedDate(job.getUpdateDate());
            description = new SyndContentImpl();
            description.setType("text/plain");
            description.setValue(job.getDescription());
            entry.setDescription(description);
            entries.add(entry);
        }

        feed.setEntries(entries);
        
	}

}
