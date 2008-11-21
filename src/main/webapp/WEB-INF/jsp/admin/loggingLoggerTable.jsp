<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

    <jmesa:tableFacade id="loggingLoggerTable" items="${configuredLoggers}" var="bean">
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
