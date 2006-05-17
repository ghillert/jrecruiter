<%@include file="/taglibs.jsp"%>

    <div  id="header_menu">
      <html:form style="margin-bottom:0;margin-top:0;" action="searchJobs" method="POST">
          <html:link action="backToWelcomePage" styleClass="button">
            <fmt:message key="all.back.to.welcome.page"/>
          </html:link>
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
</div>

