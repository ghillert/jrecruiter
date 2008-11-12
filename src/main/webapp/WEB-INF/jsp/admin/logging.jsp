<%@ page import="org.apache.log4j.*" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2>Logging Settings</h2>

<form>

    <h3>Log Files</h3>

    <jmesa:tableFacade
        id="logFileInfo" 
        items="${logFileInfos}"
        var="bean"
        >
        <jmesa:htmlTable>               
            <jmesa:htmlRow>
                <jmesa:htmlColumn title="File Name" property="fileName"/>     
                <jmesa:htmlColumn title="File Size"><s:property value="%{#attr.logFileInfo.fileSize/1024"/> Kb</jmesa:htmlColumn>
                <jmesa:htmlColumn title="Last Updated" property="fileLastChanged"/>
                <jmesa:htmlColumn title="&nbsp;">
                    <s:url id="downLoadUrl" action="downloadLogFile">
                        <s:param name="fileName" value="%{#logFileInfo.fileName}"/>
	                </s:url>
	                <a href="<s:property value="#downLoadUrl"/>"><img src="${ctx}/images/icons/crystal/viewmag.png"/></a>
                </jmesa:htmlColumn>
            </jmesa:htmlRow>
        </jmesa:htmlTable> 
    </jmesa:tableFacade>

   <h3>Loggers</h3>
   
   <jmesa:tableFacade
        id="logger" 
        items="${configuredLoggers}"
        var="bean"
        >
        <jmesa:htmlTable>               
            <jmesa:htmlRow>
                <jmesa:htmlColumn title="Level"  property="level" />     
                <jmesa:htmlColumn title="Logger" property="name"  />
                <jmesa:htmlColumn title="Set New Log Level">
                    <c:forTokens var="level" delims="," items="DEBUG,INFO,WARN,ERROR,OFF">
		                <s:url id="loggingUrl" includeParams="none">
		                      <s:param name="level" value="%{#attr.level}"/>
		                      <s:param name="log" value="%{#logger.name}"/>
		                  </s:url>
		                <a href="${loggingUrl}">${level}</a>
		            </c:forTokens>
                </jmesa:htmlColumn>
            </jmesa:htmlRow>
        </jmesa:htmlTable> 
    </jmesa:tableFacade>
    
    <h3>Add new Loggers</h3> 
    
    <table style="width: 100%;">
    <tr><th>Level</th><th>Logger</th><th>Set New Level</th></tr>

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