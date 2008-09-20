<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2>Job Posting Statistics</h2>
<p class="info">
    This screen provides some basic statistical information about your jobs.
</p>

    <s:url id="chartJobsHitsAllUrl" namespace="/chart" action="viewJobChart">
        <s:param name="mode" value="all"/>
    </s:url>
    <s:url id="chartJobsHitsUniqueUrl" namespace="/chart" action="viewJobChart">
        <s:param name="mode" value="unique"/>
    </s:url>

    <div class="center">
        <img src="${chartJobsHitsAllUrl}" alt="Job statistics graph - Page Hits"/>
    </div>
    <div class="center">
        <img src="${chartJobsHitsUniqueUrl}" alt="Job statistics graph - Unique Hits"/>
    </div>

<div id="main">
  <s:form action="index">
      <s:submit value="%{getText('jobposting.button.back')}"/>
  </s:form>
</div>