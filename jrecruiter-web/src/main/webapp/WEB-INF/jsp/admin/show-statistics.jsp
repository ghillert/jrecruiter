<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2>Job Posting Statistics</h2>
<p class="info">
    This screen provides some basic statistical information about your jobs.
</p>

<s:form action="show-statistics">
    <s:hidden name="backTo"/>
    <div id="jobs">
        <jsp:include page="showStatisticsTable.jsp"></jsp:include>
    </div>

    <s:url id="chartJobsHitsAllUrl" namespace="/chart" action="viewJobChart">
    </s:url>

    <div class="center">
        <img src="${chartJobsHitsAllUrl}" alt="Job statistics graph - Hits"/>
    </div>
    <div class="submit">
        <s:submit value="%{getText('jsp._ALL.button.back')}" name="back" method="cancel"/>
    </div>
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