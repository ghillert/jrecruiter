<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<script type="text/javascript" src="<c:url value='/js/passwordmeter.js'/>"></script>
<h2>Register a new user</h2>
<p class="info">Please fill out the form below to create an account for the
    application. The registration is only required if you want to post jobs. For
    just viewing currently available job postings, registration is not required.
</p>

    <s:form id="addUserForm" action="signup">

    <fieldset>
        <div class="required">
          <label for="username"><fmt:message key="user.username" />*</label>
          <s:textfield id="username" name="user.username" required="true" maxlength="25" tabindex="1"/>
        </div>
        <div class="required">
            <label for="password"><fmt:message key="user.password" />*</label>
            <s:password  id="password" name="password" required="true" maxlength="25" tabindex="2"/>
        </div>
        <div class="optional">
            <label>Password Strength</label>
            <span id="passwordStrength">Please insert a password</span>
        </div>
        <div class="required">
            <div style="margin-left: 173px; width: 150px; border: 1px solid black;height: 5px; overflow: hidden;"><div id="passwordStrengthBar" style="width: 0px; background-color: green; height: 5px; overflow: "></div></div>
        </div>
        <div class="required">
            <label for="password2"><fmt:message key="user.password" />*</label>
            <s:password  id="password2" name="password2" required="true" maxlength="25" tabindex="2"/>
        </div>

        <div class="required infoBox">
            <ol>
           <li>Make your password 8 characters or more</li>
           <li>Use mixed case letters (upper and lower case)</li>
           <li>Use more than one number</li>
           <li>Use special characters (!,@,#,$,%,^,&amp;,*,?,_,~)</li>
           <li>Use L33t</li>
           <li>Use a random password generator/password vault like Password Safe or pwsafe</li>
           <li>Use PasswordMaker</li>
            </ol>
        </div>

        <div class="required">
            <label for="firstName"><fmt:message key="user.firstName" />*</label>
            <s:textfield id="firstName" name="user.firstName" required="true" maxlength="50" tabindex="3"/>
        </div>
        <div class="required">
            <label for="lastName"><fmt:message key="user.lastName" />*</label>
            <s:textfield id="lastName" name="user.lastName" required="true" maxlength="50" tabindex="4"/>
        </div>
        <div class="required">
            <label for="company"><fmt:message key="user.company" />*</label>
            <s:textfield id="company" name="user.company" required="true" maxlength="50" tabindex="5"/>
        </div>
        <div class="optional">
            <label for="phone"><fmt:message key="user.phone" /></label>
            <s:textfield id="phone" name="user.phone" required="true" maxlength="25" tabindex="6"/>
        </div>
        <div class="optional">
            <label for="fax"><fmt:message key="user.fax" /></label>
            <s:textfield id="fax" name="user.fax" required="true" maxlength="25" tabindex="7"/>
        </div>
        <div class="required">
            <label for="email"><fmt:message key="user.email" />*</label>
            <s:textfield id="email" name="user.email" required="true" maxlength="25" tabindex="8"/>
        </div>
        </fieldset>
        <div style="margin: 0 auto 0 auto; width: 320px;">
            <script type="text/javascript"
                src="http://api.recaptcha.net/challenge?k=6LcqhAMAAAAAAKpIPOtIhizy6R-W1VU3Kccx8GIn">
            </script>

            <noscript>
                <iframe src="http://api.recaptcha.net/noscript?k=6LcqhAMAAAAAAKpIPOtIhizy6R-W1VU3Kccx8GIn"
                    height="300" width="500" frameborder="0"></iframe><br>
                <textarea name="recaptcha_challenge_field" rows="3" cols="40">
                </textarea>
                <input type="hidden" name="recaptcha_response_field"
                    value="manual_challenge">
            </noscript>
        </div>
        <fieldset>
          <div class="submit">
          <s:submit value="Submit" method="save"/><s:submit value="Cancel" method="cancel"/>
          </div>
        </fieldset>
        <p style="clear: both;"><fmt:message key="all.marked.fields.are.required"/></p>
</s:form>

<script type="text/javascript">

    jQuery(function() {

      jQuery(':input').bind('focus', function(event) { jQuery(event.target).addClass('selected'); });
      jQuery(':input').bind('blur', function(event) { jQuery(event.target).removeClass('selected'); });

      jQuery('#password').bind('keyup', function(event) {
      testPassword(document.getElementById('password').value); });
      jQuery('#username').focus();
    });

</script>

