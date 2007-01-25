<%@ include file="/includes/taglibs.jsp"%>

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
      </div>
  </logic:messagesPresent>

  <html:form target="" action="/emailPassword" focus="username">

  <table>
     <tr>
          <td colspan="2">
              <bean:message key="user.forgot.password.text"/>
          </td>
      </tr>
      <tr>
          <td><bean:message key="user.username" /></td>
          <td>
            <html:text tabindex="1" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" property="username" errorStyleClass="error"/>
          </td>
    </tr>
    <tr>
      <td >&nbsp;</td>
      <td colspan="2"><html:submit/> <html:cancel /></td>
    </tr>
  </table>

  </html:form>
</div>