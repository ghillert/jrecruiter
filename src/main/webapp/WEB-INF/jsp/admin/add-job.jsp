<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><fmt:message key="jsp.addjobposting.title" /></h2>

<s:form id="addJobForm">

    <jsp:include page="includes/job_header.jsp"/>
    <jsp:include page="includes/job_contact.jsp"/>
    <jsp:include page="includes/job_location.jsp"/>
    <jsp:include page="includes/job_mapDiv.jsp"/>
    <jsp:include page="includes/job_description.jsp"/>

    <fieldset>
      <div class="submit">
          <s:submit value="Submit" method="save" tabindex="23"/><s:submit value="Cancel" method="cancel" tabindex="24"/>
      </div>
    </fieldset>
    <fmt:message key="jobposting.add.label.mandatory" />
</s:form>
<jsp:include page="includes/job_javascript.jsp"/>

