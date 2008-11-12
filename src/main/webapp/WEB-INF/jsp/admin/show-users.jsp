<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<script type="text/javascript">

function checkDelete(userId){

  if (confirm("<s:text name='jsp.userlist.delete.confirm'/>")) {
    return true;
  }
  else {
     return false;
  }

}

</script>

  <s:form action="delete-user" >
      <s:if test="users != null && users.size > 0">
        <%@include file="/WEB-INF/jsp/admin/user-list-table.jsp"%>
      </s:if>
        <p>
            <s:text name="message.not.found.users"/>
        </p>

      <s:submit method="cancel" onclick="javascript:return checkDelete();" value="%{getText('jobposting.button.cancel')}"/>
      <s:if test="users != null && users.size > 0">
        <s:submit onclick="javascript:return checkDelete();" value="%{getText('jobposting.button.delete')}"/>
      </s:if>
  </s:form>
