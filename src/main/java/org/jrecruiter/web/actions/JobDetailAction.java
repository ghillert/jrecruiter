package org.jrecruiter.web.actions;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.service.DataService;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.actions.util.JobDetailPageEvents;
import org.jrecruiter.web.interceptor.StoreMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.texturemedia.smarturls.Result;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
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
    private final static Logger LOGGER = LoggerFactory.getLogger(JobDetailAction.class);

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
                 statistics.setCounter(Long.valueOf(0));
                 statistics.setUniqueVisits(Long.valueOf(0));
                 job.setStatistic(statistics);
             }

             Set<Long> viewedPostings = new HashSet<Long>();

             if (session.get("visited") != null){

                 viewedPostings = (Set<Long>)session.get("visited");

                 if (viewedPostings.contains(jobId)){


                 } else {
                     long counter = statistics.getUniqueVisits().longValue() + 1 ;
                     statistics.setUniqueVisits(Long.valueOf(counter));
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


                 statistics.setUniqueVisits(Long.valueOf(counter));

                 viewedPostings.add(jobId);
                 session.put("visited", viewedPostings);

             }

             Long counter = statistics.getCounter().longValue();
             counter ++;

             statistics.setCounter(Long.valueOf(counter));
             statistics.setLastAccess(new Date());
             jobService.updateJob(this.job);

            }

    return SUCCESS;
    }

    private void addPdfHeader(PdfWriter pdfWriter, Document document, String headerString) throws DocumentException {

        final Paragraph jobLocationHeader = new Paragraph(new Phrase(headerString, FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD)));
        document.add(jobLocationHeader);

        float pos = pdfWriter.getVerticalPosition(false) - 2;

        PdfContentByte cb = pdfWriter.getDirectContent();

        cb.moveTo(document.leftMargin(), pos);
        cb.lineTo(document.right()+document.rightMargin(), pos);
        cb.setColorStroke(Color.RED);
        cb.stroke();

        cb.moveTo(document.leftMargin(), pos - 40);

    }

    private void addPdfRow(PdfPTable table, String label, String value) throws DocumentException {

        final PdfPCell labelCell = new PdfPCell(new Phrase(label));
        final PdfPCell valueCell = new PdfPCell(new Phrase(value));

        labelCell.disableBorderSide(PdfPCell.LEFT);
        labelCell.disableBorderSide(PdfPCell.TOP);
        labelCell.disableBorderSide(PdfPCell.RIGHT);
        labelCell.disableBorderSide(PdfPCell.BOTTOM);

        valueCell.disableBorderSide(PdfPCell.LEFT);
        valueCell.disableBorderSide(PdfPCell.TOP);
        valueCell.disableBorderSide(PdfPCell.RIGHT);
        valueCell.disableBorderSide(PdfPCell.BOTTOM);

        table.addCell(labelCell);
        table.addCell(valueCell);

    }

    public String exportJobAsPdf() throws Exception {

        if (this.jobId == null) {
            super.addActionError("Please provide a valid job id.");
            return "showJobs";
        }

        final HttpServletResponse response = ServletActionContext.getResponse();

        response.setContentType("application/pdf");

        final Document document = new Document();


        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);

        pdfWriter.setPageEvent(new JobDetailPageEvents());

        document.open();

        document.addCreationDate();
        document.addAuthor("www.jRecruiter.org");
        document.addTitle("jRecruiter Job Posting Detail");

        this.job = jobService.getJobForId(jobId);


        if (this.job == null) {
            response.setHeader("Content-disposition",
                    "attachment; filename=" +
                    "No_Job.pdf");

            document.add(new Paragraph("No job posting found for the provided id."));
        } else {

            response.setHeader("Content-disposition",
                    "attachment; filename=" + "JobDetail_" + job.getId());

            //~~~~Render Job Summary~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            this.addPdfHeader(pdfWriter, document, "Summary");

            final PdfPTable table = new PdfPTable(2);

            table.setWidthPercentage(100);
            table.setSpacingBefore(10);

            table.setWidths(new float[]{25, 75});

            addPdfRow(table, "Job Title", job.getJobTitle());
            addPdfRow(table, "Business Name", job.getBusinessName());
            addPdfRow(table, "Email", job.getBusinessEmail());
            addPdfRow(table, "Address 1", job.getBusinessAddress1());
            addPdfRow(table, "Address 2", job.getBusinessAddress2());
            addPdfRow(table, "City", job.getBusinessCity());
            addPdfRow(table, "Business Name", job.getBusinessName());
            addPdfRow(table, "Phone", job.getBusinessPhone());
            addPdfRow(table, "State", job.getBusinessState());
            addPdfRow(table, "Zip", job.getBusinessZip());
            addPdfRow(table, "Job Id", String.valueOf(job.getId()));
            addPdfRow(table, "Industry", job.getIndustry().getName());
            addPdfRow(table, "Industry Other", job.getIndustryOther());
            addPdfRow(table, "Offered By", job.getOfferedBy().getName());

            if (job.getRegion() != null) {
                addPdfRow(table, "Region", job.getRegion().getName());
            }

            addPdfRow(table, "Region Other", job.getRegionOther());
            addPdfRow(table, "Date", String.valueOf(job.getUpdateDate()));
            addPdfRow(table, "Website", job.getWebsite());
            document.add(table);

            final Paragraph header = new Paragraph(new Phrase(job.getJobTitle()));
            document.add(header);

            //~~~~~Render Map Location if available~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            if (job.getUsesMap()) {

                this.addPdfHeader(pdfWriter, document, "Job Location");

                com.lowagie.text.Image img = com.lowagie.text.Image.getInstance(
                        dataService.getGoogleMapImage(job.getLongitude(), job.getLatitude(), job.getZoomLevel()),
                        Color.WHITE);
                img.scalePercent(75);
                img.setBorder(Rectangle.BOX);
                img.enableBorderSide(Rectangle.BOX);
                img.setBorderColor(Color.BLACK);
                img.setBorderWidth(1);

                document.add(img);

            }

            //~~~~~Render Job Description~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            this.addPdfHeader(pdfWriter, document, "Job Description");

            Paragraph jobDescription = new Paragraph(job.getDescription());
            document.add(jobDescription);

            //~~~~~Render Job Restrictions~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            this.addPdfHeader(pdfWriter, document, "Job Restrictions");

            Paragraph jobRestrictions = new Paragraph(job.getJobRestrictions());
            document.add(jobRestrictions);

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
