package org.jrecruiter.web.actions.admin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
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
import org.jrecruiter.web.actions.BaseAction;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class ShowStatisticsAction extends BaseAction {

	/** serialVersionUID. */
	private static final long serialVersionUID = 4467043520853890820L;

	/**
	 * Logger Declaration.
	 */
	private final Log LOGGER = LogFactory.getLog(ShowStatisticsAction.class);

	private JFreeChart chart;

	private List<Job> jobs = new ArrayList<Job>();

	private String mode;

	Boolean displayAjax = Boolean.FALSE;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * Default view for this controller.
	 *
	 */
	public final String execute() {

		jobs = jobService.getUsersJobsForStatistics(super.getLoggedInUser().getUsername());

		if (displayAjax) {
			return "ajax";
		}

		return SUCCESS;
	}

	public final String chartJobsHits() throws Exception {

		String chartTitle = null;

		if (mode != null && mode.equals("unique")) {
			mode = "unique";
		}

		if (mode != null && mode.equals("all")) {
			mode = "all";
		}

			if (mode != null && mode.equals("unique")) {
				jobs = jobService.getUsersJobsForStatistics(super.getLoggedInUser().getUsername(), 10, Constants.StatsMode.UNIQUE_HITS);
				chartTitle = "Job Statistics Top 10 - Unique Hits";
			} else {
				jobs = jobService.getUsersJobsForStatistics(super.getLoggedInUser().getUsername(), 10, Constants.StatsMode.PAGE_HITS);
				chartTitle = "Job Statistics Top 10 - Page Hits";
			}

			final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			for (Job job : jobs) {

				if (job.getStatistic() != null) {

					if (mode != null && mode.equals("unique")) {
						if (job.getStatistic().getUniqueVisits().longValue() > 0) {
							dataset.addValue(job.getStatistic()
									.getUniqueVisits(), job.getJobTitle(), "");
						}

					} else {
						if (job.getStatistic().getCounter().longValue() > 0) {
							dataset.addValue(job.getStatistic().getCounter(),
									job.getJobTitle(), "");
						}
					}

				}
			}

			this.chart = createChart(dataset, chartTitle);

		return super.SUCCESS;
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
