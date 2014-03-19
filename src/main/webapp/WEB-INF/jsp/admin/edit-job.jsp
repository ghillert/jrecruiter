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
    <legend><spring:message code="jsp.admin.edit-job.fieldset.legend.statistics"/></legend>
    <ul>
        <li><label><spring:message code="jsp.admin.edit-job.fieldset.label.page.visits"/>:</label><s:property value="%{job.statistic.counter}" default="N/A"/></li>
        <li><label><spring:message code="jsp.admin.edit-job.fieldset.label.last.access"/>:</label><fmt:formatDate value="${job.statistic.lastAccess}" type="date" pattern="${datePattern}"/></li>
    </ul>
</fieldset>
<h2><spring:message code="jsp.admin.edit-job.title"/></h2>

<s:form id="editJobForm">
    <s:hidden name="job.id"/>
    <jsp:include page="includes/job_header.jsp"/>
    <jsp:include page="includes/job_contact.jsp"/>
    <jsp:include page="includes/job_location.jsp"/>
    <jsp:include page="includes/job_mapDiv.jsp"/>
    <jsp:include page="includes/job_description.jsp"/>

    <fieldset>
      <div class="submit">
          <s:submit value="%{getText('jsp._ALL.button.submit')}" method="save"   tabindex="23" />
          <s:submit value="%{getText('jsp._ALL.button.cancel')}" method="cancel" tabindex="24"/>
      </div>
    </fieldset>
    <spring:message code="jsp._ALL.marked.fields.are.required"/>

</s:form>

<!-- Google Maps -->
	<script type="text/javascript"
	    src="http://maps.google.com/maps/api/js?sensor=false">
	</script>

<jsp:include page="includes/job_javascript.jsp"/>


