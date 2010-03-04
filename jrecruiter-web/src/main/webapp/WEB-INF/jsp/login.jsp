<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

    <script type="text/javascript" src="<c:url value='/js/lib/jquery/jquery.openid.js'/>"></script>

  <c:if test="${param.status == 'error'}">
    <table class="errorMessages">
      <tr>
        <td>
          <ul>
                <li><spring:message code="jsp.login.error.errorMessage"/></li>
          </ul>
        </td>
      </tr>
    </table>
  </c:if>


    <div id="tabs">
        <ul>
            <li><a href="#username-password-registration">Username/Password Login</a></li>
            <li><a href="#openid-registration">Login using OpenID</a></li>
        </ul>
        <div id="username-password-registration">
              <form  name="j_spring_security_check" id="j_acegi_security_check" method="POST" action="j_spring_security_check">
                  <fieldset id="loginSection" style="margin-left: 1em; margin-right: 1em;">
                      <legend>Login</legend>
                      <div class="required" style="margin-top: 2em;">
                        <label for="j_username"><spring:message code="class.user.email" /></label>
                        <s:textfield id="j_username" name="j_username" required="true" maxlength="50" tabindex="1"
                                         onblur="javascript:this.className='';"
                                         onfocus="javascript:this.className='selected';"/>
                      </div>
                      <div class="required">
                        <label for="j_password"><spring:message code="class.user.password" /></label>
                        <s:password id="j_password" name="j_password" required="true" maxlength="120" tabindex="2"
                                         onblur="javascript:this.className='';"
                                         onfocus="javascript:this.className='selected';"/>
                      </div>
                      <div class="submit">
                              <input type="submit" value="<spring:message code="jsp.login.button.login"/>"/>
                              <input type="submit" onClick="location.href='<c:url value='/'/>'; return false;" value="<spring:message code="jsp.login.button.cancel"/>"/>
                      </div>

                        <ul id="loginOptions" style="clear: left;">
                            <li class="registration">
                                <img src="${ctx}/images/icons/crystal/add_user.png"/>
                                <s:url action="signup" namespace="registration" id="signupUrl" includeParams="none"/>
                              <a href="${signupUrl}"><spring:message code="jsp.login.addUser"/></a>
                            </li>
                            <li class="getPassword">
                              <img src="${ctx}/images/icons/crystal/mail_get.png"/>
                              <s:url action="get-password" namespace="/" id="getPasswordUrl" includeParams="none"/>
                              <a href="${getPasswordUrl}"><spring:message code="jsp.login.forgotYourPassword"/></a>
                            </li>
                        </ul>
                  </fieldset>
              </form>
        </div>
        <div id="openid-registration" class="openIdLogo">
                <form class="openid" name='oidf' action='/jrecruiter-web/j_spring_openid_security_check' method='POST'>
                 <div><ul class="providers" style="display: none">
                      <li class="openid" title="OpenID"><img src="${ctx}/images/openid/openidW.png" alt="icon" />
                      <span><strong>http://{your-openid-url}</strong></span></li>
                      <li class="direct" title="Google">
                            <img src="${ctx}/images/openid/googleW.png" alt="icon" /><span>https://www.google.com/accounts/o8/id</span></li>
                      <li class="direct" title="Yahoo">
                            <img src="${ctx}/images/openid/yahooW.png" alt="icon" /><span>http://yahoo.com/</span></li>
                      <li class="username" title="AOL screen name">
                            <img src="${ctx}/images/openid/aolW.png" alt="icon" /><span>http://openid.aol.com/<strong>username</strong></span></li>
                      <li class="username" title="MyOpenID user name">
                            <img src="${ctx}/images/openid/myopenid.png" alt="icon" /><span>http://<strong>username</strong>.myopenid.com/</span></li>
                      <li class="username" title="Flickr user name">
                            <img src="${ctx}/images/openid/flickr.png" alt="icon" /><span>http://flickr.com/<strong>username</strong>/</span></li>
                      <li class="username" title="Technorati user name">
                            <img src="${ctx}/images/openid/technorati.png" alt="icon" /><span>http://technorati.com/people/technorati/<strong>username</strong>/</span></li>
                      <li class="username" title="Wordpress blog name">
                            <img src="${ctx}/images/openid/wordpress.png" alt="icon" /><span>http://<strong>username</strong>.wordpress.com</span></li>
                      <li class="username" title="Blogger blog name">
                            <img src="${ctx}/images/openid/blogger.png" alt="icon" /><span>http://<strong>username</strong>.blogspot.com/</span></li>
                      <li class="username" title="LiveJournal blog name">
                            <img src="${ctx}/images/openid/livejournal.png" alt="icon" /><span>http://<strong>username</strong>.livejournal.com</span></li>
                      <li class="username" title="ClaimID user name">
                            <img src="${ctx}/images/openid/claimid.png" alt="icon" /><span>http://claimid.com/<strong>username</strong></span></li>
                      <li class="username" title="Vidoop user name">
                            <img src="${ctx}/images/openid/vidoop.png" alt="icon" /><span>http://<strong>username</strong>.myvidoop.com/</span></li>
                      <li class="username" title="Verisign user name">
                            <img src="${ctx}/images/openid/verisign.png" alt="icon" /><span>http://<strong>username</strong>.pip.verisignlabs.com/</span></li>
                      </ul>
                </div>

                  <fieldset id="openIdLoginSection" class="openId" style="padding: 0; margin-left: 1em; margin-right: 1em; display: none;">
                          <legend>Login with OpenID Identity</legend>
                          <div class="openIdLogo" style="border: none;">
                              <div class="required" style="margin-top: 2em; margin-left: 1em;">
                                <label for="openid_username" style="width: 230px;">Enter your <span>Provider user name</span></label>
                                <s:textfield id="openid_username" name="openid_identifier" required="true" maxlength="80" tabindex="1" size="30"
                                                 onblur="javascript:this.className='';"
                                                 onfocus="javascript:this.className='selected';"/>
                                <br style="clear: both;"/>
                              </div>
                              <div class="submit">
                                      <input type="submit" value="<spring:message code="jsp.login.button.login"/>"/>
                                      <input type="submit" onClick="location.href='<c:url value='/'/>'; return false;" value="<spring:message code="jsp.login.button.cancel"/>"/>
                              </div>
                          </div>
                  </fieldset>
                  <fieldset style="padding: 0; margin-left: 1em; margin-right: 1em; display: none;">
                          <legend>Login with OpenID Identity</legend>
                          <div class="openIdLogo">
                              <div class="required" style="margin-top: 2em; margin-left: 1em;">
                                <label for="openid_identifier" style="width: 230px;">Enter your <a class="openid_logo" href="http://openid.net">OpenID</a></label>
                                <s:textfield id="openid_identifier" name="openid_identifier" required="true" maxlength="80" tabindex="1" size="30"
                                                 onblur="javascript:this.className='';"
                                                 onfocus="javascript:this.className='selected';"/>
                                <br style="clear: both;"/>
                              </div>
                              <div class="submit">
                                      <input type="submit" value="<spring:message code="jsp.login.button.login"/>"/>
                                      <input type="submit" onClick="location.href='<c:url value='/'/>'; return false;" value="<spring:message code="jsp.login.button.cancel"/>"/>
                              </div>
                          </div>
                  </fieldset>
                </form>
        </div>
    </div>

<script type="text/JavaScript" language="JavaScript">
<!--
$(function() {
    $("#tabs").tabs();
    $('#j_username').focus();
    $('table.errorMessages').width($('.content').width());

    $("form.openid:eq(0)").openid();

    $(".providers").fadeIn();

});
//-->
</script>
