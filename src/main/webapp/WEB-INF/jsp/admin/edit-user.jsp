<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2>Edit user</h2>

    <s:form id="editUserForm">
    <s:hidden name="user.id"/>
    <fieldset>
        <div class="required">
            <label for="username"><fmt:message key="user.username" />*</label>
            <s:textfield id="username" name="user.username" required="true" maxlength="25" tabindex="1"
                               onblur="javascript:this.className='';"
                               onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="required">
              <label for="password"><fmt:message key="user.password" />*</label>
              <s:password  id="password" name="user.password" required="true" maxlength="25" tabindex="2"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="required">
              <label for="firstName"><fmt:message key="user.firstName" />*</label>
              <s:textfield id="firstName" name="user.firstName" required="true" maxlength="50" tabindex="3"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="required">
              <label for="lastName"><fmt:message key="user.lastName" />*</label>
              <s:textfield id="lastName" name="user.lastName" required="true" maxlength="50" tabindex="4"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="required">
              <label for="company"><fmt:message key="user.company" />*</label>
              <s:textfield id="company" name="user.company" required="true" maxlength="50" tabindex="5"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="optional">
              <label for="phone"><fmt:message key="user.phone" /></label>
              <s:textfield id="phone" name="user.phone" required="true" maxlength="25" tabindex="6"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="optional">
              <label for="fax"><fmt:message key="user.fax" /></label>
              <s:textfield id="fax" name="user.fax" required="true" maxlength="25" tabindex="7"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        <div class="required">
              <label for="email"><fmt:message key="user.email" />*</label>
              <s:textfield id="email" name="user.email" required="true" maxlength="25" tabindex="8"
                           onblur="javascript:this.className='';"
                           onfocus="javascript:this.className='selected';"/>
        </div>
        </fieldset>
        <fieldset>
            <div class="submit">
                <s:submit value="Submit" method="save"/><s:submit value="Cancel" method="cancel"/>
            </div>
        </fieldset>
        <p style="clear: both;"><fmt:message key="all.marked.fields.are.required"/></p>
</s:form>

<script type="text/javascript">
    $('username').focus();
</script>


