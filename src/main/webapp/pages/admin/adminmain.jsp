<%@ include file="/includes/taglibs.jsp"%>

    <div  id="header_menu">
      <html:link action="backToWelcomePage" styleClass="button">
            <fmt:message key="all.back.to.welcome.page"/>
      </html:link>
      </div>

<div id="main">
  <logic:messagesPresent message="true">
        <div class="success">
                <html:messages id="message" property="info" message="true">
                     <bean:write name="message"/><br/>
                </html:messages>
        </div>
  </logic:messagesPresent>
    <fmt:message key="admin.main.welcome"/> ${pageContext.request.userPrincipal.principal.username}!

    <div class="adminMain">
      <ul>
          <li><html:link action="editUser" ><fmt:message key="admin.main.label.edit.registration"/></html:link></li>
          <li><html:link action="openEditJobPosting?method=listJobPostings" ><fmt:message key="admin.main.label.edit.jobs"/></html:link></li>
          <li><html:link action="openAddJobPosting?method=openAddJobPosting" ><fmt:message key="admin.main.label.add.job"/></html:link></li>
            <li><html:link action="showStatistics?method=showStatistics" ><fmt:message key="admin.main.label.show.statistics"/></html:link></li>
          <logic:present role="admin">
            <li><html:link action="userList" ><fmt:message key="admin.main.label.edit.user"/></html:link></li>
            <li><html:link action="addUser" ><fmt:message key="admin.main.label.add.user"/></html:link></li>
           <li><html:link action="editSettings?method=openEditSettings" ><fmt:message key="admin.main.label.edit.settings"/></html:link></li>
        </logic:present>
          <li><html:link action="showJobs" ><fmt:message key="admin.main.label.view.jobs"/></html:link></li>
          <li><html:link action="logout" ><fmt:message key="admin.main.label.logout"/></html:link></li>
      </ul>
    </div>
</div>
