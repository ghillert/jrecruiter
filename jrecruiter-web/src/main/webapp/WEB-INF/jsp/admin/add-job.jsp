<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><spring:message code="jsp.admin.add-job.title" /></h2>

<s:form id="addJobForm">

    <jsp:include page="includes/job_header.jsp"/>
    <jsp:include page="includes/job_contact.jsp"/>
    <jsp:include page="includes/job_location.jsp"/>
    <jsp:include page="includes/job_mapDiv.jsp"/>
    <jsp:include page="includes/job_description.jsp"/>

    <fieldset>
      <div class="submit">
          <s:submit value="%{getText('jsp._ALL.button.submit')}" method="save"   tabindex="23"/>
          <s:submit value="%{getText('jsp._ALL.button.cancel')}" method="cancel" tabindex="24"/>
      </div>
    </fieldset>
    <spring:message code="jsp._ALL.marked.fields.are.required" />
</s:form>

<!-- Google Maps -->
 <script
   src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=${apiKeysHolder.googleMapsKey}"
   type="text/javascript"></script>

<jsp:include page="includes/job_javascript.jsp"/>