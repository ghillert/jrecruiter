<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<style>
<!--
.statisticContainer { float: right; border: 1px solid #aaaaaa; width: 15em; margin-right: 0;}
.statisticContainer { background-color: #eeeeee; }
.statisticContainer ul { list-style: none; padding: 0;}
.statisticContainer label { display: block; width: 8em; margin-right: 0.1em; float: left;}
-->
</style>

<fieldset class="statisticContainer">
    <legend>Statistics</legend>
    <ul>
        <li><label>Page Visits:</label><s:property value="%{job.statistic.counter}" default="N/A"/></li>
        <li><label>Unique Visits:</label><s:property value="%{job.statistic.uniqueVisits}" default="N/A"/></li>
        <li><label>Last Access:</label><fmt:formatDate value="${job.statistic.lastAccess}" type="date" pattern="${datePattern}"/></li>
    </ul>
</fieldset>
<h2><fmt:message key="admin.edit.job.title"/></h2>

<s:form id="editJobForm">
    <s:hidden name="job.id"/>
    <jsp:include page="includes/job_header.jsp"/>
    <jsp:include page="includes/job_contact.jsp"/>
    <jsp:include page="includes/job_location.jsp"/>
    <jsp:include page="includes/job_mapDiv.jsp"/>
    <jsp:include page="includes/job_description.jsp"/>

    <fieldset>
      <div class="submit">
          <s:submit value="Submit" method="save" tabindex="23" /><s:submit value="Cancel" method="cancel" tabindex="24"/>
      </div>
    </fieldset>
    <fmt:message key="jobposting.add.label.mandatory" />

</s:form>
<jsp:include page="includes/job_javascript.jsp"/>


