<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

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

	<h2>Forgot Password</h2>
	<form:form commandName="command" method="post" action="getPassword.html" id="userForm">
	<form:errors path="*" cssClass="formError"/>

  <table>
     <tr>
          <td colspan="2">
              <fmt:message key="user.forgot.password.text"/>
          </td>
      </tr>
      <tr>
          <td><label for="username"><fmt:message key="user.username" /></label></td>
          <td>
            <form:input path="username" id="username" maxlength="25" tabindex="2" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"/>
        	<form:errors path="username" cssClass="fieldError"/>
          </td>
    </tr>
    <tr>
      <td >&nbsp;</td>
      <td colspan="2"><input type="submit" class="button" name="submit" value="Submit"/><input type="button" value="Cancel" onClick="location.href='<c:url value='/'/>';"></td>
    </tr>
  </table>

  </form:form>
</div>