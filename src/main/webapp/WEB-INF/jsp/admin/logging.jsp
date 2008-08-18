<%@ page import="org.apache.log4j.*" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2>Logging Settings</h2>

<h3>Log Files</h3>

<table style="width: 100%;">
    <tr>
        <th>File Name</th><th>File Size</th><th>Last Updated</th><th>&nbsp;</th>
        <s:iterator id="logFileInfos" value="logFileInfos">
            <tr>
                <td><s:property value="%{#logFileInfos.fileName}"/></td>
                <td><s:property value="%{#logFileInfos.fileSize/1024}"/> Kb</td>
                <td><s:property value="%{#logFileInfos.fileLastChanged}"/></td>
                <td>
                <s:url id="downLoadUrl" action="downloadLogFile">
                    <s:param name="fileName" value="%{#logFileInfos.fileName}"/>
                </s:url>
                <a href="<s:property value="#downLoadUrl"/>"><img src="${ctx}/images/icons/crystal/viewmag.png"/></a></td>
            </tr>
         </s:iterator>
</table>

<c:set var="rootLogger" value="<%= Logger.getRootLogger() %>"/>
<form>
    <table style="width: 100%;">
    <tr><th>Level</th><th>Logger</th><th>Set New Level</th></tr>
    <tr><td>${rootLogger.level}</td><td>${rootLogger.name}</td><td>
        <c:forTokens var="level" delims="," items="DEBUG,INFO,WARN,ERROR,OFF">
        <s:url id="loggingUrl" includeParams="none">
            <s:param name="level" value="%{#attr.level}"/>
        </s:url>
            <a href="${loggingUrl}">${level}</a>
        </c:forTokens>
    </td></tr>
    <c:forEach var="logger" items="${rootLogger.loggerRepository.currentLoggers}">
        <c:if test="${!empty logger.level.syslogEquivalent || param.showAll}">
            <tr><td>${logger.level}</td><td>${logger.name}</td><td>
            <c:forTokens var="level" delims="," items="DEBUG,INFO,WARN,ERROR,OFF">
                <s:url id="loggingUrl" includeParams="none">
                      <s:param name="level" value="%{#attr.level}"/>
                      <s:param name="log" value="%{#attr.logger.name}"/>
                  </s:url>
                <a href="${loggingUrl}">${level}</a>
            </c:forTokens>
            </td></tr>
        </c:if>
    </c:forEach>
    <tr><td></td><td><input type="text" name="log"/></td><td>
    <select name="level">
        <c:forTokens var="level" delims="," items="DEBUG,INFO,WARN,ERROR,OFF">
            <option>${level}</option>
        </c:forTokens>
    </select> <input type="submit" value="Add New Logger"/></td></tr>
  </table>
</form>

<s:url id="showAllUrl" includeParams="none">
    <s:param name="showAll" value="true"/>
</s:url>
Show <a href="${showAllUrl}">all known loggers</a>