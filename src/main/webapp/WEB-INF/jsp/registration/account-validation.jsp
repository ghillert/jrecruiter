<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><spring:message code="jsp.registration.account-validation.title" /></h2>

<s:form>
    <fieldset>
        <div class="required">
            <label for="validationKey"><spring:message code="jsp.registration.account-validation.form.code" />*</label>
            <s:textfield id="validationKey" name="key" required="true" maxlength="50" tabindex="1"/>
        </div>
        <div class="submit">
            <s:submit value="%{getText('jsp.registration.account-validation.form.submit')}" method="process"/>
        </div>
    </fieldset>
</s:form>

<script type="text/javascript">

    $(function() {
      $('#validationKey').focus();
    });

</script>
