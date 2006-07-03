/**
 *
 */
package org.jrecruiter.webtier;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.jrecruiter.model.Job;

/**
 * @author admin
 *
 */
public class JobsForDisplaytagSorting implements PaginatedList {


    private List < Job > jobs;
    private int pageNumber = 1;
    private int objectsPerPage = 20;
    private int fullListSize = 30;
    private String sortCriterion = "";
    private SortOrderEnum sortOrder = SortOrderEnum.DESCENDING;
    private String searchId = null;
    /**
     *
     */
    public JobsForDisplaytagSorting() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.displaytag.pagination.PaginatedList#getList()
     */
    public List getList() {
        return this.jobs;
    }

    /* (non-Javadoc)
     * @see org.displaytag.pagination.PaginatedList#getPageNumber()
     */
    public int getPageNumber() {
        return this.pageNumber;
    }

    /* (non-Javadoc)
     * @see org.displaytag.pagination.PaginatedList#getObjectsPerPage()
     */
    public int getObjectsPerPage() {
        return this.objectsPerPage;
    }

    /* (non-Javadoc)
     * @see org.displaytag.pagination.PaginatedList#getFullListSize()
     */
    public int getFullListSize() {
        return this.fullListSize;
    }

    /* (non-Javadoc)
     * @see org.displaytag.pagination.PaginatedList#getSortCriterion()
     */
    public String getSortCriterion() {
        return this.sortCriterion;
    }

    /* (non-Javadoc)
     * @see org.displaytag.pagination.PaginatedList#getSortDirection()
     */
    public SortOrderEnum getSortDirection() {
        return this.sortOrder;
    }

    /* (non-Javadoc)
     * @see org.displaytag.pagination.PaginatedList#getSearchId()
     */
    public String getSearchId() {
        return this.searchId;
    }


    /**
     * @return Returns the jobs.
     */
    public final List<Job> getJobs() {
        return jobs;
    }

    /**
     * @param jobs The jobs to set.
     */
    public final void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    /**
     * @param fuleListSize The fuleListSize to set.
     */
    public final void setFullListSize(int fullListSize) {
        this.fullListSize = fullListSize;
    }

    /**
     * @param objectsPePage The objectsPePage to set.
     */
    public final void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }

    /**
     * @return Returns the sortOrder.
     */
    public final SortOrderEnum getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder The sortOrder to set.
     */
    public final void setSortOrder(SortOrderEnum sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * @param pageNumber The pageNumber to set.
     */
    public final void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * @param searchId The searchId to set.
     */
    public final void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    /**
     * @param sortCriterion The sortCriterion to set.
     */
    public final void setSortCriterion(String sortCriterion) {
        this.sortCriterion = sortCriterion;
    }









}
