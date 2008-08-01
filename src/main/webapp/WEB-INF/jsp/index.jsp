<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
  <p><fmt:message key="welcome.introduction.text.top" /></p>
  <div>
    <img src="<c:url value="/images/atlanta.jpg"/>" class="welcomeMain" />

    <s:url id="jobCountUrl" namespace="/chart" action="viewJobCountChart"/>
    <ul class="menuList" style="width: 15em; float: left;">
      <li  style="width: 15em;">
        <s:url namespace="/" action="show-jobs" id="showJobsUrl"/>
        <a href="${showJobsUrl}"><span class="showJobs">&nbsp;</span><fmt:message key="link.showJobs"/></a>
      </li>
      <li  style="width: 15em;">
        <s:url namespace="/admin" action="index" id="adminMainUrl"/>
        <a href="${adminMainUrl}"><span class="manageAccount">&nbsp;</span><fmt:message key="link.manageJobsAccount" /></a>
      </li>
      <li  style="width: 15em;">
        <s:url namespace="/registration" action="signup" id="signupUrl"/>
        <a href="${signupUrl}"><span class="addUser">&nbsp;</span><fmt:message key="link.createNewUser" /></a>
      </li>
    </ul>
    <div style="margin-left: 0; margin-right: auto;">
        <img src="${jobCountUrl}" alt="Job statistics graph - Number of Jobs"/>
    </div>

  </div>
  <p style="clear: both;"><fmt:message key="welcome.introduction.text.bottom" /></p>
  <p><fmt:message key="welcome.introduction.text.support" /></p>


