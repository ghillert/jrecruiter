<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
    <div class="required">
       <label for="jobTitle"><spring:message code="jsp._ALL.job.field.jobTitle" /></label>
       <s:textfield name="job.jobTitle" id="jobTitle" size="40" tabindex="1"/>
    </div>
    <div class="required">
       <label for="businessName"><spring:message code="jsp._ALL.job.field.businessName" /></label>
       <s:textfield name="job.businessName" id="businessName" size="40" tabindex="2"/>
    </div>
    <div class="required">
       <label for="industry"><spring:message code="jsp._ALL.job.field.industry" /></label>
       <s:select name="job.industry.id" id="industry" tabindex="3"
                    list="industries" headerKey="" headerValue="%{getText('jsp._ALL.marked.fields.please.select')}"
                    listValue="name" listKey="id"/>
    </div>
    <div class="optional" id="industryOtherDiv" style="display: <s:property value="%{job.industry.id == '-16' ? 'block' : 'none'}"/>">
       <label for="industryOther">Other</label>
        <s:textfield name="job.industryOther" id="job.industryOther" size="40" tabindex="4"/>
    </div>
    <div class="optional">
       <label for="salary"><spring:message code="jsp._ALL.job.field.salary" /></label>
       <s:textfield name="job.salary" id="salary" size="11" tabindex="4"/>
    </div>

    <div class="required">
       <label for="offered By"><spring:message code="jsp._ALL.job.field.offered.by" /></label>
       <s:select name="job.offeredBy" id="offeredBy" tabindex="5"
                    list="offeredBySet" headerKey="" headerValue="%{getText('jsp._ALL.marked.fields.please.select')}"/>
    </div>