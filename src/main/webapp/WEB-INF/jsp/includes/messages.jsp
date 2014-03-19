<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<%-- Success Messages --%>
<s:if test="hasActionErrors() || hasFieldErrors()">
  <table class="errorMessages">
    <tr>
      <td>
      <ul>
        <s:iterator value="actionErrors">
          <li><s:property escape="false" /></li>
        </s:iterator>
        <s:iterator value="fieldErrors">
          <s:iterator value="value">
            <li><s:property escape="false" /></li>
          </s:iterator>
        </s:iterator>
      </ul>
      </td>
    </tr>
  </table>

  <script type="text/javascript">

  $(function() {
      $('table.errorMessages').width($('.content').width());
  });

  </script>

</s:if>

<s:if test="hasActionMessages()">
  <table class="successMessages">
    <tr>
      <td>
      <ul>
        <s:iterator value="actionMessages">
          <li><s:property /></li>
        </s:iterator>
      </ul>
      </td>
    </tr>
  </table>

 <script type="text/javascript">

  $(function() {
      $('table.successMessages').width($('.content').width());
  });

  </script>
</s:if>

