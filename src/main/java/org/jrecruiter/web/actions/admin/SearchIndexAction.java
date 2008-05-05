package org.jrecruiter.web.actions.admin;

import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.interceptor.RetrieveMessages;
import org.texturemedia.smarturls.Result;

/**
 * Re-index the Hibernate Search Index
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@Result(name="success", location="index", type="redirectAction")
public class SearchIndexAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = 4614516114027504626L;

    /**
     *
     */
    @Override
    @RetrieveMessages
    public String execute() {
            jobService.reindexSearch();
            super.addActionMessage("Re-indexing of the search index triggered.");
            return SUCCESS;
    }
}
