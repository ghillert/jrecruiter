<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

	<p><fmt:message key="welcome.introduction.text.top" /></p>
	<div>
		<img src="<c:url value="/images/atlanta.jpg"/>" class="welcomeMain" />
		<ul>
			<li>
				<a href="<c:url value='/showJobs.html'/>"><fmt:message key="link.showJobs"/></a>
			</li>
			<li>
				<a href="<c:url value='/admin/admin.html'/>"><fmt:message key="link.manageJobsAccount" /></a>
			</li>
			<li>
				<a href="<c:url value='/addUser.html'/>"><fmt:message key="link.createNewUser" /></a>
			</li>
		</ul>
	</div>          
	<p><fmt:message key="welcome.introduction.text.bottom" /></p>
	<p><fmt:message key="welcome.introduction.text.support" /></p>


