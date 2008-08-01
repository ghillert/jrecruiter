<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
    <fieldset style="clear: left;">
        <legend>
            <fmt:message key="jsp.addjobposting.job.fieldset.job.details" />
        </legend>
        <div class="required">
           <label for="description"><fmt:message key="field.jobDescription" /></label>
           <s:textarea name="description" id="description" rows="15" tabindex="21" cssStyle="width: 500px"/>
        </div>
        <div class="optional">
           <label for="jobRestrictions"><fmt:message key="field.jobRestrictions" /></label>
           <s:textarea name="jobRestrictions" id="jobRestrictions" rows="10" tabindex="22" cssStyle="width: 500px"/>
           <fmt:message key="jobposting.texarea.note" />
        </div>
    </fieldset>