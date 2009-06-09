<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><spring:message code="jsp.edit-user.title" /></h2>

    <s:form id="editUserForm">
    <s:hidden name="user.id"/>
    <fieldset>
        <div class="required">
            <label for="username"><spring:message code="class.user.username" />*</label>
            <s:textfield id="username" name="user.username" required="true" maxlength="25" tabindex="1"
                               onblur="javascript:this.className='';"
                               onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="required">
              <label for="password"><spring:message code="class.user.password" />*</label>
              <s:password  id="password" name="password" required="true" maxlength="25" tabindex="2"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="required">
              <label for="firstName"><spring:message code="class.user.firstName" />*</label>
              <s:textfield id="firstName" name="user.firstName" required="true" maxlength="50" tabindex="3"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="required">
              <label for="lastName"><spring:message code="class.user.lastName" />*</label>
              <s:textfield id="lastName" name="user.lastName" required="true" maxlength="50" tabindex="4"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="required">
              <label for="company"><spring:message code="class.user.company" />*</label>
              <s:textfield id="company" name="user.company" required="true" maxlength="50" tabindex="5"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="optional">
              <label for="phone"><spring:message code="class.user.phone" /></label>
              <s:textfield id="phone" name="user.phone" required="true" maxlength="25" tabindex="6"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="optional">
              <label for="fax"><spring:message code="class.user.fax" /></label>
              <s:textfield id="fax" name="user.fax" required="true" maxlength="25" tabindex="7"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="required">
              <label for="email"><spring:message code="class.user.email" />*</label>
              <s:textfield id="email" name="user.email" required="true" maxlength="25" tabindex="8"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        </fieldset>
        <fieldset>
            <div class="submit">
                <s:submit value="%{getText('jsp._ALL.button.submit')}" method="save"/>
                <s:submit value="%{getText('jsp._ALL.button.cancel')}" method="cancel"/>
            </div>
        </fieldset>
        <p style="clear: both;"><spring:message code="jsp._ALL.marked.fields.are.required"/></p>
</s:form>

<script type="text/javascript">
    $('username').focus();
</script>


