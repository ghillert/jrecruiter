<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<%-- Success Messages --%>
<s:if test="hasActionErrors() || hasFieldErrors()">

    <ul id="errorMessages">
      <s:iterator value="actionErrors">
        <li><s:property escape="false"/></li>
      </s:iterator>
      <s:iterator value="fieldErrors">
          <s:iterator value="value">
             <li><s:property escape="false"/></li>
          </s:iterator>
      </s:iterator>
   </ul>
</s:if>

<s:if test="hasActionMessages()">
    <ul id="successMessages">
      <s:iterator value="actionMessages">
            <li><s:property /></li>
      </s:iterator>
   </ul>
</s:if>

