<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2>Logging Settings</h2>

  <s:url id="showLoggersUrl"  action="logging-ajax-logger-table"/>
  <s:url id="showLogFilesUrl" action="logging-ajax-log-file-table"/>

  <script type="text/javascript">

  function onInvokeAction(id) {

      setExportToLimit(id, '');

      var parameterString = createParameterStringForLimit(id);

      var url;

      if (id == 'loggingLoggerTable') {
    	   url = '<s:property value="%{#showLoggersUrl}"/>';
      } else {
    	  url = '<s:property value="%{#showLogFilesUrl}"/>';
      }
      alert(url);
      jQuery.get(url + '?ajax=true&tableId=id' + parameterString, function(data) {
          jQuery("#" + id + 'Div').html(data);
      });
  }
  </script>

<form>

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