package org.jrecruiter.web.actions;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.service.DataService;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.interceptor.StoreMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.texturemedia.smarturls.Result;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */

@Result(name="showJobs", location="show-jobs", type="redirectAction")
public class JobDetailAction extends BaseAction implements SessionAware {


    /** serialVersionUID. */
    private static final long serialVersionUID = 369806210598096582L;

    private Long jobId;

    private Job job;

    private @Autowired JobService jobService;
    private @Autowired DataService dataService;
    private Map<String, Object>session;

    /**
     * Logger Declaration.
     */
    private final Log LOGGER = LogFactory.getLog(JobDetailAction.class);

    @SuppressWarnings("unchecked")
    public void setSession(Map session) {
        this.session = session;
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    @SuppressWarnings("unchecked")
    @StoreMessages
    public String execute() throws Exception {

        if (this.jobId == null) {
            super.addActionError("Please provide a valid job id.");
            return "showJobs";
        }

        this.job = jobService.getJobForId(jobId);

        if (this.job==null){

            String errorMessage = "Requested jobposting with id " + jobId + " was not found.";
            LOGGER.warn(errorMessage);
            super.addActionMessage("The requested job posting does not exist.");
            return "showJobs";
        } else {

             Statistic statistics = job.getStatistic();

             if (statistics == null) {

                 statistics = new Statistic();
                 statistics.setJob(job);
                 statistics.setCounter(new Long(0));
                 statistics.setUniqueVisits(new Long(0));
                 job.setStatistic(statistics);
             }

             Set<Long> viewedPostings = new HashSet<Long>();

             if (session.get("visited") != null){

                 viewedPostings = (Set<Long>)session.get("visited");

                 if (viewedPostings.contains(jobId)){


                 } else {
                     long counter = statistics.getUniqueVisits().longValue() + 1 ;
                     statistics.setUniqueVisits(new Long (counter));
                     viewedPostings.add(jobId);
                 }

             } else {

                 long counter;

                 if (statistics.getUniqueVisits() != null)
                 {
                     counter = statistics.getUniqueVisits().longValue() + 1 ;
                 } else {
                     counter = 1;
                 }


                 statistics.setUniqueVisits(new Long (counter));

                 viewedPostings.add(jobId);
                 session.put("visited", viewedPostings);

             }

             Long counter = statistics.getCounter().longValue();
             counter ++;

             statistics.setCounter(new Long(counter));
             statistics.setLastAccess(new Date());
             jobService.updateJob(this.job);

            }

    return SUCCESS;
    }

    public String exportJobAsPdf() throws Exception {

        if (this.jobId == null) {
            super.addActionError("Please provide a valid job id.");
            return "showJobs";
        }

        HttpServletResponse response = ServletActionContext.getResponse();

        response.setContentType("application/pdf");

        Document document = new Document();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        this.job = jobService.getJobForId(jobId);

        if (this.job == null) {
            response.setHeader("Content-disposition",
                    "attachment; filename=" +
                    "No_Job.pdf");

            document.add(new Paragraph("test"));
        } else {

            response.setHeader("Content-disposition",
                    "attachment; filename=" + "JobDetail_" + job.getId());

            Paragraph header = new Paragraph(new Phrase(job.getJobTitle()));
            document.add(header);

            PdfPTable table = new PdfPTable(2);

            com.lowagie.text.Image img = com.lowagie.text.Image.getInstance(
                    dataService.getGoogleMapImage(job.getLongitude(), job.getLatitude(), job.getZoomLevel()),
                    Color.WHITE);

            document.add(img);

            table.setWidths(new float[]{50, 50});

            PdfPCell labelCell0 = new PdfPCell(new Phrase("Email"));
            PdfPCell valueCell0 = new PdfPCell(new Phrase(job.getBusinessEmail()));

            PdfPCell labelCell1 = new PdfPCell(new Phrase("Address 1"));
            PdfPCell valueCell1 = new PdfPCell(new Phrase(job.getBusinessAddress1()));

            PdfPCell labelCell2 = new PdfPCell(new Phrase("Address 2"));
            PdfPCell valueCell2 = new PdfPCell(new Phrase(job.getBusinessAddress2()));

            PdfPCell labelCell3 = new PdfPCell(new Phrase("City"));
            PdfPCell valueCell3 = new PdfPCell(new Phrase(job.getBusinessCity()));

            PdfPCell labelCell4 = new PdfPCell(new Phrase("Business Name"));
            PdfPCell valueCell4 = new PdfPCell(new Phrase(job.getBusinessName()));

            PdfPCell labelCell5 = new PdfPCell(new Phrase("Phone"));
            PdfPCell valueCell5 = new PdfPCell(new Phrase(job.getBusinessPhone()));

            PdfPCell labelCell6 = new PdfPCell(new Phrase("State"));
            PdfPCell valueCell6 = new PdfPCell(new Phrase(job.getBusinessState()));

            PdfPCell labelCell7 = new PdfPCell(new Phrase("Zip"));
            PdfPCell valueCell7 = new PdfPCell(new Phrase(job.getBusinessZip()));

            PdfPCell labelCell8 = new PdfPCell(new Phrase("Description"));
            PdfPCell valueCell8 = new PdfPCell(new Phrase(job.getDescription()));

            PdfPCell labelCell9 = new PdfPCell(new Phrase("Job Id"));
            PdfPCell valueCell9 = new PdfPCell(new Phrase(job.getId()));

            PdfPCell labelCell10 = new PdfPCell(new Phrase("Industry"));
            PdfPCell valueCell10 = new PdfPCell(new Phrase(job.getIndustry().getName()));

            PdfPCell labelCell11 = new PdfPCell(new Phrase("Industry Other"));
            PdfPCell valueCell11 = new PdfPCell(new Phrase(job.getIndustryOther()));

            PdfPCell labelCell12 = new PdfPCell(new Phrase("Restriction"));
            PdfPCell valueCell12 = new PdfPCell(new Phrase(job.getJobRestrictions()));

            PdfPCell labelCell13 = new PdfPCell(new Phrase("Latitude"));
            PdfPCell valueCell13 = new PdfPCell(new Phrase(String.valueOf(job.getLatitude())));

            PdfPCell labelCell14 = new PdfPCell(new Phrase("Longitude"));
            PdfPCell valueCell14 = new PdfPCell(new Phrase(String.valueOf(job.getLongitude())));

            PdfPCell labelCell15 = new PdfPCell(new Phrase("Offered By"));
            PdfPCell valueCell15 = new PdfPCell(new Phrase(job.getOfferedBy().getName()));


            if (job.getRegion() != null) {
                PdfPCell labelCell16 = new PdfPCell(new Phrase("Region"));
                PdfPCell valueCell16 = new PdfPCell(new Phrase(job.getRegion().getName()));
                table.addCell(labelCell16);
                table.addCell(valueCell16);
            }

            PdfPCell labelCell17 = new PdfPCell(new Phrase("Region Other"));
            PdfPCell valueCell17 = new PdfPCell(new Phrase(job.getRegionOther()));

            PdfPCell labelCell18 = new PdfPCell(new Phrase("Date"));
            PdfPCell valueCell18 = new PdfPCell(new Phrase(String.valueOf(job.getUpdateDate())));

            PdfPCell labelCell19 = new PdfPCell(new Phrase("Website"));
            PdfPCell valueCell19 = new PdfPCell(new Phrase(job.getWebsite()));

            table.addCell(labelCell0);
            table.addCell(valueCell0);
            table.addCell(labelCell1);
            table.addCell(valueCell1);
            table.addCell(labelCell2);
            table.addCell(valueCell2);
            table.addCell(labelCell3);
            table.addCell(valueCell3);
            table.addCell(labelCell4);
            table.addCell(valueCell4);
            table.addCell(labelCell5);
            table.addCell(valueCell5);
            table.addCell(labelCell6);
            table.addCell(valueCell6);
            table.addCell(labelCell7);
            table.addCell(valueCell7);
            table.addCell(labelCell8);
            table.addCell(valueCell8);
            table.addCell(labelCell9);
            table.addCell(valueCell9);
            table.addCell(labelCell10);
            table.addCell(valueCell10);
            table.addCell(labelCell11);
            table.addCell(valueCell11);
            table.addCell(labelCell12);
            table.addCell(valueCell12);
            table.addCell(labelCell13);
            table.addCell(valueCell13);
            table.addCell(labelCell14);
            table.addCell(valueCell14);
            table.addCell(labelCell15);
            table.addCell(valueCell15);

            table.addCell(labelCell17);
            table.addCell(valueCell17);
            table.addCell(labelCell18);
            table.addCell(valueCell18);

            table.addCell(labelCell19);
            table.addCell(valueCell19);

            document.add(table);
        }

        document.close();

        response.setContentLength(baos.size());
        ServletOutputStream out = response.getOutputStream();
        baos.writeTo(out);
        out.flush();

        //response.

        return null;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

}
