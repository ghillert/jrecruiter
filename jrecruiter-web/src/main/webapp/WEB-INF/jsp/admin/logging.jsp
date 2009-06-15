<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2>Logging Settings</h2>

  <s:url id="showLoggersUrl"  action="logging-ajax-logger-table"   includeParams="none"/>
  <s:url id="showLogFilesUrl" action="logging-ajax-log-file-table" includeParams="none"/>

  <script type="text/javascript">

  function onInvokeAction(id) {

	  jQuery.jmesa.setExportToLimit(id, '');

      var parameterString = jQuery.jmesa.createParameterStringForLimit(id);
      var showAll = '<s:property value="%{showAll}"/>';
      var url;

      if (id == 'loggingLoggerTable') {
    	   url = '<s:property value="%{#showLoggersUrl}"/>';
      } else {
    	  url = '<s:property value="%{#showLogFilesUrl}"/>';
      }

      jQuery.get(url + '?showAll=' + showAll + '&' + parameterString, function(data) {
          jQuery("#" + id + 'Div').html(data);
      });
  }
  </script>

<s:form>

<h3>Log Files</h3>

<div id="loggingLogFileTableDiv">
    <jsp:include page="loggingLogFileTable.jsp"/>
</div>

<s:url id="showAllLoggersUrl" includeParams="none">
	<s:param name="showAll" value="true" />
</s:url> <s:url id="showConfiguredLoggersUrl" includeParams="none">
</s:url> <s:if test="showAll">
	<h3><a href="${showConfiguredLoggersUrl}">Configured Loggers</a> |
	All Loggers</h3>
</s:if> <s:else>
	<h3>Configured Loggers | <a href="${showAllLoggersUrl}">All
	Loggers</a></h3>
</s:else> 

<div id="loggingLoggerTableDiv">
    <jsp:include page="loggingLoggerTable.jsp"/>
</div>
            <div class="submit" style="margin-right: auto; margin-left: 0; margin-top: 0.5em;">
              <s:submit value="Update Log Levels" method="updateLagLevels"/>
            </div>
            
	<fieldset style="margin: 1em 0 0 0;"><legend>Add a new Logger</legend>
	        <div class="optional">
	          <label for="username">Logger Name</label>
	          <s:textfield id="loggerName" name="newLogger.loggerName" required="true" tabindex="1" size="40"/>
	        </div>
	        <div class="optional">
	          <label for="username">Log Level</label>
              <s:select list="logLevels" listValue="logLevelName" value="%{#attr.bean.logLevel}" name="newLogger.newLogLevel"/>
	        </div>
	        <div class="submit">
	          <s:submit value="Add New Logger" method="addNewLogger"/>
	        </div>
	</fieldset>
</s:form>