<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

   <jmesa:tableModel
        id="jobsTable"
        items="${jobs}"
        limit="${limit}"
        var="job"
        stateAttr="restore"
        >
      <jmesa:htmlTable>
        <jmesa:htmlRow>
                <jmesa:htmlColumn  property="usesMap" title="&nbsp;" filterable="false">
                    <c:url var="jobDetailUrl" value="/job-detail.html?jobId=${job.id}"/>
                    <a title="Job Detail - ${job.jobTitle}" href="${jobDetailUrl}">
                        <img alt="<spring:message code="jsp.show.jobs.table.icon.alt.details" />" title="<spring:message code="jsp.show.jobs.table.icon.title.details"/>" src="${ctx}/images/icons/crystal/viewmag.png" style="border-width: 0;"/>
                    </a>
                </jmesa:htmlColumn>
                <jmesa:htmlColumn property="details" title="&nbsp;" filterable="false">
                    <c:choose>
                        <c:when test="${job.usesMap}">
                            <img alt="<spring:message code="jsp.show.jobs.table.icon.alt.map" />" title="<spring:message code="jsp.show.jobs.table.icon.title.map" />" src="${ctx}/images/icons/crystal/globe.png" style="border-width: 0;"/>
                        </c:when>
                        <c:otherwise>&nbsp;</c:otherwise>
                    </c:choose>
                </jmesa:htmlColumn>
                <jmesa:htmlColumn property="jobTitle"     titleKey="jsp.show.jobs.table.job.title" />
                <jmesa:htmlColumn property="businessName" titleKey="jsp.show.jobs.table.business.name"/>
                <jmesa:htmlColumn property="regionForDisplayFormatted"  titleKey="jsp.show.jobs.table.job.location" filterable="false" sortable="false"/>
                <jmesa:htmlColumn property="updateDate"   titleKey="jsp.show.jobs.table.date"         pattern="${datePattern}" cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
        </jmesa:htmlRow>
        </jmesa:htmlTable>
    </jmesa:tableModel>