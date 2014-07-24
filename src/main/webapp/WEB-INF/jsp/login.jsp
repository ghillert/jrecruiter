<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<c:if test="${param.status == 'error'}">
	<div class="error">
		<spring:message code="jsp.login.error.errorMessage" />
	</div>
</c:if>

<div id="tabs">
	<ul>
		<li><a href="#username-password-registration">Username/Password
				Login</a></li>
	</ul>
	<div id="username-password-registration">
		<form class="form-group" name="j_spring_security_check"
			id="j_acegi_security_check" method="POST" action="${ctx}/login.html"
			role="form" style="margin: 15px">
			<fieldset id="loginSection"
				style="margin-left: 1em; margin-right: 1em;">
				<legend>Login</legend>

				<div class="required form-group">
					<label for="j_username"><spring:message
							code="class.user.email" /></label> <input class="title form-control"
						type="text" id="j_username" name="username" maxlength="50"
						tabindex="1" />
				</div>
				<div class="required form-group">
					<label for="j_password"><spring:message
							code="class.user.password" /></label> <input class="title form-control"
						type="password" name="password" maxlength="120" tabindex="2" />
				</div>

				<button type="submit"
					value="<spring:message code="jsp.login.button.login"/>">
					<spring:message code="jsp.login.button.login" />
				</button>

				<ul id="loginOptions" style="clear: left;">
					<li class="registration"><img
						src="${ctx}/images/icons/crystal/add_user.png" /> <c:url
							value="/registration/signup.html" var="signupUrl"/>
						<a href="${signupUrl}"><spring:message
								code="jsp.login.addUser" /></a></li>
					<li class="getPassword"><img
						src="${ctx}/images/icons/crystal/mail_get.png" /> <c:url
							value="/get-password.html" var="getPasswordUrl"/>
						<a href="${getPasswordUrl}"><spring:message
								code="jsp.login.forgotYourPassword" /></a></li>
				</ul>
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
