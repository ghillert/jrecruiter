<%@include file="/taglibs.jsp"%>

    <ajax:displayTag id="displayTagFrame" ajaxFlag="displayAjax" baseUrl="${ctx}/showJobs.do" postFunction="h()">
        <display:table name="JobList" pagesize="20" requestURI="" id="jobList" class="displaytag" export="false" sort="list" defaultsort="4" defaultorder="descending">
        <display:column class="column1" property="jobTitle" titleKey="field.jobTitle" sortable="true" href="showJobDetail.do" paramId="id" paramProperty="id" media="html csv xml excel pdf"/>
        <display:column class="column2" property="businessName" titleKey="field.businessName" sortable="true" media="html csv xml excel pdf"/>
        <display:column class="column3" property="businessLocation" titleKey="field.location" sortable="true" media="html csv xml excel pdf"/>
        <display:column class="column4" titleKey="field.jobPostDate" sortable="true" media="html csv xml excel pdf" sortProperty="updateDate">
          <fmt:formatDate value="${jobList.updateDate}" type="date" pattern="${datePattern}"/>
        </display:column>
        <display:column property="businessEmail" titleKey="field.email" sortable="true" media="csv xml excel pdf"/>
        <display:column property="description" titleKey="field.jobDescription" sortable="true" media="csv xml excel pdf"/>
      </display:table>

    </ajax:displayTag>
    <script type="text/javascript">highlightTableRows("jobList");</script>
     <script type="text/javascript">

     function h() {

     window.setTimeout("highlightTableRows('jobList')", 1000);

     }</script>

