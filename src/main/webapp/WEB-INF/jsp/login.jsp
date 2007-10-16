<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

  <c:if test="${param.status == 'error'}">
      <div class="error">
          <fmt:message key="jsp.login.error.errorMessage"/><br/>
      </div>
  </c:if>
  <form  name="j_acegi_security_check" id="j_acegi_security_check" method="POST" action="j_acegi_security_check">
      <table id="login">
          <tr>
              <td class="top">
                  <fmt:message key="user.username" />
              </td>
              <td class="top">
                  <input type="text" name="j_username" tabindex="1" onblur="javascript:this.className='';"
                         onfocus="javascript:this.className='selected';"/>
              </td>
          </tr>
          <tr>
              <td>
                  <fmt:message key="user.password" />
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
                  <input type="button" value="Submit" onClick="$('j_acegi_security_check').submit();">&nbsp;
                  <input type="button" value="Cancel" onClick="location.href='<c:url value='/'/>';">
              </td>
          </tr>
          <tr>
              <td colspan="2">
                <br/>
                <ul>
                <li>
                    <s:url action="signup" namespace="registration" id="signupUrl"/>
                	<a href="${signupUrl}"><fmt:message key="link.login.addUser"/></a>
                </li>
                <li>
                	<a href="<c:url value='/getPassword.html'/>"><fmt:message key="link.login.forgotYourPassword"/></a>
                </li>
              </ul>
              </td>
          </tr>
      </table>
  </form>

<script type="text/JavaScript" language="JavaScript">
<!--
document.forms["j_acegi_security_check"].elements["j_username"].focus();
//-->
</script>
