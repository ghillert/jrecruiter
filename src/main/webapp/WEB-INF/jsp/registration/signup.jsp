<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><spring:message code="jsp.signup.title" /></h2>
<p class="info"><spring:message code="jsp.signup.text.introduction" /></p>

<form:form id="userForm" class="form-horizontal" role="form" method="post" modelAttribute="userForm" enctype="multipart/form-data">

		<form:hidden path="userAuthenticationType"/>

		<spring:bind path="firstName">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="email" class="col-lg-2 control-label"><spring:message code="class.user.email" />*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="email" id="email" maxlength="255" tabindex="1"/>
				<form:errors path="email" cssClass="fieldError"/>
			</div>
		</div>

		<c:if test="${userForm.userAuthenticationType.name() != 'OPEN_ID'}">
			<spring:bind path="password">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="password" class="col-lg-2 control-label"><spring:message code="class.user.password" />*</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="password" id="password" maxlength="255" tabindex="2"/>
					<form:errors path="password" cssClass="fieldError"/>
				</div>
			</div>
			<div class="form-group">
				<label><spring:message code="jsp.signup.label.password.strength" /></label>
				<div class="col-lg-10">
					<span id="passwordStrength"><spring:message code="jsp.signup.label.insert.password" /></span>
				</div>
			</div>

			<spring:bind path="userForm.password2">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="password2" class="col-lg-2 control-label"><spring:message code="class.user.password2" />*</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="password2" id="password2" maxlength="255" tabindex="3"/>
					<form:errors path="password2" cssClass="fieldError"/>
				</div>
			</div>
		</c:if>

		<spring:bind path="userForm.firstName">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="firstName" class="col-lg-2 control-label"><spring:message code="class.user.firstName" />*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="firstName" id="firstName" maxlength="255" tabindex="4"/>
				<form:errors path="firstName" cssClass="fieldError"/>
			</div>
		</div>
		<spring:bind path="userForm.lastName">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="lastName" class="col-lg-2 control-label"><spring:message code="class.user.lastName" />*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="lastName" id="lastName" maxlength="255" tabindex="5"/>
				<form:errors path="lastName" cssClass="fieldError"/>
			</div>
		</div>
		<spring:bind path="userForm.company">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="company" class="col-lg-2 control-label"><spring:message code="class.user.company" />*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="company" id="company" maxlength="255" tabindex="6"/>
				<form:errors path="company" cssClass="fieldError"/>
			</div>
		</div>
		<spring:bind path="userForm.phone">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="phone" class="col-lg-2 control-label"><spring:message code="class.user.phone" /></label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="phone" id="phone" maxlength="25" tabindex="7"/>
				<form:errors path="phone" cssClass="fieldError"/>
			</div>
		</div>

		<c:if test="${reCaptchaEnabled}">
			<label class="col-lg-2 control-label">Are you human?</label>
			<div class="col-lg-10" style="margin-bottom: 1em;">
				<c:out value="${reCaptchaHtml}" escapeXml="false"/>
			</div>
		</c:if>

		<div class="row">
			<p style="clear: both;"><spring:message code="jsp._ALL.marked.fields.are.required"/></p>
		</div>

		<c:if test="${userForm.userAuthenticationType.name() != 'OPEN_ID'}">
			<jsp:include page="../includes/password-tips.jsp"/>
		</c:if>

		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button type="submit" class="btn btn-default" lang="save" tabindex="8">Save</button>
				<button type="submit" class="btn btn-default" name="cancel" tabindex="9">Cancel</button>
			</div>
		</div>

</form:form>



