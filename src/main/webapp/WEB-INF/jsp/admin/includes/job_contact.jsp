<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
   <fieldset style="clear: left;">
        <legend>
            <fmt:message key="jsp.addjobposting.fieldset.contact.information" />
        </legend>
           <div class="required">
               <label for="businessEmail"><fmt:message key="field.email" /></label>
               <s:textfield name="businessEmail" id="businessEmail" size="21" tabindex="6"/>
           </div>
           <div class="optional">
               <label for="website"><fmt:message key="field.website" /></label>
               <s:textfield name="website" id="website" size="21" tabindex="7" />
               <fmt:message key="field.website.note" />
           </div>
           <div class="optional">
               <label for="businessPhone"><fmt:message key="field.phone" /></label>
               <s:textfield name="businessPhone" id="businessPhone" size="11" tabindex="8"/>
           </div>
    </fieldset>
