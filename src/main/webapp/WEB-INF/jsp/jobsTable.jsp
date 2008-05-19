<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

    <ajax:displayTag id="displayTagFrame" ajaxFlag="displayAjax" baseUrl="${ctx}/show-jobs-ajax.html" postFunction="initialize">
        <display:table name="jobs" pagesize="20" requestURI="" id="job" class="displaytag" export="false" defaultsort="4" defaultorder="descending">
        <display:column class="column1" titleKey="field.jobTitle" sortable="true" media="csv xml excel pdf" maxLength="30"/>

        <s:url id="jobDetailUrl" action="job-detail" namespace="/">
              <s:param name="jobId" value="%{#attr.job.id}"/>
        </s:url>

        <display:column class="column1" sortable="false" media="html" maxLength="100">
            <a title="Job Detail - ${job.jobTitle}" href="${jobDetailUrl}">
            <img alt="Job Detail" title="Job Detail - ${job.jobTitle}" src="${ctx}/images/icons/crystal/viewmag.png" style="border-width: 0;"/></a>
        </display:column>
        <display:column class="column3" property="jobTitle"     titleKey="field.jobTitle"     sortable="true" media="html csv xml excel pdf" maxLength="20"/>
        <display:column class="column4" property="businessName" titleKey="field.businessName" sortable="true" media="html csv xml excel pdf" maxLength="20"/>
        <display:column class="column5" property="region.name"  titleKey="field.location"     sortable="true" media="html csv xml excel pdf" maxLength="15"/>
        <display:column class="column6"                         titleKey="field.jobPostDate"  sortable="true" media="html csv xml excel pdf" sortProperty="updateDate">
            <fmt:formatDate value="${job.updateDate}" type="date" pattern="${datePattern}"/>
        </display:column>
        <display:column class="column1"sortable="false" media="html" maxLength="100" title="Uses Map">
            <s:if test="job.usesMap">
              <a title="Job Detail - ${job.jobTitle}" href="${jobDetailUrl}">
              <img alt="Job Detail" title="Job Detail - ${job.jobTitle}" src="${ctx}/images/icons/crystal/globe.png" style="border-width: 0;"/></a>
            </s:if>
            <s:else>&nbsp;</s:else>
        </display:column>
      </display:table>
    </ajax:displayTag>

