<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

    <ajax:displayTag id="displayTagFrame" ajaxFlag="displayAjax" baseUrl="${ctx}/showJobs.html" postFunction="h()">
        <display:table name="JobList" pagesize="20" requestURI="" id="joblist" class="displaytag" export="false" defaultsort="4" defaultorder="descending">
        <display:column class="column1" titleKey="field.jobTitle" sortable="true" media="csv xml excel pdf" maxLength="30"/>
        <display:column class="column1" sortProperty="field.jobTitle" sortable="true" media="html" maxLength="30">
        	<a href="#" onclick="javascript:showJobDetail('A');">${joblist.jobTitle}</a>
        </display:column>
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

