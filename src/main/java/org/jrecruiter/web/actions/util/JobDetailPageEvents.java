package org.jrecruiter.web.actions.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class JobDetailPageEvents extends PdfPageEventHelper {

    /** Initialize Logging. */
    private final static Logger LOGGER = LoggerFactory.getLogger(JobDetailPageEvents.class);

    public BaseFont font;
    public PdfTemplate tpl;

    /**

     * @see com.lowagie.text.pdf.PdfPageEventHelper#onOpenDocument(com.lowagie.text.pdf.PdfWriter, com.lowagie.text.Document)

     */
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {

        tpl = writer.getDirectContent().createTemplate(100, 100);

        try {
            font = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
        } catch (DocumentException e) {
            LOGGER.error("Error creating font.", e);
        } catch (IOException e) {
            LOGGER.error("Error creating font.", e);
        }

    }

    /**

     * @see com.lowagie.text.pdf.PdfPageEventHelper#onEndPage(com.lowagie.text.pdf.PdfWriter, com.lowagie.text.Document)

     */
    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        PdfContentByte cb = writer.getDirectContent();

        //~~~~~draw header and footer boxes~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        cb.saveState();
        cb.setRGBColorFill(0, 80, 138);
        cb.rectangle(0, document.top(), document.getPageSize().getWidth(), document.top() + document.topMargin());

        cb.fill();
        cb.restoreState();

        cb.saveState();

        cb.setRGBColorFill(0, 80, 138);
        cb.rectangle(0, 0, document.getPageSize().getWidth(), document.bottom());

        cb.fill();
        cb.restoreState();

        //~~~~~Add Header Text~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        float textBaseHeader = document.top() + (document.topMargin() / 2) - 6;

        cb.beginText();
        cb.setFontAndSize(font, 12);
        cb.setRGBColorFill(224, 113, 85);
        cb.setTextMatrix(document.left(), textBaseHeader);
        cb.showText("j");
        cb.resetRGBColorFill();
        cb.setRGBColorFill(255, 255, 255);
        cb.showText("Recruiter");

        cb.endText();

        //~~~~Compose the footer~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        String text = "Page " + writer.getPageNumber() + " of ";

        float textSize = font.getWidthPoint(text, 12);
        float textBase = document.bottom() - 20;

        cb.beginText();
        cb.setFontAndSize(font, 12);

        float adjust = font.getWidthPoint("0", 12);

        cb.setTextMatrix(document.right() - textSize - adjust, textBase);
        cb.showText(text);
        cb.endText();
        cb.addTemplate(tpl, document.right() - adjust, textBase);
        cb.saveState();

    }



    /**

     * @see com.lowagie.text.pdf.PdfPageEventHelper#onStartPage(com.lowagie.text.pdf.PdfWriter, com.lowagie.text.Document)

     */
    @Override
    public void onStartPage(PdfWriter writer, Document document) {

    }



    /**

     * @see com.lowagie.text.pdf.PdfPageEventHelper#onCloseDocument(com.lowagie.text.pdf.PdfWriter, com.lowagie.text.Document)

     */
    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {

       tpl.beginText();

       tpl.setFontAndSize(font, 12);

       tpl.setTextMatrix(0, 0);

       tpl.showText("" + (writer.getPageNumber() - 1));

       tpl.endText();

    }

}
