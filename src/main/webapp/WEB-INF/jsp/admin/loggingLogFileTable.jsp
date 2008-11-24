<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<jmesa:tableFacade id="loggingLogFileTable" items="${logFileInfos}" var="logFileInfo">
	<jmesa:htmlTable>
		<jmesa:htmlRow>
			<jmesa:htmlColumn title="File Name" property="fileName" />
			<jmesa:htmlColumn title="File Size"                      >
				<s:property value="%{#attr.logFileInfo.fileSize/1024}" /> Kb
		    </jmesa:htmlColumn>
			<jmesa:htmlColumn title="Last Updated" property="fileLastChanged" />
			<jmesa:htmlColumn title="&nbsp;" property="fileSize" filterable="false">
				<s:url id="downLoadUrl" action="downloadLogFile" includeParams="none">
					<s:param name="fileName" value="%{#attr.logFileInfo.fileName}" />
				</s:url>
				<a href="<s:property value="%{downLoadUrl}"/>"><img
					src="${ctx}/images/icons/crystal/viewmag.png" /></a>
			</jmesa:htmlColumn>
		</jmesa:htmlRow>
	</jmesa:htmlTable>
</jmesa:tableFacade> 
