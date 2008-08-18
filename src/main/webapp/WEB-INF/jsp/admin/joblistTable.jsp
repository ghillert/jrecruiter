<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<s:url id="showJobsUrl" action="showJobs" method="ajaxCall">
</s:url>

<ajax:displayTag id="displayTagFrame" ajaxFlag="displayAjax" baseUrl="${ctx}/openEditJobPostingList.do" postFunction="h()">
      <display:table name="jobs" pagesize="15" requestURI="" id="job" class="displaytag" export="false" sort="list">
        <display:column class="joblist1" property="id" titleKey="field.jobNumber" sortable="true" media="html csv xml excel pdf"/>

        <s:url id="editJobPostingUrl" action="edit-job"><s:param name="id" value="#attr.row.job.id"/></s:url>

        <display:column class="joblist2" property="jobTitle" titleKey="field.jobTitle" sortable="true" href="${editJobPostingUrl}" paramId="id" paramProperty="id" media="html csv xml excel pdf"/>
        <display:column class="joblist3" property="region.name" titleKey="field.location" sortable="true" media="html csv xml excel pdf"/>
        <display:column class="joblist4" titleKey="field.jobPostDate" sortable="true" media="html csv xml excel pdf" sortProperty="updateDate">
          <fmt:formatDate value="${job.updateDate}" type="date" pattern="MM/dd/yy"/>
        </display:column>
        <display:column class="joblist5" titleKey="jsp.joblist.table.delete" sortable="false" media="html csv xml excel pdf" sortProperty="updateDate">
          <input type="checkbox" name="jobsToDelete[${job_rowNum}]" value="${job.id}" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"></input>
        </display:column>
      </display:table>
</ajax:displayTag>

<script type="text/javascript">

     function h() {

     window.setTimeout("highlightTableRows('job')", 1000);

     }
</script>
