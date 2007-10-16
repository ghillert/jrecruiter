<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<%-- Success Messages --%>
<s:if test="hasActionErrors()">
    <div class="error" id="errorMessages">
      <s:iterator value="actionErrors">
        <img src="<c:url value="/images/iconWarning.gif"/>"
            alt="<fmt:message key="icon.warning"/>" class="icon" />
        <s:property escape="false"/><br />
      </s:iterator>
   </div>
</s:if>

<%-- FieldError Messages - usually set by validation rules --%>
<s:if test="hasFieldErrors()">
    <div class="error" id="errorMessages">
      <s:iterator value="fieldErrors">
          <s:iterator value="value">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<fmt:message key="icon.warning"/>" class="icon" />
             <s:property escape="false"/><br />
          </s:iterator>
      </s:iterator>
   </div>
</s:if>

<s:if test="hasActionMessages()">
    <div class="error" id="errorMessages">
      <s:iterator value="actionMessages">
          <s:iterator value="value">
			<img src="<c:url value="/images/iconInformation.gif"/>"
                alt="<fmt:message key="icon.information"/>" class="icon" />
            <c:out value="${msg}" escapeXml="false"/><br />
          </s:iterator>
      </s:iterator>
   </div>
</s:if>

