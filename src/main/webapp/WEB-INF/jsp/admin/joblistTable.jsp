<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

   <jmesa:tableFacade
        id="tag"
        items="${jobs}"
        limit="${limit}"
        var="job"
        stateAttr="restore"
        >
      <jmesa:htmlTable>
        <jmesa:htmlRow>
                <jmesa:htmlColumn property="details" title="&nbsp;" filterable="false">
                    <s:url id="jobDetailUrl" action="edit-job" includeParams="false">
                      <s:param name="id" value="%{#attr.job.id}"/>
                    </s:url>
                    <s:url id="editJobPostingUrl" action="edit-job"><s:param name="id" value="#attr.row.job.id"/></s:url>
                    <a href="${jobDetailUrl}"><img alt="Edit this Job." title="Edit this Job." src="${ctx}/images/icons/crystal/edit.png" style="border-width: 0;"/></a>
                </jmesa:htmlColumn>
                <jmesa:htmlColumn property="jobTitle"     titleKey="jsp.show.jobs.job.title" />
                <jmesa:htmlColumn property="businessName" titleKey="jsp.show.jobs.business.name"/>
                <jmesa:htmlColumn property="region.name"  titleKey="jsp.show.jobs.job.location" filterable="false"/>
                <jmesa:htmlColumn property="updateDate"   titleKey="jsp.show.jobs.date" pattern="${datePattern}" cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
                <jmesa:htmlColumn>
                     <input type="checkbox" name="jobsToDelete[${job_rowNum}]" value="${job.id}"></input>
                </jmesa:htmlColumn>
        </jmesa:htmlRow>
        </jmesa:htmlTable>
    </jmesa:tableFacade>


