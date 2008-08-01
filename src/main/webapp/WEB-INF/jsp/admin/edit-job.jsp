<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<style>
<!--
.statisticContainer { float: right; border: 1px solid #aaaaaa; width: 15em;}
.statisticContainer ul { list-style: none; padding: 0;}
.statisticContainer label { display: block; width: 10em;}
-->
</style>

<fieldset class="statisticContainer">
    <legend>Statistics</legend>
    <ul>
        <li><label>Page Visits:</label><s:property value="%{statistics.counter}" default="N/A"/></li>
        <li><label>Unique Visits:</label><s:property value="%{statistics.uniqueVisits}" default="N/A"/></li>
        <li><label>Last Access:</label><fmt:formatDate value="${statistics.lastAccess}" type="date" pattern="${datePattern}"/></li>
    </ul>
</fieldset>
<h2><fmt:message key="admin.edit.job.title"/></h2>

<s:form id="editJobForm">
    <s:hidden name="id"/>

    <jsp:include page="includes/job_header.jsp"/>
    <jsp:include page="includes/job_contact.jsp"/>
    <jsp:include page="includes/job_location.jsp"/>
    <jsp:include page="includes/job_mapDiv.jsp"/>
    <jsp:include page="includes/job_description.jsp"/>

    <fieldset>
      <div class="submit">
          <s:submit value="Submit" method="save" tabindex="23" cssClass="submitBtn"/><s:submit value="Cancel" method="cancel" tabindex="24" cssClass="submitBtn"/>
      </div>
    </fieldset>
    <fmt:message key="jobposting.add.label.mandatory" />

</s:form>
<jsp:include page="includes/job_javascript.jsp"/>


