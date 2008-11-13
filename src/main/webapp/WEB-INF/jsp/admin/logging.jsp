<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2>Logging Settings</h2>

<form>

<h3>Log Files</h3>

<jmesa:tableFacade id="logFileInfo" items="${logFileInfos}" var="logFileInfo">
	<jmesa:htmlTable>
		<jmesa:htmlRow>
			<jmesa:htmlColumn title="File Name" property="fileName" />
			<jmesa:htmlColumn title="File Size"                      >
				<s:property value="%{#attr.logFileInfo.fileSize/1024}" /> Kb
		    </jmesa:htmlColumn>
			<jmesa:htmlColumn title="Last Updated" property="fileLastChanged" />
			<jmesa:htmlColumn title="1" property="fileSize">
				<s:url id="downLoadUrl" action="downloadLogFile">
					<s:param name="fileName" value="%{#attr.logFileInfo.fileName}" />
				</s:url>
				<a href="<s:property value="%{downLoadUrl}"/>"><img
					src="${ctx}/images/icons/crystal/viewmag.png" /></a>
			</jmesa:htmlColumn>
		</jmesa:htmlRow>
	</jmesa:htmlTable>
</jmesa:tableFacade> <s:url id="showAllLoggersUrl" includeParams="none">
	<s:param name="showAll" value="true" />
</s:url> <s:url id="showConfiguredLoggersUrl" includeParams="none">
</s:url> <s:if test="showAll">
	<h3><a href="${showConfiguredLoggersUrl}">Configured Loggers</a> |
	All Loggers</h3>
</s:if> <s:else>
	<h3>Configured Loggers | <a href="${showAllLoggersUrl}">All
	Loggers</a></h3>
</s:else> <jmesa:tableFacade id="logger" items="${configuredLoggers}" var="bean">
	<jmesa:htmlTable>
		<jmesa:htmlRow>
			<jmesa:htmlColumn title="Level" property="level" />
			<jmesa:htmlColumn title="Logger" property="name" />
			<jmesa:htmlColumn title="Set New Log Level">
				<c:forTokens var="level" delims=","
					items="DEBUG,INFO,WARN,ERROR,OFF">
					<s:url id="loggingUrl" includeParams="none">
						<s:param name="level" value="%{#attr.level}" />
						<s:param name="log" value="%{#logger.name}" />
					</s:url>
					<a href="${loggingUrl}">${level}</a>
				</c:forTokens>
			</jmesa:htmlColumn>
		</jmesa:htmlRow>
	</jmesa:htmlTable>
</jmesa:tableFacade>

	<fieldset><legend>Add a new Logger</legend>
	
	        <div class="required">
	          <label for="username">Logger Name*</label>
	          <s:textfield id="loggerName" name="log" required="true" tabindex="1"/>
	        </div>
	        <div class="required">
	          <label for="username">Log Level*</label>
	          <select name="level">
	            <c:forTokens var="level" delims="," items="DEBUG,INFO,WARN,ERROR,OFF">
	                <option>${level}</option>
	            </c:forTokens>
	          </select>
	        </div>
	        <div class="submit">
	          <s:submit value="Add New Logger"/>
	        </div>
	</fieldset>
</form>