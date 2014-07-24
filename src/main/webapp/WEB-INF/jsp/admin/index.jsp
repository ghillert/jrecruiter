<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>jRecruiter - <spring:message code="jsp.admin.index.header"/></title>
<h2><spring:message code="jsp.admin.index.header"/></h2>
<p class="info">
    <spring:message code="jsp.admin.index.welcome"/>
    <security:authentication property="principal.firstName"/>
    <security:authentication property="principal.lastName"/> to the administrative area
    of jRecruiter. In this area you can post new jobs, change existing job postings
    and/or change your registration information.
</p>

    <div class="adminMain">
          <c:url value="/admin/index" var="adminIndexUrl"/>

          <c:url value="/admin/edit-user.html"             var="editUserUrl"/>
          <c:url value="/s/show-jobs?backTo=${adminIndexUrl}" var="showJobsNotAdminUrl"/>
          <c:url value="/admin/show-jobs.html"             var="showJobsForAdminUrl"/>
          <c:url value="/admin/add-job.html"               var="addJobUrl"/>
          <c:url value="/admin/show-statistics.html"       var="showStatisticsUrl"/>
          <c:url value="/admin/show-users.html"            var="userListUrl"/>
          <c:url value="/admin/add-user.html"              var="addUserUrl"/>
          <c:url value="/admin/edit-settings.html"         var="editSettingsUrl"/>
          <c:url value="/admin/edit-job.html?show-jobs=true" var="editJobUrl"/>

          <c:url value="/logout.html"                var="logoutUrl"/>
          <c:url value="/admin/search-index.html"          var="searchIndexUrl"/>
          <c:url value="/admin/setup-demo.html"            var="setupDemoUrl"/>
          <c:url value="/admin/logging.html"               var="loggingUrl"/>
      <fieldset class="manageJobPostings">
          <legend><spring:message code="jsp.admin.index.legend.manage.jobs"/></legend>
                <ul>
                    <li><a href="${addJobUrl}" ><spring:message code="jsp.admin.index.label.add.job"/></a></li>
                    <li><a href="${showJobsForAdminUrl}" ><spring:message code="jsp.admin.index.label.edit.jobs"/></a></li>
                    <li><a href="${showStatisticsUrl}" ><spring:message code="jsp.admin.index.label.show.statistics"/></a></li>
                    <li><a href="${showJobsNotAdminUrl}" ><spring:message code="jsp.admin.index.label.view.jobs"/></a></li>
                </ul>
      </fieldset>
      <fieldset class="userTasks">
                <legend><spring:message code="jsp.admin.index.legend.user.task"/></legend>
                <ul>
                    <li><a href="${editUserUrl}" ><spring:message code="jsp.admin.index.label.edit.registration"/></a></li>
                    <li><a href="${logoutUrl}" ><spring:message code="jsp.admin.index.label.logout"/></a></li>
                </ul>
      </fieldset>
      <security:authorize ifAllGranted="ADMIN">
          <fieldset class="jRecruiterAdmin">
              <legend><spring:message code="jsp.admin.index.legend.administration"/></legend>
              <ul>
                  <c:url value="/s/admin/systemInformation" var="systemInformationUrl"/>
                  <li><a href="#" id="systemInformationLink"><spring:message code="jsp.admin.index.label.system_information"/></a></li>
                  <li><a href="${userListUrl}" ><spring:message code="jsp.admin.index.label.edit.user"/></a></li>
                  <li><a href="${addUserUrl}" ><spring:message code="jsp.admin.index.label.add.user"/></a></li>
                  <li><a href="${editSettingsUrl}" ><spring:message code="jsp.admin.index.label.edit.settings"/></a></li>
                  <li><a href="${searchIndexUrl}" ><spring:message code="jsp.admin.index.label.reindex.search.index"/></a></li>
                  <li><a href="${setupDemoUrl}" ><spring:message code="jsp.admin.index.label.setup.demo.jobs"/></a></li>
                  <li><a href="${loggingUrl}" ><spring:message code="jsp.admin.index.label.logging.settings"/></a></li>
                  <c:url value="/s/admin/backup.xml" var="backupUrl"/>
                  <li><a href="${backupUrl}"><spring:message code="jsp.admin.index.label.backup_xml"/></a></li>
                  <c:url value="/s/admin/restore" var="restoreUrl"/>
                  <li><a href="${restoreUrl}"><spring:message code="jsp.admin.index.label.restore"/></a></li>
              </ul>
          </fieldset>
      </security:authorize>
    </div>

<div id="systemInformationBox" style="display: none;"></div>

<script type="text/javascript">
<!--

$(function() {

    $("#systemInformationLink").click(function() {

        $("#systemInformationBox").dialog({ width: 700, height: 600, title: '<spring:message code="jsp.admin.index.label.system_information"/>' });
        $("#systemInformationBox").dialog('open').load('${systemInformationUrl}');
        });

});

//-->
</script>

