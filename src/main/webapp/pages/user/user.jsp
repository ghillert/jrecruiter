<%@ include file="/taglibs.jsp"%>

<div  id="header_menu">
  <html:link action="adminMain" styleClass="button">
        <fmt:message key="all.back.to.welcome.page"/>
  </html:link>
</div>

<div id="main">


  <logic:messagesPresent  >
  <div class="error">
      <html:messages id="error">
         <bean:write name="error"/><br />
      </html:messages>
  </div>
  </logic:messagesPresent>

  <html:form target="" action="/userSubmit" focus="username" onsubmit="return validateUserForm(this);" >

  <table>
      <tr>
          <td><bean:message key="user.username" />*</td>
          <td>
              <html:hidden property="update" />
              <logic:equal name="UserForm" property="update" value="true">
                  <html:hidden property="username" />
                  <html:hidden property="dispatch" value="edit"/>
                  <bean:write name="UserForm" property="username" />
              </logic:equal>
              <logic:notEqual name="UserForm" property="update" value="true">
                  <html:hidden property="dispatch" value="add"/>
                  <html:text tabindex="1" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"
                      maxlength="25" property="username" errorStyleClass="error"/>
              </logic:notEqual>
          </td>
      </tr>
      <tr>
          <td><bean:message key="user.userPassword" />*</td>
          <td>
            <html:password tabindex="2" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"
                      maxlength="25" property="password" errorStyleClass="error"/>
          </td>
      </tr>
      <tr>
          <td><bean:message key="user.userPassword2" />*</td>
          <td>
            <html:password tabindex="3" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"
                      maxlength="25"  property="password2" errorStyleClass="error"/>
          </td>
      </tr>
      <tr>
          <td><bean:message key="user.firstName" />*</td>
          <td>
            <html:text tabindex="4" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"
                      maxlength="50" property="firstName" errorStyleClass="error"/>
          </td>
      </tr>
      <tr>
          <td><bean:message key="user.lastName" />*</td>
          <td>
            <html:text tabindex="5" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"
                      maxlength="50" property="lastName" errorStyleClass="error"/>
          </td>
      </tr>
      <tr>
          <td><bean:message key="user.company" /></td>
          <td>
            <html:text tabindex="5" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"
                      maxlength="50" property="company" errorStyleClass="error"/>
          </td>
      </tr>
      <tr>
          <td><bean:message key="user.phone" /></td>
          <td>
            <html:text tabindex="6" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"
                      maxlength="25" property="phone" errorStyleClass="error"/>
          </td>
      </tr>
      <tr>
          <td><bean:message key="user.fax" /></td>
          <td>
            <html:text tabindex="7" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"
                      maxlength="25" property="fax" errorStyleClass="error"/>
          </td>
      </tr>
      <tr>
          <td><bean:message key="user.email" />*</td>
          <td>
            <html:text tabindex="8" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"
                      maxlength="50" property="email" errorStyleClass="error"/>
          </td>
      </tr>

      <logic:equal name="UserForm" property="update" value="true">
      <tr>
          <td><bean:message key="user.registerDate" /></td>
          <td>
              <html:hidden property="registerDateDF" />
              <bean:write name="UserForm" property="registerDateDF" format="MM/dd/yyyy"/>
          </td>
      </tr>
      <tr>
          <td><bean:message key="user.updateDate" /></td>
          <td>
              <html:hidden property="updateDateDF" />
              <bean:write  name="UserForm" property="updateDateDF" format="MM/dd/yyyy"/>
          </td>
      </tr>
      </logic:equal>
      <tr>
          <td>&nbsp;</td>
          <td >
              <html:submit/> <html:cancel/>
          </td>
      </tr>
      <tr>
          <td colspan="2"><bean:message key="all.marked.fields.are.required"/></td>
      </tr>
  </table>

  </html:form>

</div>


<script type="text/javaccript">
<!--
document.forms[0].elements["password"].focus();
//-->
</script>

