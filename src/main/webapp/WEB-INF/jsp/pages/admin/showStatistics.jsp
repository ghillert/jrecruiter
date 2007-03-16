<%@ include file="/includes/taglibs.jsp"%>

    <div  id="header_menu">
      <html:link action="adminMain" styleClass="button">
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

    <%@include file="/pages/admin/showStatisticsTable.jsp"%>

    <div class="center">
        <img src="<c:url value='/showStatistics.do?method=chartJobsHits&mode=all'/>" alt="Job statistics graph - Page Hits"/>
    </div>
    <div class="center">
        <img src="<c:url value='/showStatistics.do?method=chartJobsHits&mode=unique'/>" alt="Job statistics graph - Unique Hits"/>
    </div>
</div>
