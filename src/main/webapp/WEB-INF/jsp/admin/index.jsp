<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>jRecruiter - Administrative Area</title>

<h2>Administrative Area</h2>
<p class="info">
    <s:text name="admin.main.welcome"/>
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
          <legend>Manage Jobs Postings</legend>
                <ul>
                    <li><a href="${addJobUrl}" ><s:text name="admin.main.label.add.job"/></a></li>
                    <li><a href="${showJobsForAdminUrl}" ><s:text name="admin.main.label.edit.jobs"/></a></li>
                    <li><a href="${showStatisticsUrl}" ><s:text name="admin.main.label.show.statistics"/></a></li>
                    <li><a href="${showJobsNotAdminUrl}" ><s:text name="admin.main.label.view.jobs"/></a></li>
                </ul>
      </fieldset>
      <fieldset class="userTasks">
                <legend>User Task</legend>
                <ul>
                    <li><a href="${editUserUrl}" ><s:text name="admin.main.label.edit.registration"/></a></li>
                    <li><a href="${logoutUrl}" ><s:text name="admin.main.label.logout"/></a></li>
                </ul>
      </fieldset>
      <security:authorize ifAllGranted="ADMIN">
          <fieldset class="jRecruiterAdmin">
              <legend>jRecruiter Administration</legend>
              <ul>
                  <li><a href="${userListUrl}" ><s:text name="admin.main.label.edit.user"/></a></li>
                  <li><a href="${addUserUrl}" ><s:text name="admin.main.label.add.user"/></a></li>
                  <li><a href="${editSettingsUrl}" ><s:text name="admin.main.label.edit.settings"/></a></li>
                  <li><a href="${searchIndexUrl}" >Re-Index Search Index</a></li>
                  <li><a href="${setupDemoUrl}" >Setup Demo Jobs</a></li>
                  <li><a href="${loggingUrl}" >Logging Settings</a></li>
              </ul>
          </fieldset>
      </security:authorize>
    </div>

