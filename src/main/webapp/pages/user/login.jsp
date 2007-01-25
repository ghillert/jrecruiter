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

  <c:if test="${param.status == 'error'}">
      <div class="error">
          <fmt:message key="jsp.login.error.errorMessage"/><br/>
      </div>
  </c:if>
  <form  name="j_acegi_security_check" method="POST" action="j_acegi_security_check">
      <table id="login">
          <tr>
              <td class="top">
                  <bean:message key="user.username" />
              </td>
              <td class="top">
                  <input type="text" name="j_username" tabindex="1" onblur="javascript:this.className='';"
                         onfocus="javascript:this.className='selected';"/>
              </td>
          </tr>
          <tr>
              <td>
                  <bean:message key="user.userPassword" />
              </td>
              <td>
                  <input type="password" name="j_password" tabindex="1" onblur="javascript:this.className='';"
                         onfocus="javascript:this.className='selected';"/>
              </td>
          </tr>
          <tr>
              <td>
             </td>
              <td>
                  <html:submit />&nbsp;
                  <input type="button" value="Cancel" onClick="location.href='<c:url value='/'/>';">
              </td>
          </tr>
          <tr>
              <td colspan="2">
                <br/>
                <ul>
                <li><html:link action="addUser"><bean:message key="link.login.addUser"/></html:link></li>
                <li><html:link action="forgotYourPassword"><bean:message key="link.login.forgotYourPassword"/></html:link></li>
              </ul>
              </td>
          </tr>
      </table>
  </form>
</div>
<script type="text/JavaScript" language="JavaScript">
<!--
document.forms["j_acegi_security_check"].elements["j_username"].focus();
//-->
</script>
