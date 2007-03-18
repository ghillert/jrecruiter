<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

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
