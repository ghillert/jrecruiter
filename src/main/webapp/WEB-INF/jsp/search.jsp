<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<s:form action="search">

    <div class="required">
       <label for="keyword">Search terms</label>
       <s:textfield name="keyword" id="keyword" size="255" tabindex="1"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="submit">
        <s:submit value="Search" method="search" cssClass="submitBtn"/>
    </div>

      <display:table name="jobs" pagesize="15" requestURI="" id="job" class="displaytag" export="false" sort="list">
        <display:column class="joblist1" property="id" titleKey="field.jobNumber" sortable="true" media="html csv xml excel pdf"/>

        <s:url id="editJobPostingUrl" action="edit-job"><s:param name="id" value="#attr.row.job.id"/></s:url>

        <display:column class="joblist2" property="jobTitle" titleKey="field.jobTitle" sortable="true" href="${editJobPostingUrl}" paramId="id" paramProperty="id" media="html csv xml excel pdf"/>
        <display:column class="joblist3" property="region.name" titleKey="field.location" sortable="true" media="html csv xml excel pdf"/>
        <display:column class="joblist4" titleKey="field.jobPostDate" sortable="true" media="html csv xml excel pdf" sortProperty="updateDate">
          <fmt:formatDate value="${job.updateDate}" type="date" pattern="MM/dd/yy"/>
        </display:column>
      </display:table>
</s:form>

