/*
 * Copyright 2006-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrecruiter.web;

import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

/**
 * <!-- START SNIPPET: description -->
 * <p/>
 * A custom Result type for chart data. Built on top of
 * <a href="http://www.jfree.org/jfreechart/" target="_blank">JFreeChart</a>. When executed
 * this Result will write the given chart as a PNG or JPG to the servlet output stream.
 * <p/>
 * <!-- END SNIPPET: description -->
 * <p/>
 * <b>This result type takes the following parameters:</b>
 * <p/>
 * <!-- START SNIPPET: params -->
 * <p/>
 * <ul>
 * <p/>
 * <li><b>value</b> - the name of the JFreeChart object on the ValueStack, defaults to 'chart'.</li>
 * <p/>
 * <li><b>type</b> - the render type for this chart. Can be jpg (or jpeg) or png. Defaults to png.</li>
 * <p/>
 * <li><b>width (required)</b> - the width (in pixels) of the rendered chart.</li>
 * <p/>
 * <li><b>height (required)</b> - the height (in pixels) of the rendered chart.</li>
 * <p/>
 * </ul>
 * <!-- END SNIPPET: params -->
 * <p/>
 * <b>Example:</b>
 * <p/>
 * <pre><!-- START SNIPPET: example -->
 * public class ExampleChartAction extends ActionSupport {
 *
 *	    private JFreeChart chart;
 *
 *	    public String execute() throws Exception {
 *		    // chart creation logic...
 *		    XYSeries dataSeries = new XYSeries(new Integer(1)); // pass a key for this serie
 *		    for (int i = 0; i <= 100; i++) {
 *			    dataSeries.add(i, RandomUtils.nextInt());
 *		    }
 *		    XYSeriesCollection xyDataset = new XYSeriesCollection(dataSeries);
 *
 *		    ValueAxis xAxis = new NumberAxis("Raw Marks");
 *		    ValueAxis yAxis = new NumberAxis("Moderated Marks");
 *
 *		    // set my chart variable
 *		    chart =
 *			    new JFreeChart( "Moderation Function", JFreeChart.DEFAULT_TITLE_FONT,
 *				    new XYPlot( xyDataset, xAxis, yAxis, new StandardXYItemRenderer(StandardXYItemRenderer.LINES)),
 *				    false);
 *		    chart.setBackgroundPaint(java.awt.Color.white);
 *
 *		    return SUCCESS;
 *	    }
 *
 *      // this method will get called if we specify &lt;param name="value"&gt;chart&lt;/param&gt;
 *	    public JFreeChart getChart() {
 *		    return chart;
 *	    }
 *  }
 *
 * &lt;result name="success" type="chart"&gt;
 *   &lt;param name="value"&gt;chart&lt;/param&gt;
 *   &lt;param name="type"&gt;png&lt;/param&gt;
 *   &lt;param name="width"&gt;640&lt;/param&gt;
 *   &lt;param name="height"&gt;480&lt;/param&gt;
 * &lt;/result&gt;
 * <!-- END SNIPPET: example --></pre>
 */
public class CustomChartResult implements Result {

	private static final long serialVersionUID = -6484761870055986612L;
	private static final String DEFAULT_TYPE = "png";
	private static final String DEFAULT_VALUE = "chart";

	private JFreeChart chart; // the JFreeChart to render
	private boolean chartSet;
	Integer height, width;
	String type = DEFAULT_TYPE; // supported are jpg, jpeg or png, defaults to png
	String value = DEFAULT_VALUE; // defaults to 'chart'

	// CONSTRUCTORS ----------------------------

	public CustomChartResult() {
		super();
	}

	public CustomChartResult(JFreeChart chart, int height, int width) {
		this.chart = chart;
		this.height = height;
		this.width = width;
	}

	// ACCESSORS ----------------------------

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chartSet = true;
		this.chart = chart;
	}

	// OTHER METHODS -----------------------

	// Required by com.opensymphony.xwork2.Result

	/**
	 * Executes the result. Writes the given chart as a PNG or JPG to the servlet output stream.
	 *
	 * @param invocation an encapsulation of the action execution state.
	 * @throws Exception if an error occurs when creating or writing the chart to the servlet output stream.
	 */
	public void execute(ActionInvocation invocation) throws Exception {
		if (!chartSet) { // if our chart hasn't been set (by the testcase), we'll look it up in the value stack
			chart = (JFreeChart) invocation.getStack().findValue(value, JFreeChart.class);
		}
		if (chart == null) {// we need to have a chart object - if not, blow up
			throw new IllegalArgumentException("No JFreeChart object found on the stack with name " + value);
		}
		// make sure we have some value for the width and height
		if (height == null) {
			throw new IllegalArgumentException("No height parameter was given.");
		}
		if (width == null) {
			throw new IllegalArgumentException("No width parameter was given.");
		}

		// get a reference to the servlet output stream to write our chart image to
		OutputStream os = ServletActionContext.getResponse().getOutputStream();
		try {
			// check the type to see what kind of output we have to produce
			if ("png".equalsIgnoreCase(type)) {
				ChartUtilities.writeChartAsPNG(os, chart, width, height, true, 0);
			} else if ("jpg".equalsIgnoreCase(type) || "jpeg".equalsIgnoreCase(type)) {
				ChartUtilities.writeChartAsJPEG(os, chart, width, height);
			} else {
				throw new IllegalArgumentException(type + " is not a supported render type (only JPG and PNG are).");
			}
		} finally {
			if (os != null) os.flush();
		}
	}
}
