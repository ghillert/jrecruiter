<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><spring:message code="jsp.edit-settings.title"/></h2>

<p class="info"><spring:message code="jsp.edit-settings.introduction.text" /></p>

    <div class="required">
       <label><spring:message code="jsp.edit-settings.label.variable.job.id"/></label>
       &#36;{jobId}
    </div>
    <div class="required">
       <label><spring:message code="jsp.edit-settings.label.variable.job.title"/></label>
       &#36;{jobTitle}
    </div>
    <div class="required">
       <label><spring:message code="jsp.edit-settings.label.variable.job.business.location"/></label>
       &#36;{businessLocation}
    </div>
    <div class="required">
       <label><spring:message code="jsp.edit-settings.label.variable.job.business.name"/></label>
       &#36;{businessName}
    </div>
    <div class="required">
       <label><spring:message code="jsp.edit-settings.label.variable.job.description"/></label>
       &#36;{description}
    </div>
    <div class="required">
       <label><spring:message code="jsp.edit-settings.label.variable.job.restrictions"/></label>
       &#36;{jobRestrictions}
    </div>
    <div class="required">
       <label><spring:message code="jsp.edit-settings.label.variable.job.update.date"/></label>
       &#36;{updateDate}
    </div>
    <div class="required">
       <label><spring:message code="jsp.edit-settings.label.variable.job.password"/></label>
       &#36;{password}
    </div>

  <s:form action="edit-settings">
    <div class="required">
       <label for="mailingListEmail"><spring:message code="jsp.edit-settings.field.jobposting.to"/>*</label>
       <s:textfield name="mailingListEmail" id="mailingListEmail" size="11" tabindex="1"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="mailFrom"><spring:message code="jsp.edit-settings.field.mail.from"/>*</label>
       <s:textfield name="mailFrom" id="mailFrom" size="11" tabindex="2"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="mailingListSubject"><spring:message code="jsp.edit-settings.field.jobposting.subject"/>*</label>
       <s:textfield name="mailingListSubject" id="mailingListSubject" size="11" tabindex="3"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="mailingListTemplate"><spring:message code="jsp.edit-settings.field.jobposting.template"/></label>
       <s:textarea name="mailingListTemplate" id="mailingListTemplate" rows="10" tabindex="4"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="passwordSubject"><spring:message code="jsp.edit-settings.field.password.subject"/></label>
       <s:textfield name="passwordSubject" id="passwordSubject" size="11" tabindex="5"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="passwordTemplate"><spring:message code="jsp.edit-settings.field.password.template"/>*</label>
       <s:textarea  name="passwordTemplate" id="passwordTemplate" rows="10" tabindex="6"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <fieldset>
            <div class="submit">
                <s:submit value="%{getText('jsp._ALL.button.submit')}"   method="save"/>
                <s:submit value="%{getText('jsp._ALL.button.cancel')}"   method="cancel"/>
            </div>
    </fieldset>
    <p style="clear: both;"><spring:message code="jsp._ALL.marked.fields.are.required"/></p>
  </s:form>
