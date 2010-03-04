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
package org.jrecruiter.web.actions.admin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jrecruiter.common.CalendarUtils;
import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.model.statistics.JobCountPerDay;
import org.jrecruiter.model.Job;
import org.jrecruiter.web.actions.BaseAction;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@Result(name="success", location="index", type="redirectAction")
public class ShowStatisticsAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = 4467043520853890820L;

    private JFreeChart chart;

    private List<Job> jobs = CollectionUtils.getArrayList();

    Boolean displayAjax = Boolean.FALSE;

    /**
     * Default view for this controller.
     *
     */
    public final String execute() {

        jobs = jobService.getUsersJobsForStatistics(super.getLoggedInUser().getUsername());

        if (displayAjax) {
            return "ajax";
        }

        return INPUT;
    }

    /**
     *
     */
    public final String chartJobsHits() throws Exception {

        String chartTitle = null;

        jobs = jobService.getUsersJobsForStatistics(super.getLoggedInUser().getUsername(), 10);
        chartTitle = "Job Statistics Top 10 - Hits";

            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            for (Job job : jobs) {

                if (job.getStatistic() != null) {

                        if (job.getStatistic().getCounter().longValue() >= 0) {
                            dataset.addValue(job.getStatistic().getCounter(),
                                    job.getJobTitle() + "_" + job.getId(), "");
                        }
                }
            }

            this.chart = createChart(dataset, chartTitle);

        return SUCCESS;
    }

    private static JFreeChart createChart(
            final CategoryDataset categorydataset, final String chartTitle) {
        final JFreeChart chart = ChartFactory.createBarChart(chartTitle,
                "Jobs", "Number of Hits", categorydataset,
                PlotOrientation.VERTICAL, true, true, false);

        final CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        categoryplot.setNoDataMessage("NO DATA!");

        chart.setBackgroundPaint(new Color(245, 245, 255));
        chart.setBorderPaint(new Color(204, 204, 204));
        chart.setBorderStroke(new BasicStroke(1f));

        final LegendTitle legend = chart.getLegend();
        legend.setWidth(1000);
        legend.setPosition(RectangleEdge.BOTTOM);

        final BlockBorder border = new BlockBorder(new Color(204, 204, 204));
        legend.setBorder(border);

        final CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE);
        categoryPlot.setRangeGridlinePaint(new Color(204, 204, 204));
        categoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        final NumberAxis numberaxis = (NumberAxis) categoryPlot.getRangeAxis();

        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setDrawBarOutline(true);

        final ItemLabelPosition itemlabelposition = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setPositiveItemLabelPosition(itemlabelposition);

        renderer
                .setItemLabelGenerator(new StandardCategoryItemLabelGenerator());

        renderer.setItemLabelsVisible(true);
        return chart;
    }

    public final String chartJobCount() throws Exception {

            final Calendar calendarToday = CalendarUtils.getCalendarWithoutTime();

            final Calendar calendar30    = CalendarUtils.getCalendarWithoutTime();
            calendar30.add(Calendar.MONTH, -36);

            final List<JobCountPerDay>jobCountPerDayList = jobService.getJobCountPerDayAndPeriod(calendar30.getTime(), calendarToday.getTime());

            final TimeSeries hitsPerDayData = new TimeSeries( "Hits", Day.class );
            final XYDataset hitsPerDayDataset = new TimeSeriesCollection( hitsPerDayData );
            this.chart = ChartFactory.createTimeSeriesChart("",
                    super.getText("class.ShowStatisticsAcion.chart.job.count.caption"), "", hitsPerDayDataset, false, true, false);


            final XYPlot xyplot = (XYPlot)this.chart.getPlot();

            for(JobCountPerDay jobCountPerDay : jobCountPerDayList ) {

              final Day day =  new Day(jobCountPerDay.getJobDate());

              if (jobCountPerDay.getAutomaticallyCleaned()) {

                  final Marker originalEnd = new ValueMarker(day.getFirstMillisecond());
                  originalEnd.setPaint(new Color(0, 80, 138, 150));
                  float[] dashPattern = { 6, 2 };

                  originalEnd.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT,
                          BasicStroke.JOIN_MITER, 10,
                          dashPattern, 0));
                  originalEnd.setLabelAnchor(RectangleAnchor.TOP_LEFT);
                  originalEnd.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
                  originalEnd.setLabel("Cleanup");
                  originalEnd.setAlpha(0.1F);
                  xyplot.addDomainMarker(originalEnd);
              }

              hitsPerDayData.add(day, jobCountPerDay.getTotalNumberOfJobs());
            }




            chart.setBackgroundPaint(new Color(255,255,255,0));


            xyplot.setDomainGridlinePaint(Color.LIGHT_GRAY);
            xyplot.setBackgroundPaint(new Color(255,255,255,0));

            xyplot.setRangeGridlinePaint(Color.LIGHT_GRAY);
            xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
            xyplot.setDomainCrosshairVisible(true);
            xyplot.setRangeCrosshairVisible(true);

            org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot.getRenderer();
            if(xyitemrenderer instanceof XYLineAndShapeRenderer)
            {
                XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyitemrenderer;
                xylineandshaperenderer.setBaseShapesVisible(false);
                xyitemrenderer.setSeriesPaint(0, new Color(244, 66, 0));
            }

            DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();

            dateaxis.setAutoRange(true);
            dateaxis.setAutoTickUnitSelection(true);

            NumberAxis valueAxis = (NumberAxis)xyplot.getRangeAxis();
            valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());


        return SUCCESS;
    }

    public Boolean getDisplayAjax() {
        return displayAjax;
    }

    public void setDisplayAjax(Boolean displayAjax) {
        this.displayAjax = displayAjax;
    }

    public JFreeChart getChart() {
        return chart;
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

}
