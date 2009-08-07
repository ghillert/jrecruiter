/**
 *
 */
package org.jrecruiter.web.views;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.jrecruiter.common.CalendarUtils;
import org.jrecruiter.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.View;

/**
 * Provides an XML document containing jobs, suitable for Indeed.com integration.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class XmlView implements View {

    public static final Logger LOGGER = LoggerFactory.getLogger(XmlView.class);

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

        response.setContentType(this.getContentType());

        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(response.getOutputStream(), "UTF-8");

        writer.writeStartDocument("UTF-8", "1.0");

        writer.writeStartElement("source");
        writer.writeStartElement("publisher");
        writer.writeCData("jRecruiter - AJUG Job Postings");
        writer.writeEndElement();

        writer.writeStartElement("publisherUrl");
        writer.writeCData("http://www.ajug.org/jobs/");
        writer.writeEndElement();

        writer.writeStartElement("lastBuildDate");
        writer.writeCharacters(CalendarUtils.getXmlFormatedDate());
        writer.writeEndElement();

        final List<Job> jobs = (List<Job>) model.get("jobs");
        final String serverAddress = (String) model.get("serverAddress");

        int i = 0;

        for (final Job job : jobs) {

            writer.writeStartElement("job");

            writer.writeStartElement("jobTitle");
            writer.writeCData(job.getJobTitle());
            writer.writeEndElement();

            writer.writeStartElement("date");
            writer.writeCData(CalendarUtils.getXmlFormatedDate(job.getUpdateDate()));
            writer.writeEndElement();

            writer.writeStartElement("referenceNumber");
            writer.writeCData(String.valueOf(job.getId()));
            writer.writeEndElement();

            writer.writeStartElement("url");

            final String jobUrl = serverAddress + "/job-detail.html?jobId=" + job.getId();

            writer.writeCData(jobUrl);
            writer.writeEndElement();

            writer.writeStartElement("company");
            writer.writeCData(job.getBusinessName());
            writer.writeEndElement();

            writer.writeStartElement("city");
            writer.writeCData(job.getBusinessCity());
            writer.writeEndElement();

            writer.writeStartElement("state");
            writer.writeCData(job.getBusinessState());
            writer.writeEndElement();

            writer.writeStartElement("country");
            writer.writeCData("US");
            writer.writeEndElement();

            writer.writeStartElement("postalCode");
            writer.writeCData(job.getBusinessZip());
            writer.writeEndElement();

            writer.writeStartElement("description");
            writer.writeCData(job.getDescription());
            writer.writeEndElement();

            writer.writeStartElement("education");
            writer.writeCData("");
            writer.writeEndElement();

            writer.writeStartElement("salary");
            writer.writeCData(String.valueOf(job.getSalary()));
            writer.writeEndElement();

            writer.writeStartElement("jobtype");
            writer.writeCData("");
            writer.writeEndElement();

            writer.writeStartElement("category");
            writer.writeCData(job.getIndustry().getName());
            writer.writeEndElement();

            writer.writeStartElement("experience");
            writer.writeCData("");
            writer.writeEndElement();

            writer.writeEndElement();

            if (job.getId().equals(Long.valueOf(3520))) {
                LOGGER.info("1");
            }

            if (i % 300 == 0) {
                writer.flush();
            }

            i++;

        }

        writer.writeEndDocument();
        writer.flush();
        writer.close();

    }

}
