<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<script type="text/javascript">
function openJob(id)
{
  loc="<c:url value='/openEditJobPosting.do?method=openEditJobPosting&id='></c:url>";
  window.location = loc+id;
}

function checkDelete(){

  if (confirm("<bean:message key='jsp.userlist.delete.confirm'/>"))
  {
    return true;
  }

  else {
     return false;
  }

}
</script>

  <s:form  action="deleteUser">

      <s:if test="userList != null && userList.size > 0">
        <%@include file="/WEB-INF/jsp/admin/user-list-table.jsp"%>
      </s:if>
	  <s:else>
	  	   <fmt:message key="message.noAvailableJobs"/>
	  </s:else>

      <s:submit method="cancel" onclick="javascript:return checkDelete();" value="%{getText('jobposting.button.cancel')}"/>
      <s:if test="userList != null && userList.size > 0">
        <s:submit method="delete" onclick="javascript:return checkDelete();" value="%{getText('jobposting.button.delete')}"/>
      </s:if>
  </s:form>
