<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<script type="text/javascript">

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

<s:form action="editJob">
    <s:if test="jobList != null && joblist.size() > 0">
        <%@include file="/WEB-INF/jsp/admin/joblistTable.jsp"%>
    </s:if>
    <s:else>
        <s:text name="message.noAvailableJobs"/>
    </s:else>

      <s:submit value="Cancel" method="cancel"/>
      <s:if test="jobList != null && joblist.size() > 0">
            <s:submit value="Delete" method="delete" onclick="javascript:return checkDelete();" />
      </s:if>
</s:form>

