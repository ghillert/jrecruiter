<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
    <fieldset style="clear: left;">
        <legend>
            <spring:message code="jsp.addjobposting.job.fieldset.job.details" />
        </legend>
        <div class="required">
           <label for="description"><spring:message code="jsp._ALL.job.field.jobDescription" /></label>
           <s:textarea name="job.description" id="description" rows="15" tabindex="21" cssStyle="width: 500px"/>
        </div>
        <div class="optional">
           <label for="jobRestrictions"><spring:message code="jsp._ALL.job.field.jobRestrictions" /></label>
           <s:textarea name="job.jobRestrictions" id="jobRestrictions" rows="10" tabindex="22" cssStyle="width: 500px"/>
           <spring:message code="jsp._ALL.job.label.texarea.note" />
        </div>
    </fieldset>