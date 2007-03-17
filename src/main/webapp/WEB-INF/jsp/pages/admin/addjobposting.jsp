<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<div  id="header_menu">
  <html:link action="adminMain" styleClass="button">
        <fmt:message key="all.back.to.welcome.page"/>
  </html:link>
</div>
<div id="main">
  <h2><fmt:message key="admin.add.job.title" /></h2>
  <logic:messagesPresent>
  <div class="error">
   <html:messages id="error">
     <bean:write name="error"/><br />
   </html:messages>
  </div>
  </logic:messagesPresent>
  <html:form target="" action="/addJobPosting?method=addJobPosting" focus="jobTitle">
  <table class="jobposting">
    <tr>
      <td><fmt:message key="field.jobTitle" />*</td>
      <td>
        <html:text tabindex="1" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" property="jobTitle" errorStyleClass="error"/>
      </td>
      <td><fmt:message key="field.businessName" />*</td>
      <td><html:text tabindex="2" property="businessName" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
    </tr>
    <tr>
      <td><fmt:message key="field.location" /></td>
      <td><html:text tabindex="3" property="businessLocation" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
      <td><fmt:message key="field.address" /></td>
      <td><html:text tabindex="4" property="businessAddress1" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
    </tr>
    <tr>
      <td><fmt:message key="field.industry" /></td>
      <td><html:text tabindex="5" property="industry" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
      <td><fmt:message key="field.address" /></td>
      <td><html:text tabindex="6" property="businessAddress2" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
    </tr>
    <tr>
      <td><fmt:message key="field.salary" /></td>
      <td><html:text tabindex="7" property="salary" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
      <td><fmt:message key="field.city" /></td>
      <td><html:text tabindex="8" property="businessCity" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
    </tr>
    <tr>
      <td><fmt:message key="field.phone" /></td>
      <td><html:text tabindex="9" property="businessPhone" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
      <td><fmt:message key="field.state" /></td>
      <td><html:text tabindex="10" property="businessState" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
    </tr>
    <tr>
      <td><fmt:message key="field.email" />*</td>
      <td><html:text tabindex="11" property="businessEmail" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
      <td><fmt:message key="field.zip" /></td>
      <td><html:text tabindex="12" property="businessZip" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td><fmt:message key="field.website" /></td>
      <td><html:text tabindex="13" property="website" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td colspan="2"><fmt:message key="field.website.note" /></td>
    </tr>
    <tr>
      <td><fmt:message key="field.jobDescription" />*</td>
      <td colspan="3"><html:textarea tabindex="14" property="description" style="width: 500px" rows="15" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"></html:textarea></td>
    </tr>
    <tr>
      <td><fmt:message key="field.jobRestrictions" /></td>
      <td colspan="3"><html:textarea tabindex="15" property="jobRestrictions" style="width: 500px" rows="10" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"></html:textarea></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td colspan="3"><fmt:message key="jobposting.texarea.note" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><html:submit><fmt:message key="jobposting.button.add"/></html:submit> <html:cancel><fmt:message key="jobposting.button.cancel"/></html:cancel></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp; </td>
      <td colspan="3"><fmt:message key="jobposting.add.label.mandatory" /></td>
    </tr>
  </table>
  </html:form>
</div>