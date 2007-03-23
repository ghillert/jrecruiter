package org.jrecruiter.web.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.jrecruiter.Constants;
import org.jrecruiter.model.Job;
import org.jrecruiter.service.JobService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.OutputStream;
import java.util.List;

/**
 * List all the jobs. 
 * 
 * @author Gunnar Hillert
 * @version $Id: JobListAction.java 58 2006-10-16 03:45:45Z ghillert $
 *
 */
public class ShowStatisticsChartController extends MultiActionController  {
	
	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(ShowStatisticsChartController.class);
    
    /**
     * The service layer reference.
     */
    private JobService service;

    /**
     * Success View
     */
    private String successView;
    
    /**
     * Success View for showing the job details
     */
    private String successViewShowDetails;
    
    /**
     * Ajax View 
     */
    private String ajaxView;
    
    /**
     * Inject the service layer reference.
     * @param service 
     */
    public void setService(JobService service) {
		this.service = service;
	}

    /**
	 * @param ajaxView the ajaxView to set
	 */
	public void setAjaxView(String ajaxView) {
		this.ajaxView = ajaxView;
	}

	public void setSuccessViewShowDetails(String successViewShowDetails) {
		this.successViewShowDetails = successViewShowDetails;
	}

	/**
	 * @param successView the successView to set
	 */
	public void setSuccessView(String successView) {
		this.successView = successView;
	}


    /**
     * Default view for this controller.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @throws Exception Let Exceptions bubble up
     */
    public final ModelAndView view(final HttpServletRequest request,
                                   final HttpServletResponse response)
            throws Exception {

final List jobs = service.getUsersJobsForStatistics(request.getRemoteUser());

request.setAttribute("jobs", jobs);

String ajaxCall = request.getParameter("displayAjax");
if (ajaxCall != null && ajaxCall.equalsIgnoreCase("true")) {
    return new ModelAndView("ajax");
}

return new ModelAndView("success");
    }
    
    
    
        public final ModelAndView chartJobsHits(final HttpServletRequest request,
                final HttpServletResponse response) throws Exception {
        
        String chartTitle = null;

            String mode = request.getParameter("mode");
            boolean createChart = true;

            if (mode != null && mode.equals("unique")) { mode = "unique";}

            else if (mode != null && mode.equals("all")) { mode = "all";}

            else {
                createChart = false;
            }

            if (createChart){

                List < Job > jobs = null;

                if (mode.equals("unique")){
                    jobs = service.getUsersJobsForStatistics(request.getRemoteUser(), 10, Constants.StatsMode.UNIQUE_HITS);
                    chartTitle = "Job Statistics Top 10 - Unique Hits";
                } else {
                    jobs = service.getUsersJobsForStatistics(request.getRemoteUser(), 10, Constants.StatsMode.PAGE_HITS);
                    chartTitle = "Job Statistics Top 10 - Page Hits";
                }

                final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                for (Job job : jobs) {

                    if (job.getStatistics() != null) {

                        if (mode.equals("unique")){
                            if (job.getStatistics().getUniqueVisits().longValue()>0){
                                dataset.addValue(job.getStatistics().getUniqueVisits(),
                                    job.getJobTitle(), "");
                            }

                        } else {
                            if (job.getStatistics().getCounter().longValue()>0){
                                dataset.addValue(job.getStatistics().getCounter(), job.getJobTitle(), ""
                                    );
                            }
                        }

                    }
                }

                final JFreeChart chart = createChart(dataset, chartTitle);

                final OutputStream out = response.getOutputStream();
                ChartUtilities.writeChartAsPNG(out, chart, 400, 300);

                out.close();

            }

            return null;
    }

    private static JFreeChart createChart(final CategoryDataset categorydataset,
                                          final String chartTitle) {
        final JFreeChart chart = ChartFactory.createBarChart(
                        chartTitle,
                        "Jobs", "Number of Hits", categorydataset,
                        PlotOrientation.VERTICAL, true, true, false);

        final CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
        categoryplot.setNoDataMessage("NO DATA!");

        chart.setBackgroundPaint(new Color(245,245,255));
        chart.setBorderPaint(new Color(204,204,204));
        chart.setBorderStroke(new BasicStroke(1f));

        final LegendTitle legend = chart.getLegend();
        legend.setWidth(1000);
        legend.setPosition(RectangleEdge.BOTTOM);

        final BlockBorder border = new BlockBorder(new Color(204,204,204));
        legend.setBorder(border);

        final CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE);
        categoryPlot.setRangeGridlinePaint(new Color(204,204,204));
        categoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        final NumberAxis numberaxis = (NumberAxis) categoryPlot.getRangeAxis();

        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setDrawBarOutline(true);

        final ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setPositiveItemLabelPosition(itemlabelposition);

        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());


        renderer.setItemLabelsVisible(true);
        return chart;
    }

    
    
    
    /**
     * Convenient method for getting a i18n key's value with a single
     * string argument.
     *
     * @param msgKey
     * @param arg
     * @return
     */
    public String getText(String msgKey, String arg) {
        return getText(msgKey, new Object[] { arg });
    }
    
    /**
     * Convenience method for getting a i18n key's value with arguments.
     *
     * @param msgKey
     * @param args
     * @return
     */
    public String getText(String msgKey, Object[] args) {
        return getMessageSourceAccessor().getMessage(msgKey, args);
    }

    
    
}
