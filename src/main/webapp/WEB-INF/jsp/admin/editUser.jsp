<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

	<h2><fmt:message key="admin.main.label.edit.registration"/></h2>
	<form:form method="post" id="userForm">
	<form:errors path="*" cssClass="formError"/>

  <table class="default">
      <tr>
          <td>
          	<label for="username"><fmt:message key="user.username" /></label>*
          </td>
          <td>        
          	<form:input  path="username" id="username" maxlength="25" tabindex="1" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"/>
        	<form:errors path="username" cssClass="fieldError"/>
          </td>
      </tr>
      <tr>
      		<td>
          	<label for="password"><fmt:message key="user.password" /></label>*
          </td>
          <td>        
          	<form:input  path="password" id="password" maxlength="25" tabindex="2" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"/>
        	<form:errors path="password" cssClass="fieldError"/>
          </td>
      </tr>      
      <tr>
      <td>
          	<label for="firstName"><fmt:message key="user.firstName" /></label>*
          </td>
          <td>        
          	<form:input  path="firstName" id="firstName" maxlength="50" tabindex="3" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"/>
        	<form:errors path="firstName" cssClass="fieldError"/>
          </td>
      </tr>      
      <tr>
      <td>
          	<label for="lastName"><fmt:message key="user.lastName" /></label>*
          </td>
          <td>        
          	<form:input  path="lastName" id="lastName" maxlength="50" tabindex="4" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"/>
        	<form:errors path="lastName" cssClass="fieldError"/>
          </td>
      </tr>
      <tr>
      <td>
          	<label for="company"><fmt:message key="user.company" /></label>*
          </td>
          <td>        
          	<form:input  path="company" id="company" maxlength="50" tabindex="5" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"/>
        	<form:errors path="company" cssClass="fieldError"/>
          </td>
      </tr>
      <tr>
      <td>
          	<label for="phone"><fmt:message key="user.phone" /></label>*
          </td>
          <td>        
          	<form:input  path="phone" id="phone" maxlength="25" tabindex="6" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"/>
        	<form:errors path="phone" cssClass="fieldError"/>
          </td>
      </tr>
      <tr>
      <td>
          	<label for="fax"><fmt:message key="user.fax" /></label>*
          </td>
          <td>        
          	<form:input  path="fax" id="fax" maxlength="25" tabindex="7" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"/>
        	<form:errors path="fax" cssClass="fieldError"/>
          </td>
      </tr>
      <tr>
      <td>
          	<label for="email"><fmt:message key="user.email" /></label>*
          </td>
          <td>        
          	<form:input  path="email" id="email" maxlength="50" tabindex="8" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"/>
        	<form:errors path="email" cssClass="fieldError"/>
          </td>
      </tr>      

      <tr>
          <td>&nbsp;</td>
          <td >
              <input type="submit" class="button" name="add" value="Add"/><input type="submit" class="button" name="cancel" value="Cancel"/>
          </td>
      </tr>
      <tr>
          <td colspan="2"><fmt:message key="all.marked.fields.are.required"/></td>
      </tr>
  </table>

  </form:form>

<script type="text/javaccript">
<!--
document.forms[0].elements["password"].focus();
//-->
</script>

