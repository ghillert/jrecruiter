<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2>Job Posting Statistics</h2>
<p class="info">
    This screen provides some basic statistical information about your jobs.
</p>

<s:form action="show-statistics">
    <s:hidden name="backTo"/>
    <div id="jobs">
        <jsp:include page="jobsTable.jsp"></jsp:include>
    </div>

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

    <s:submit value="%{getText('jobposting.button.back')}" name="back"/>
  </s:form>


  <script type="text/javascript">

  function onInvokeAction(id) {
      setExportToLimit(id, '');

      var parameterString = createParameterStringForLimit(id);

      jQuery.get('<s:property value="%{#tableUrlAjax}"/>?ajax=true&' + parameterString, function(data) {
          jQuery("#jobs").html(data);
      });
  }
  </script>