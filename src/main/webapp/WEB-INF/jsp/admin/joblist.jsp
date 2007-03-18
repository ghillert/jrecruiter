<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<script type="text/javascript">
function openJob(id)
{
  loc="<c:url value='/openEditJobPosting.do?method=openEditJobPosting&id='></c:url>";
  window.location = loc+id;
}

function checkDelete(){

  if (confirm("<bean:message key='jsp.joblist.delete.confirm'/>"))
  {
    return true;
  }

  else {
     return false;
  }

}
</script>

<div  id="header_menu">
  <html:link action="adminMain" styleClass="button">
        <fmt:message key="all.back.to.welcome.page"/>
  </html:link>
</div>

<div id="main">
  <html:form target="" action="deleteJobPosting?method=deleteJobPosting">
    <logic:messagesPresent message="true">
      <div class="success">
              <html:messages id="message" property="info" message="true">
                   <bean:write name="message"/><br/>
              </html:messages>
      </div>
    </logic:messagesPresent>
    <logic:notEmpty name="JobList">
        <%@include file="/pages/admin/joblistTable.jsp"%>
    </logic:notEmpty>

    <logic:empty name="JobList">
                    <bean:message key="message.noAvailableJobs"/>
    </logic:empty>
      <br/><br/>
      <html:cancel><bean:message key="jobposting.button.cancel"/></html:cancel>
          <logic:notEmpty name="JobList">
            <html:submit onclick="javascript:return checkDelete();"><fmt:message key="jobposting.button.delete"/></html:submit>
          </logic:notEmpty>
  </html:form>
</div>

