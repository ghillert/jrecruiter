<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

    <fmt:message key="admin.main.welcome"/> ${pageContext.request.userPrincipal.principal.username}!

    <div class="adminMain">
      <ul>
          <li><a href="<c:url value='/editUser.html'/>" ><fmt:message key="admin.main.label.edit.registration"/></a></li>
          <li><a href="<c:url value='/openEditJobPosting?method=listJobPostings.html'/>" ><fmt:message key="admin.main.label.edit.jobs"/></a></li>
          <li><a href="<c:url value='/openAddJobPosting?method=openAddJobPosting.html'/>" ><fmt:message key="admin.main.label.add.job"/></a></li>
          <li><a href="<c:url value='/showStatistics?method=showStatistics.html'/>" ><fmt:message key="admin.main.label.show.statistics"/></a></li>
          <logic:present role="admin">
          <li><a href="<c:url value='/userList.html'/>" ><fmt:message key="admin.main.label.edit.user"/></a></li>
          <li><a href="<c:url value='/addUser.html'/>" ><fmt:message key="admin.main.label.add.user"/></a></li>
          <li><a href="<c:url value='/editSettings.html?method=openEditSettings'/>" ><fmt:message key="admin.main.label.edit.settings"/></a></li>
          </logic:present>
          <li><a href="<c:url value='/showJobs.html'/>" ><fmt:message key="admin.main.label.view.jobs"/></a></li>
          <li><a href="<c:url value='/logout.html'/>" ><fmt:message key="admin.main.label.logout"/></a></li>
      </ul>
    </div>

