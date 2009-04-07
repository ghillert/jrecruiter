/**
 * 
 */
package org.jrecruiter.web.views;

import java.awt.Color;
import java.awt.Image;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jrecruiter.model.Job;
import org.jrecruiter.web.actions.util.JobDetailPageEvents;
import org.springframework.web.servlet.view.document.AbstractPdfView;

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
 * @version $Id$
 */
public class JobDetailPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(final Map<String, Object> model, 
			                        final Document document,
			                        final PdfWriter pdfWriter, 
			                        final HttpServletRequest request, 
			                        final HttpServletResponse response)
			throws Exception {
		
		final Job job = (Job)model.get("job");
		final Image googleMapImage = (Image)model.get("googleMapImage");
        
        super.setContentType("application/pdf");

        pdfWriter.setPageEvent(new JobDetailPageEvents());

        document.open();

        document.addCreationDate();
        document.addAuthor("www.jRecruiter.org");
        document.addTitle("jRecruiter Job Posting Detail");

        if (job == null) {
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
            
            if (job.getOfferedBy() != null) {
            	addPdfRow(table, "Offered By", job.getOfferedBy().getName());
            }
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

            if (job.getUsesMap() && googleMapImage != null) {

                this.addPdfHeader(pdfWriter, document, "Job Location2");

                com.lowagie.text.Image img = com.lowagie.text.Image.getInstance(googleMapImage,
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

	}

	/** XML Document **/
	private org.jdom.Document document;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.View#getContentType()
	 */
	@Override
	public String getContentType() {
		return "text/xml";
	}

	//~~~~~Helper~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
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


	
}
