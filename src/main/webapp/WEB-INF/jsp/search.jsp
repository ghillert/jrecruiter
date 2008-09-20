<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<s:form action="search">

    <div class="required">
       <label for="keyword">Search terms</label>
       <s:textfield name="keyword" id="keyword" size="30" tabindex="1" cssClass="searchBox"/>
       <s:submit value="Search" method="search" cssStyle="margin-left: 1em;"/>
    </div>
    <p style="clear: both;"/>
    <s:if test="jobs != null && !jobs.empty">
      <display:table name="jobs" pagesize="15" requestURI="" id="job" class="displaytag" export="false" sort="list">
        <display:column class="joblist1" property="id" titleKey="field.jobNumber" sortable="true" media="html csv xml excel pdf"/>

        <s:url id="editJobPostingUrl" action="edit-job"><s:param name="id" value="#attr.row.job.id"/></s:url>

        <display:column class="joblist2" property="jobTitle" titleKey="field.jobTitle" sortable="true" href="${editJobPostingUrl}" paramId="id" paramProperty="id" media="html csv xml excel pdf"/>
        <display:column class="joblist3" property="region.name" titleKey="field.location" sortable="true" media="html csv xml excel pdf"/>
        <display:column class="joblist4" titleKey="field.jobPostDate" sortable="true" media="html csv xml excel pdf" sortProperty="updateDate">
          <fmt:formatDate value="${job.updateDate}" type="date" pattern="MM/dd/yy"/>
        </display:column>
      </display:table>
    </s:if>
</s:form>

<script type="text/javascript">

    jQuery(function() {

      jQuery(':input').bind('focus', function(event) { jQuery(event.target).addClass('selected'); });
      jQuery(':input').bind('blur', function(event) { jQuery(event.target).removeClass('selected'); });

      jQuery('#keyword').focus();

    });

</script>
