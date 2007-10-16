<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

    <fmt:message key="admin.main.welcome"/>
    <authz:authentication operation="firstName"/>
     <authz:authentication operation="lastName"/>!
    
    <div class="adminMain">
      <ul>
          <li><a href="<c:url value='/admin/editUser.html'/>" ><fmt:message key="admin.main.label.edit.registration"/></a></li>
          <li><a href="<c:url value='/admin/editJob.html'/>" ><fmt:message key="admin.main.label.edit.jobs"/></a></li>
          <li><a href="<c:url value='/admin/addJob.html'/>" ><fmt:message key="admin.main.label.add.job"/></a></li>
          <li><a href="<c:url value='/admin/showStatisticsChart.html'/>" ><fmt:message key="admin.main.label.show.statistics"/></a></li>
          
          <authz:authorize ifAllGranted="admin">
	          <li><a href="<c:url value='/admin/userList.html'/>" ><fmt:message key="admin.main.label.edit.user"/></a></li>
	          <li><a href="<c:url value='/admin/addUser.html'/>" ><fmt:message key="admin.main.label.add.user"/></a></li>
	          <li><a href="<c:url value='/admin/editSettings.html'/>" ><fmt:message key="admin.main.label.edit.settings"/></a></li>
		  </authz:authorize>  

          <li><a href="<c:url value='/showJobs.html?admin=true'/>" ><fmt:message key="admin.main.label.view.jobs"/></a></li>
          <li><a href="<c:url value='/logout.html'/>" ><fmt:message key="admin.main.label.logout"/></a></li>
      </ul>
    </div>

