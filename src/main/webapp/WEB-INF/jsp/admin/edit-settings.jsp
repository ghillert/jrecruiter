<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><fmt:message key="jsp.editSettings.title"/></h2>

<br/>
The following Variables are available when creating the templates below.

    <div class="required">
       <label>Job Id</label>
       &#36;{jobId}
    </div>
    <div class="required">
       <label>Job Title</label>
       &#36;{jobTitle}
    </div>
    <div class="required">
       <label>Business Location</label>
       &#36;{businessLocation}
    </div>
    <div class="required">
       <label>Business Name</label>
       &#36;{businessName}
    </div>
    <div class="required">
       <label>Description</label>
       &#36;{description}
    </div>
    <div class="required">
       <label>Job Restrictions</label>
       &#36;{jobRestrictions}
    </div>
    <div class="required">
       <label>Update Date</label>
       &#36;{updateDate}
    </div>
    <div class="required">
       <label>Password</label>
       &#36;{password}
    </div>

  <s:form action="edit-settings">
    <div class="required">
       <label for="mailingListEmail"><fmt:message key="jsp.editSettings.field.jobposting.to"/>*</label>
       <s:textfield name="mailingListEmail" id="mailingListEmail" size="11" tabindex="1"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="mailFrom"><fmt:message key="jsp.editSettings.field.mail.from"/>*</label>
       <s:textfield name="mailFrom" id="mailFrom" size="11" tabindex="2"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="mailingListSubject"><fmt:message key="jsp.editSettings.field.jobposting.subject"/>*</label>
       <s:textfield name="mailingListSubject" id="mailingListSubject" size="11" tabindex="3"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="mailingListTemplate"><fmt:message key="jsp.editSettings.field.jobposting.template"/></label>
       <s:textarea name="mailingListTemplate" id="mailingListTemplate" rows="10" tabindex="4"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="passwordSubject"><fmt:message key="jsp.editSettings.field.password.subject"/></label>
       <s:textfield name="passwordSubject" id="passwordSubject" size="11" tabindex="5"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="passwordTemplate"><fmt:message key="jsp.editSettings.field.password.template"/>*</label>
       <s:textarea  name="passwordTemplate" id="passwordTemplate" rows="10" tabindex="6"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <fieldset>
            <div class="submit">
                <s:submit value="%{getText('jobposting.button.save')}"   method="save"/>
                <s:submit value="%{getText('jobposting.button.cancel')}" method="cancel"/>
            </div>
    </fieldset>
    <fmt:message key="jobposting.add.label.mandatory"/>
  </s:form>
