<%@include file="/taglibs.jsp"%>


<div  id="header_menu">
  <html:link action="deleteJobPosting?method=listJobPostings" styleClass="button">
        <fmt:message key="all.back.to.welcome.page"/>
  </html:link>
</div>

<div id="main">

  <h2><bean:message key="jsp.editSettings.title"/></h2>
  <logic:messagesPresent>
      <div class="error">
          <html:messages id="error">
          <bean:write name="error"/><br/>
          </html:messages>
      </div>
  </logic:messagesPresent>

<br/>
The following Variables are available when creating the templates below.

  <table class="jobposting">
    <tr>
      <td>Job Id</td>
      <td>&#36;{jobId}</td>
    </tr>
    <tr>
      <td>Job Title</td>
      <td>&#36;{jobTitle}</td>
    </tr>
    <tr>
      <td>Business Location</td>
      <td>&#36;{businessLocation}</td>
    </tr>
    <tr>
      <td>Business Name</td>
      <td>&#36;{businessName}</td>
    </tr>
    <tr>
      <td>Description</td>
      <td>&#36;{description}</td>
    </tr>
    <tr>
      <td>Job Restrictions</td>
      <td>&#36;{jobRestrictions}</td>
    </tr>
    <tr>
      <td>Update Date</td>
      <td>&#36;{updateDate}</td>
    </tr>
    <tr>
      <td>Password</td>
      <td>&#36;{password}</td>
    </tr>
  </table>

  <html:form target="" action="/editSettings?method=editSettings" focus="jobTitle">
  <table class="jobposting">
      <tr>
          <td><bean:message key="jsp.editSettings.field.jobposting.to"/>*</td>
          <td>
              <html:text tabindex="1" onblur="javascript:this.className='';" style="width: 250px;"
                         onfocus="javascript:this.className='selected';" property="mailingListEmail" errorStyleClass="error"/>
          </td>
    </tr>
    <tr>
          <td><bean:message key="jsp.editSettings.field.jobposting.subject"/>*</td>
          <td>
              <html:text tabindex="2" property="mailingListSubject" onblur="javascript:this.className='';" style="width: 250px;"
                         onfocus="javascript:this.className='selected';" errorStyleClass="error"/>
          </td>
      </tr>
      <tr>
          <td><bean:message key="jsp.editSettings.field.jobposting.template"/></td>
          <td>
              <html:textarea tabindex="14" property="mailingListTemplate" style="width: 500px" rows="15"
                             onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"
                             errorStyleClass="error"></html:textarea>
          </td>
    <tr>
    <tr>
          <td><bean:message key="jsp.editSettings.field.password.subject"/></td>
          <td>
              <html:text tabindex="4" property="passwordSubject" onblur="javascript:this.className='';" style="width: 250px;"
                         onfocus="javascript:this.className='selected';" errorStyleClass="error"/>
          </td>
      </tr>
       <tr>
          <td><bean:message key="jsp.editSettings.field.password.template"/>*</td>
          <td>
              <html:textarea tabindex="14" property="passwordTemplate" style="width: 500px" rows="15"
                             onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"
                             errorStyleClass="error"></html:textarea></td>
      </tr>
      <tr>
          <td>&nbsp;</td>
          <td>
              <html:submit>
              <bean:message key="jobposting.button.save"/>
              </html:submit>
              <html:cancel>
              <bean:message key="jobposting.button.cancel"/>
              </html:cancel></td>
      </tr>
      <tr>
          <td>&nbsp; </td>
          <td>
              <bean:message key="jobposting.add.label.mandatory"/></td>
      </tr>
  </table>
  </html:form>
</div>