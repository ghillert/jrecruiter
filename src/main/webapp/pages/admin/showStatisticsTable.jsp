<%@include file="/taglibs.jsp"%>

<ajax:displayTag id="displayTagFrame" ajaxFlag="displayAjax" baseUrl="${ctx}/showStatistics.do" postFunction="h()">
    <display:table name="jobs" pagesize="20" requestURI="" id="jobs" class="displaytag" export="false" sort="list" defaultsort="3" defaultorder="descending">
      <display:column class="column1" property="jobTitle"               titleKey="jsp.showStatistics.job.title"    sortable="true" media="html csv xml excel pdf"/>
      <display:column class="column2" property="statistics.counter"     titleKey="jsp.showStatistics.hits"         sortable="true" media="html csv xml excel pdf"/>
      <display:column class="column3" property="statistics.uniqueVisits" titleKey="jsp.showStatistics.unique.hits" sortable="true" media="html csv xml excel pdf"/>
      <display:column class="column4"                                    titleKey="jsp.showStatistics.last.access" sortable="true" media="html csv xml excel pdf" sortProperty="statistics.lastAccess">
        <fmt:formatDate value="${jobs.statistics.lastAccess}" type="date" pattern="${datePattern}"/>
      </display:column>
    </display:table>
</ajax:displayTag>

<script type="text/javascript">highlightTableRows("jobs");</script>

<script type="text/javascript">

     function h() {

     window.setTimeout("highlightTableRows('jobs')", 1000);

     }
</script>

