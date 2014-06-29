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

import org.jmesa.view.html.AbstractHtmlView;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.HtmlSnippets;

/**
 * Custom jmesa View - removing the header and footer, showing all records
 * (no pagination)
 *
 *
 * @author Gunnar Hillert
 */
public class JmesaViewWithoutHeader  extends AbstractHtmlView {

	public Object render() {
		HtmlSnippets snippets = getHtmlSnippets();

		HtmlBuilder html = new HtmlBuilder();

		html.append(snippets.themeStart());

		html.append(snippets.tableStart());

		html.append(snippets.theadStart());

		html.append(snippets.header());

		html.append(snippets.theadEnd());

		html.append(snippets.tbodyStart());

		html.append(snippets.body());

		html.append(snippets.tbodyEnd());

		html.append(snippets.footer());

		html.append(snippets.tableEnd());

		html.append(snippets.themeEnd());

		html.append(snippets.initJavascriptLimit());

		return html.toString();
	}


}
