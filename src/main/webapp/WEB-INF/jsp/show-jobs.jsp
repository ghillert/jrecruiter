<title>Job List</title>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<c:url var="tableUrlAjax" value="/s/show-jobs-ajax"/>

<h2><spring:message code="jsp.show.jobs.title"/></h2>
<p class="info"><spring:message code="jsp.show.jobs.description"/></p>

<form:form name="jobsForm"  class="form-horizontal" role="form" method="post">

    <input type="hidden" name="backTo"/>
    <div id="jobsTableDiv">
        <jsp:include page="jobsTable.jsp"></jsp:include>
    </div>

  <script type="text/javascript">

  function onInvokeAction(id) {
      jQuery.jmesa.setExportToLimit(id, '');

      var parameterString = jQuery.jmesa.createParameterStringForLimit(id);

      jQuery.get('${tableUrlAjax}?ajax=true&restore=true&' + parameterString, function(data) {
    	  console.log("#" + id + 'Div');
          jQuery("#" + id + 'Div').html(data);
      });
  }
  </script>

  <table id="socialinks">
    <tr>
      <td>
        <a href='http://del.icio.us/post?url=http://www.ajug.org/jrecruiter/showJobs.html&title=AJUG+Job+Posting+Service' target='_blank'><img border='0' src='${ctx}/images/icons/delicious.png' alt='del.icio.us' title='del.icio.us' /></a>
      </td>
      <td>
        <a href='http://www.stumbleupon.com/submit?url=http://www.ajug.org/jrecruiter/showJobs.html&amp;title=AJUG+Job+Posting+Service' target='_blank'><img border='0' src='${ctx}/images/icons/stumbleupon.png' alt='stumbleupon' title='stumbleupon' /></a>
      </td>
      <td>
        <a href='http://www.google.com/bookmarks/mark?op=add&amp;hl=de&amp;bkmk=http://www.ajug.org/jrecruiter/showJobs.html&amp;annotation=&amp;labels=&amp;title=AJUG+Job+Posting+Service' target='_blank'><img border='0' src='${ctx}/images/icons/google.png' alt='google' title='google' /></a>
      </td>
      <td>
        <a href='http://myweb2.search.yahoo.com/myresults/bookmarklet?u=http://www.ajug.org/jrecruiter/showJobs.html&t=AJUG+Job+Posting+Service' target='_blank'><img border='0' src='${ctx}/images/icons/yahoomyweb.png' alt='YahooMyWeb' title='YahooMyWeb' /></a>
      </td>
    </tr>
  </table>

  <a href="${ctx}/index.html">Back</a>
</form:form>
