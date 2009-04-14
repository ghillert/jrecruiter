<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
   <fieldset style="clear: left;">
        <legend>
            <spring:message code="jsp.admin.add-job.fieldset.contact.information" />
        </legend>
           <div class="required">
               <label for="businessEmail"><spring:message code="jsp._ALL.job.field.email" /></label>
               <s:textfield name="job.businessEmail" id="businessEmail" size="21" tabindex="6"/>
           </div>
           <div class="optional">
               <label for="website"><spring:message code="jsp._ALL.job.field.website" /></label>
               <s:textfield name="job.website" id="website" size="21" tabindex="7" />
               <spring:message code="jsp._ALL.job.field.website_note" />
           </div>
           <div class="optional">
               <label for="businessPhone"><spring:message code="jsp._ALL.job.field.phone" /></label>
               <s:textfield name="job.businessPhone" id="businessPhone" size="11" tabindex="8"/>
           </div>
    </fieldset>
