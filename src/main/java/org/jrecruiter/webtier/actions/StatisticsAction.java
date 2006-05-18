/*
 *	http://www.jrecruiter.org
 *
 *	Disclaimer of Warranty.
 *
 *	Unless required by applicable law or agreed to in writing, Licensor provides
 *	the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
 *	including, without limitation, any warranties or conditions of TITLE,
 *	NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
 *	solely responsible for determining the appropriateness of using or
 *	redistributing the Work and assume any risks associated with Your exercise of
 *	permissions under this License.
 *
 */
package org.jrecruiter.webtier.actions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
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
import org.jrecruiter.service.JobServiceInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * This struts action will generate charts for job
 * statistics purposes.
 *
 * @author Gunnar Hillert
 * @version $Revision$, $Date$, $Author$
 */
public class StatisticsAction extends DispatchAction {

    public static final Logger logger = Logger
            .getLogger(StatisticsAction.class);

    public ActionForward showStatistics(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (request.getUserPrincipal() != null) {

            final ApplicationContext context = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(servlet
                            .getServletContext());

            final JobServiceInterface service = (JobServiceInterface) context
                    .getBean("jobService");

            final List jobs = service.getUsersJobsForStatistics(request.getRemoteUser());

            request.setAttribute("jobs", jobs);

            String ajaxCall = request.getParameter("displayAjax");
            if (ajaxCall != null && ajaxCall.equalsIgnoreCase("true")) {
                return mapping.findForward("ajax");
            }

            return mapping.findForward("success");

        }
        return mapping.findForward("sessionTimeout");
    }

    public ActionForward chartJobsHits(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String chartTitle = null;

        if (request.getUserPrincipal() != null) {

            final ApplicationContext context = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(servlet
                            .getServletContext());

            final JobServiceInterface service = (JobServiceInterface) context
                    .getBean("jobService");

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
        return mapping.findForward("sessionTimeout");
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

}
