<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

  <c:if test="${param.status == 'error'}">
    <table id="errorMessages">
      <tr>
        <td>
          <ul>
                <li><fmt:message key="jsp.login.error.errorMessage"/></li>
          </ul>
        </td>
      </tr>
    </table>
  </c:if>
  <form  name="j_spring_security_check" id="j_acegi_security_check" method="POST" action="j_spring_security_check">
      <fieldset id="loginSection">
          <legend>Login</legend>
          <div class="required">
            <label for="j_username"><fmt:message key="user.username" />*</label>
            <s:textfield id="j_username" name="j_username" required="true" maxlength="25" tabindex="1"
                             onblur="javascript:this.className='';"
                             onfocus="javascript:this.className='selected';"/>
          </div>
          <div class="required">
            <label for="j_password"><fmt:message key="user.password" />*</label>
            <s:password id="j_password" name="j_password" required="true" maxlength="25" tabindex="2"
                             onblur="javascript:this.className='';"
                             onfocus="javascript:this.className='selected';"/>
          </div>
          <div class="submit">
                  <input type="submit" value="Login"/>
                  <input type="submit" onClick="location.href='<c:url value='/'/>'; return false;" value="Cancel"/>
          </div>

            <ul id="loginOptions" style="clear: left;">
                <li class="registration">
                    <img src="${ctx}/images/icons/crystal/add_user.png"/>
                    <s:url action="signup" namespace="registration" id="signupUrl"/>
                  <a href="${signupUrl}"><fmt:message key="link.login.addUser"/></a>
                </li>
                <li class="getPassword">
                  <img src="${ctx}/images/icons/crystal/mail_get.png"/>
                  <s:url action="get-password" namespace="/" id="getPasswordUrl"/>
                  <a href="${getPasswordUrl}"><fmt:message key="link.login.forgotYourPassword"/></a>
                </li>
            </ul>
      </fieldset>



  </form>

<script type="text/JavaScript" language="JavaScript">
<!--
document.getElementById('j_username').focus();
//-->
</script>
