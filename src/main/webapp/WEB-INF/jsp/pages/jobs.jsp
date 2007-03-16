<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<a href="<c:url value='/rss.jobs'/>">
	<img src="<c:url value='/images/icons/feed-icon-28x28.png'/>" alt="Get the latest job postings as RSS feed" title="Get the latest job postings as RSS feed"/>    
</a>    
<%@include file="/WEB-INF/jsp/pages/jobsTable.jsp"%>

<div id="jobDetailContainer" style="display: none;"></div>


