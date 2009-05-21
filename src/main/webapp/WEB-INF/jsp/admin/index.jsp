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
          <s:url action="index" id="adminIndexUrl" includeContext="false" includeParams="none"/>

          <s:url action="edit-user"             id="editUserUrl"/>
          <s:url action="show-jobs"             id="showJobsNotAdminUrl" namespace="/"
                 encode="true" escapeAmp="true" includeParams="none">
            <s:param name="backTo" value="%{#adminIndexUrl}"/>
          </s:url>
          <s:url action="show-jobs"             id="showJobsForAdminUrl"/>
          <s:url action="add-job"               id="addJobUrl"/>
          <s:url action="show-statistics"       id="showStatisticsUrl"/>
          <s:url action="show-users"            id="userListUrl"/>
          <s:url action="add-user"              id="addUserUrl"/>
          <s:url action="edit-settings"         id="editSettingsUrl"/>
          <s:url action="edit-job"              id="editJobUrl">
            <s:param name="show-jobs" value="true"/>
          </s:url>
          <s:url action="logout" id="logoutUrl"/>
          <s:url action="search-index" id="searchIndexUrl"/>
          <s:url action="setup-demo"            id="setupDemoUrl"/>
          <s:url action="logging" id="loggingUrl"/>
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
                  <li><a href="${userListUrl}" ><spring:message code="jsp.admin.index.label.edit.user"/></a></li>
                  <li><a href="${addUserUrl}" ><spring:message code="jsp.admin.index.label.add.user"/></a></li>
                  <li><a href="${editSettingsUrl}" ><spring:message code="jsp.admin.index.label.edit.settings"/></a></li>
                  <li><a href="${searchIndexUrl}" ><spring:message code="jsp.admin.index.label.reindex.search.index"/></a></li>
                  <li><a href="${setupDemoUrl}" ><spring:message code="jsp.admin.index.label.setup.demo.jobs"/></a></li>
                  <li><a href="${loggingUrl}" ><spring:message code="jsp.admin.index.label.logging.settings"/></a></li>
              </ul>
          </fieldset>
      </security:authorize>
    </div>

