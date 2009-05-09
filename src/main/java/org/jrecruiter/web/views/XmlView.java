/**
 *
 */
package org.jrecruiter.web.views;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.CDATA;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jrecruiter.common.CalendarUtils;
import org.jrecruiter.model.Job;
import org.springframework.web.servlet.View;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class XmlView implements View {

    /** XML Document **/
    private org.jdom.Document document;

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.View#getContentType()
     */
    @Override
    public String getContentType() {
        return "text/xml";
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.View#render(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void render(final Map<String, ?> model,
                       final HttpServletRequest request,
                       final HttpServletResponse response) throws Exception {

        this.document = new org.jdom.Document(new Element("source"));

        final Element rootElement = this.document.getRootElement();
        final Element publisherElement = new Element("publisher");
        publisherElement.setText("jRecruiter - AJUG Job Postings");

        final Element publisherUrlElement = new Element("publisherUrl");
        publisherUrlElement.setText("http://www.ajug.org/jobs/");

        final Element lastBuildDateElement = new Element("lastBuildDate");

        lastBuildDateElement.setText(CalendarUtils.getXmlFormatedDate());

        rootElement.addContent(publisherElement);
        rootElement.addContent(publisherUrlElement);
        rootElement.addContent(lastBuildDateElement);

        final List<Job> jobs = (List<Job>) model.get("jobs");

        for (final Job job : jobs) {

            final Element jobElement = new Element("job");

            final Element jobTitleElement           = new Element("title");
            final Element jobDateElement            = new Element("date");
            final Element jobReferenceNumberElement = new Element("referenceNumber");
            final Element jobUrlElement             = new Element("url");
            final Element jobCompanyElement         = new Element("company");
            final Element jobCity                   = new Element("city");
            final Element jobState                  = new Element("state");
            final Element jobCountry                = new Element("country");
            final Element jobPostalCode             = new Element("postalCode");
            final Element jobDescription            = new Element("description");
            final Element jobSalary                 = new Element("salary");
            final Element jobEducation              = new Element("education");
            final Element jobType                   = new Element("jobtype");
            final Element jobCategory               = new Element("category");
            final Element jobExperience             = new Element("experience");

            jobTitleElement.addContent(new CDATA(job.getJobTitle()));
            jobDateElement.addContent(CalendarUtils.getXmlFormatedDate(job.getUpdateDate()));
            jobReferenceNumberElement.addContent(String.valueOf(job.getId()));
            jobUrlElement.addContent(new CDATA(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/job-detail.html?jobId=" + job.getId()));
            jobCompanyElement.addContent(new CDATA(job.getBusinessName()));
            jobCity.addContent(new CDATA(job.getBusinessCity()));
            jobState.addContent(new CDATA(job.getBusinessCity()));
            jobCountry.addContent(new CDATA("US")); //FIXME no hard coding
            jobPostalCode.addContent(new CDATA(job.getBusinessZip()));
            jobDescription.addContent(new CDATA(job.getDescription()));
            jobSalary.addContent(new CDATA(String.valueOf(job.getSalary())));
            jobEducation.addContent(new CDATA("")); //FIXME job education currently not implemented
            jobType.addContent(new CDATA("")); //FIXME job type currently not implemented
            jobCategory.addContent(new CDATA(job.getIndustry().getName())); //FIXME Nullpointer
            jobExperience.addContent(new CDATA("")); //FIXME job experience currently not implemented

            jobElement.addContent(jobTitleElement);
            jobElement.addContent(jobDateElement);
            jobElement.addContent(jobReferenceNumberElement);
            jobElement.addContent(jobUrlElement);
            jobElement.addContent(jobCompanyElement);
            jobElement.addContent(jobCity);
            jobElement.addContent(jobState);
            jobElement.addContent(jobCountry);
            jobElement.addContent(jobPostalCode);
            jobElement.addContent(jobDescription);
            jobElement.addContent(jobSalary);
            jobElement.addContent(jobEducation);
            jobElement.addContent(jobType);
            jobElement.addContent(jobCategory);
            jobElement.addContent(jobExperience);

            rootElement.addContent(jobElement);

        }

        final XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());

        final String xmlAsString = outputter.outputString(document);

        response.setContentType(this.getContentType());
        response.setContentLength(xmlAsString.length());

        final PrintWriter out = new PrintWriter(response.getOutputStream());
        out.print(xmlAsString);
        out.flush();
        out.close();

    }

}
