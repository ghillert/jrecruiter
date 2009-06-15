<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

    <jmesa:tableFacade id="loggingLoggerTable" items="${configuredLoggers}" var="bean" stateAttr="">
    	<jmesa:htmlTable >
	       	<jmesa:htmlRow >
			 <jmesa:htmlColumn title="Current Log Level" property="logLevel.logLevelName" />
			 <jmesa:htmlColumn title="Logger" property="loggerName" />
			 <jmesa:htmlColumn title="Change Log Level" filterable="false">
			    <s:hidden name="updatedLoggers[%{#attr.rowcount}].loggerName" value="%{#attr.bean.loggerName}"/>
			    <s:select list="logLevels" listValue="logLevelName" value="%{#attr.bean.logLevel}" name="updatedLoggers[%{#attr.rowcount}].newLogLevel"/>
			 </jmesa:htmlColumn>
		  </jmesa:htmlRow>
	   </jmesa:htmlTable>
    </jmesa:tableFacade>
