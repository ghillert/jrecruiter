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

<div  id="header_menu">
  <html:link action="adminMain" styleClass="button">
        <fmt:message key="all.back.to.welcome.page"/>
  </html:link>
</div>

<div id="main">

  <s:form  action="deleteUser">

      <s:if test="userList != null && userList.size > 0">
        <%@include file="/WEB-INF/jsp/user/userListTable.jsp"%>
      </s:if>
	  <s:else>
	  	   <fmt:message key="message.noAvailableJobs"/>
	  </s:else>

      <s:submit method="cancel" onclick="javascript:return checkDelete();" value="%{getText('jobposting.button.cancel')}"/>
      <s:if test="userList != null && userList.size > 0">
        <s:submit method="delete" onclick="javascript:return checkDelete();" value="%{getText('jobposting.button.delete')}"/>
      </s:if>
  </s:form>

</div>