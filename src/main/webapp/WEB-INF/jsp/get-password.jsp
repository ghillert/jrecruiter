<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

  <h2>Forgot Password</h2>

    <s:form id="getPasswordForm" action="get-password">

    <p>
        <fmt:message key="user.forgot.password.text"/>
    </p>
        <fieldset>
          <div class="required">
              <label for="username"><fmt:message key="user.username" />*</label>
              <s:textfield id="username" name="user.username" required="true" maxlength="25" tabindex="1"/>
          </div>
        </fieldset>
        <fieldset>
            <div class="submit">
                <s:submit value="Submit" method="process"/><s:submit value="Cancel" method="cancel"/>
            </div>
        </fieldset>
        <p style="clear: both;"><fmt:message key="all.marked.fields.are.required"/></p>
</s:form>

<script type="text/javascript">
    jQuery(function() {

      jQuery(':input').bind('focus', function(event) { jQuery(event.target).addClass('selected'); });
      jQuery(':input').bind('blur', function(event) { jQuery(event.target).removeClass('selected'); });
      jQuery('#username').focus();

    });
</script>
