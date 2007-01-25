<%@ include file="/includes/taglibs.jsp"%>

    <div  id="header_menu">
      <html:form style="margin-bottom:0;margin-top:0;" action="searchJobs" method="POST">
          <a href="<c:url value='/welcome.html'/>" class="button">
            <fmt:message key="all.back.to.welcome.page"/>
          </a>
        <html:text property="keyword" styleClass="headerForm" onblur="javascript:this.className='headerForm';"
                       onfocus="javascript:this.className='headerFormSelected';" />
        <a href="javascript:document.forms[0].submit();" class="button">Search</a>
        </html:form>
      </div>

<div id="main">

    <logic:messagesPresent>
        <div class="error">

            <html:messages id="error">
            <bean:write name="error"/><br/>
            </html:messages>
        </div><br/>
    </logic:messagesPresent>
<%@include file="/pages/jobsTable.jsp"%>

 <div id="jobDetailContainer" style="display: none;"></div>
</div>


