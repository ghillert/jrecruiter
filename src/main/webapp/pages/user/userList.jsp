<%@ include file="/taglibs.jsp"%>

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

  <logic:messagesPresent message="true">
  <div class="success">
      <html:messages id="message" property="info" message="true">
      <bean:write name="message"/>
      <br/>
      </html:messages>
  </div>
  </logic:messagesPresent>


  <html:form  action="deleteUser">

      <logic:notEmpty name="userList">
        <%@include file="/pages/user/userListTable.jsp"%>
      </logic:notEmpty>

      <logic:empty name="userList">
                      <bean:message key="message.noAvailableJobs"/>
      </logic:empty>
        <br/><br/>
        <html:cancel><bean:message key="jobposting.button.cancel"/></html:cancel>
            <logic:notEmpty name="userList">
              <html:submit onclick="javascript:return checkDelete();"><bean:message key="jobposting.button.delete"/></html:submit>
            </logic:notEmpty>

  </html:form>

</div>