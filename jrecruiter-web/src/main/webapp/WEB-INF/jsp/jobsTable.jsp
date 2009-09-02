<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

   <jmesa:tableFacade
        id="jobsTable"
        items="${jobs}"
        limit="${limit}"
        var="job"
        stateAttr="restore"
        >
      <jmesa:htmlTable>
        <jmesa:htmlRow>
                <jmesa:htmlColumn  property="usesMap" title="&nbsp;" filterable="false">
                    <s:url id="jobDetailUrl" action="job-detail" includeParams="none">
                      <s:param name="jobId" value="%{#attr.job.id}"/>
                    </s:url>
                    <a title="Job Detail - ${job.jobTitle}" href="${jobDetailUrl}">
                        <img alt="<spring:message code="jsp.show.jobs.table.icon.alt.details" />" title="<spring:message code="jsp.show.jobs.table.icon.title.details"/>" src="${ctx}/images/icons/crystal/viewmag.png" style="border-width: 0;"/>
                    </a>
                </jmesa:htmlColumn>
                <jmesa:htmlColumn property="details" title="&nbsp;" filterable="false">
                    <s:if test="#attr.job.usesMap">
                        <img alt="<spring:message code="jsp.show.jobs.table.icon.alt.map" />" title="<spring:message code="jsp.show.jobs.table.icon.title.map" />" src="${ctx}/images/icons/crystal/globe.png" style="border-width: 0;"/>
                    </s:if>
                    <s:else>&nbsp;</s:else>
                </jmesa:htmlColumn>
                <jmesa:htmlColumn property="jobTitle"     titleKey="jsp.show.jobs.table.job.title" />
                <jmesa:htmlColumn property="businessName" titleKey="jsp.show.jobs.table.business.name"/>
                <jmesa:htmlColumn property="regionForDisplayFormatted"  titleKey="jsp.show.jobs.table.job.location" filterable="false" sortable="false"/>
                <jmesa:htmlColumn property="updateDate"   titleKey="jsp.show.jobs.table.date"         pattern="${datePattern}" cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
        </jmesa:htmlRow>
        </jmesa:htmlTable>
    </jmesa:tableFacade>