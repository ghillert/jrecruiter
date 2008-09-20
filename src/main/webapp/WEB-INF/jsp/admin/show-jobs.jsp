<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<script type="text/javascript">
function openJob(id)
{
  loc="<c:url value='/openEditJobPosting.do?method=openEditJobPosting&id='></c:url>";
  window.location = loc+id;
}

function checkDelete(){

  if (confirm("<s:text name='jsp.joblist.delete.confirm'/>"))
  {
    return true;
  }

  else {
     return false;
  }

}
</script>

<h2>Job Administration</h2>
<p class="info">
    Located below are all jobs you have access to. You can either select a job for
    deletion or click on a job posted in order to modify it.
</p>

<div id="main">
  <s:form>
    <s:if test="jobs != null && jobs.size() > 0">
        <%@include file="/WEB-INF/jsp/admin/joblistTable.jsp"%>
    </s:if>
    <s:else>
        <s:text name="message.noAvailableJobs"/>
    </s:else>
      <br/><br/>
      <s:submit method="cancel" value="%{getText('jobposting.button.cancel')}"/>
      <s:if test="jobs != null && jobs.size() > 0">
          <s:submit method="delete" value="%{getText('jobposting.button.delete')}"
                    onclick="javascript:return checkDelete();"/>
      </s:if>
  </s:form>
</div>

