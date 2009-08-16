<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><spring:message code="jsp.registration.account-verification.title" /></h2>

<s:form>
    <fieldset>
        <div class="required">
            <label for="validationKey"><spring:message code="jsp.registration.account-verification.form.code" />*</label>
            <s:textfield id="verificationKey" name="key" required="true" maxlength="50" tabindex="1" size="35"/>
        </div>
        <div class="submit">
            <s:submit value="%{getText('jsp.registration.account-verification.form.submit')}" method="process"/>
        </div>
    </fieldset>
</s:form>

<script type="text/javascript">

    $(function() {
      $('#verificationKey').focus();
    });

</script>
