<%@include file="/taglibs.jsp"%>

    <ajax:displayTag id="displayTagFrame" ajaxFlag="displayAjax" baseUrl="${ctx}/showJobs.do" postFunction="h()">
        <display:table name="JobList" pagesize="20" requestURI="" id="joblist" class="displaytag" export="false" sort="list" defaultsort="4" defaultorder="descending">
        <display:column class="column1" property="jobTitle" titleKey="field.jobTitle" sortable="true" href="showJobDetail.do" paramId="id" paramProperty="id" media="html csv xml excel pdf" maxLength="35"/>
        <display:column class="column2" property="businessName" titleKey="field.businessName" sortable="true" media="html csv xml excel pdf" maxLength="20"/>
        <display:column class="column3" property="businessLocation" titleKey="field.location" sortable="true" media="html csv xml excel pdf" maxLength="15"/>
        <display:column class="column4" titleKey="field.jobPostDate" sortable="true" media="html csv xml excel pdf" sortProperty="updateDate">
          <fmt:formatDate value="${joblist.updateDate}" type="date" pattern="${datePattern}"/>
        </display:column>
      </display:table>

    </ajax:displayTag>
    <script type="text/javascript">highlightTableRows("joblist");</script>
     <script type="text/javascript">

     function h() {

     window.setTimeout("highlightTableRows('joblist')", 1000);

     }</script>

