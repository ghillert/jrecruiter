<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

  <c:if test="${param.status == 'error'}">
      <div class="error">
          <fmt:message key="jsp.login.error.errorMessage"/><br/>
      </div>
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
            <s:textfield id="j_password" name="j_password" required="true" maxlength="25" tabindex="2"
                             onblur="javascript:this.className='';"
                             onfocus="javascript:this.className='selected';"/>
          </div>
          <div class="submit">
                  <input type="submit" class="submitBtn" value="Login"/>
                  <input type="submit" class="submitBtn" onClick="location.href='<c:url value='/'/>'; return false;" value="Cancel"/>
          </div>

            <ul id="loginOptions" style="clear: left;">
                <li class="registration">
                    <img src="${ctx}/images/icons/crystal/add_user.png"/>
                    <s:url action="signup" namespace="registration" id="signupUrl"/>
                  <a href="${signupUrl}"><fmt:message key="link.login.addUser"/></a>
                </li>
                <li class="getPassword">
                  <img src="${ctx}/images/icons/crystal/mail_get.png"/>
                  <a href="<c:url value='/getPassword.html'/>"><fmt:message key="link.login.forgotYourPassword"/></a>
                </li>
            </ul>
      </fieldset>



  </form>

<script type="text/JavaScript" language="JavaScript">
<!--
document.getElementById('j_username').focus();
//-->
</script>
