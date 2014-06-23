<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><spring:message code="jsp.add-user.title" /></h2>

<s:form id="addUserForm">

        <fieldset>
            <div class="required">
                <label for="email"><spring:message code="class.user.email" />*</label>
                <s:textfield id="email" name="user.email" requiredLabel="true" maxlength="25" tabindex="1"/>
            </div>
            <div class="required">
                <label for="password"><spring:message code="class.user.password" />*</label>
                <s:password  id="password" name="password" requiredLabel="true" maxlength="25" tabindex="2"/>
            </div>
            <div class="optional">
                <label><spring:message code="jsp.signup.label.password.strength" /></label>
                <span id="passwordStrength"><spring:message code="jsp.signup.label.insert.password" /></span>
            </div>
            <div class="required">
                <div style="margin-left: 173px; width: 150px; border: 1px solid black;height: 5px; overflow: hidden;"><div id="passwordStrengthBar" style="width: 0px; background-color: green; height: 5px; overflow: "></div></div>
            </div>
            <div class="required">
                <label for="password2"><spring:message code="class.user.password2" />*</label>
                <s:password  id="password2" name="password2" requiredLabel="true" maxlength="25" tabindex="2"/>
            </div>

            <div class="required">
                <label for="firstName"><spring:message code="class.user.firstName" />*</label>
                <s:textfield id="firstName" name="user.firstName" requiredLabel="true" maxlength="50" tabindex="3"/>
            </div>
            <div class="required">
                <label for="lastName"><spring:message code="class.user.lastName" />*</label>
                <s:textfield id="lastName" name="user.lastName" requiredLabel="true" maxlength="50" tabindex="4"/>
            </div>
            <div class="required">
                <label for="company"><spring:message code="class.user.company" />*</label>
                <s:textfield id="company" name="user.company" requiredLabel="true" maxlength="50" tabindex="5"/>
            </div>
            <div class="optional">
                <label for="phone"><spring:message code="class.user.phone" /></label>
                <s:textfield id="phone" name="user.phone" requiredLabel="true" maxlength="25" tabindex="6"/>
            </div>
            <div class="optional">
                <label for="fax"><spring:message code="class.user.fax" /></label>
                <s:textfield id="fax" name="user.fax" requiredLabel="true" maxlength="25" tabindex="7"/>
            </div>
        </fieldset>
        <fieldset>
            <div class="submit">
                <s:submit value="%{getText('jsp._ALL.button.submit')}" method="save"/>
                <s:submit value="%{getText('jsp._ALL.button.cancel')}" method="cancel"/>
            </div>
        </fieldset>
        <p style="clear: both;"><spring:message code="jsp._ALL.marked.fields.are.required"/></p>
        <jsp:include page="../includes/password-tips.jsp"/>
</s:form>

<script type="text/javascript">
    $('#email').focus();
    $('#changePassword').click(function() {
        if ($('#changePassword').is(':checked')) {
            $('#changePasswordDiv').fadeIn('slow');
            $('#passwordStrengthBarContainer').width($('#password').width());
        } else {
            $('#changePasswordDiv').fadeOut('slow');
        }
    });
</script>


