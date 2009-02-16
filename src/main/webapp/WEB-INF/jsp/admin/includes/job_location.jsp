<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
    <fieldset style="clear: left;">
        <legend>
            <spring:message code="jsp.admin.add-job.job.fieldset.job.location" />
        </legend>
        <div class="required">
           <label for="region"><spring:message code="jsp._ALL.job.field.location" /></label>
           <s:select name="job.region.id" id="region" tabindex="9"
                        list="regions" headerKey="" headerValue="%{getText('jsp._ALL.marked.fields.please.select')}"
                        listValue="name" listKey="id"/>
        </div>
      <s:set name="displayRegionOtherDiv" value="%{job.industry.id == -16 ? 'block' : 'none'}"/>
      <div class="required" id="regionOtherDiv" style="display: <s:property value="%{job.region.id == '-1' ? 'block' : 'none'}"/>">
         <label for="regionOther">&nbsp;</label>
          <s:textfield name="job.regionOther" id="job.regionOther" size="40" tabindex="4"/>
      </div>
        <div class="optional">
           <label for="businessAddress1"><spring:message code="jsp._ALL.job.field.address" /></label>
           <s:textfield name="job.businessAddress1" id="businessAddress1" size="22" tabindex="10"/>
        </div>
        <div class="optional">
           <label for="businessAddress2"><spring:message code="jsp._ALL.job.field.address" /></label>
           <s:textfield name="job.businessAddress2" id="businessAddress2" size="22" tabindex="11"/>
        </div>
        <div class="optional">
           <label for="businessCity"><spring:message code="jsp._ALL.job.field.city" /></label>
           <s:textfield name="job.businessCity" id="businessCity" size="11" tabindex="12"/>
        </div>
        <div class="optional">
           <label for="businessState"><spring:message code="jsp._ALL.job.field.state" /></label>
           <s:textfield name="job.businessState" id="businessState" size="11" tabindex="13"/>
        </div>

        <div class="optional">
           <label for="businessZip"><spring:message code="jsp._ALL.job.field.zip" /></label>
           <s:textfield name="job.businessZip" id="businessZip" size="11" tabindex="14"/>
        </div>
    </fieldset>