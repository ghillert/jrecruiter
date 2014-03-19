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
 * @version $Id$
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
