<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
    <div class="required">
       <label for="jobTitle"><fmt:message key="field.jobTitle" /></label>
       <s:textfield name="jobTitle" id="jobTitle" size="40" tabindex="1"/>
    </div>
    <div class="required">
       <label for="businessName"><fmt:message key="field.businessName" /></label>
       <s:textfield name="businessName" id="businessName" size="40" tabindex="2"/>
    </div>
    <div class="required">
       <label for="industry"><fmt:message key="field.industry" /></label>
       <s:select name="industry.id" id="industry" tabindex="3"
                    list="industries" headerKey="" headerValue="Please select an industry."
                    listValue="name" listKey="id"/>
    </div>
    <div class="optional">
       <label for="salary"><fmt:message key="field.salary" /></label>
       <s:textfield name="salary" id="salary" size="11" tabindex="4"/>
    </div>

    <div class="required">
       <label for="offered By"><fmt:message key="field.offered.by" /></label>
       <s:select name="offeredBy" id="offeredBy" tabindex="5"
                    list="offeredBySet" headerKey="" headerValue="Please select..."/>
    </div>