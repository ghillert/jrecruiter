
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
  <p><spring:message code="jsp.index.introduction.text.top" /></p>

  <s:form action="search">
    <div class="required" style="border: 1px solid #aaaaCC; width: 100%; padding-top: 1em; margin-left: 0; text-align: center; background-color: #FAFAFA">
       <label for="keyword"><spring:message code="jsp.index.search.term"/></label>
       <s:textfield name="keyword" id="keyword" size="30" tabindex="1" cssClass="searchBox"/>
       <s:submit value="%{getText('jsp.index.search.button')}" method="search" cssClass="button" cssStyle="margin-left: 1em; border-style: solid; border-width: 1px;"/>
       <br style="clear: both;"/>
    </div>
</s:form>

  <div id="startPageMenuContainer">
    <s:url id="jobCountUrl" namespace="/chart" action="viewJobCountChart"/>
    <ul class="menuList" style="width: 15em; float: left;">
      <li>
        <s:url namespace="/" action="show-jobs" id="showJobsUrl"/>
        <a href="${showJobsUrl}" class="button"><span class="showJobs">&nbsp;</span><spring:message code="jsp.index.showJobs"/></a>
      </li>
      <li>
        <s:url namespace="/admin" action="index" id="adminMainUrl"/>
        <a href="${adminMainUrl}" class="button"><span class="manageAccount">&nbsp;</span><spring:message code="jsp.index.manageJobsAccount" /></a>
      </li>
      <li>
        <s:url namespace="/registration" action="signup" id="signupUrl"/>
        <a href="${signupUrl}" class="button"><span class="addUser">&nbsp;</span><spring:message code="jsp.index.createNewUser" /></a>
      </li>
    </ul>
    <div style="margin-left: 0; margin-right: auto;">
        <img src="${jobCountUrl}" alt="Job statistics graph - Number of Jobs" style="width: 300px; height: 150px;"/>
    </div>

  </div>
  <p style="clear: both;"><spring:message code="jsp.index.introduction.text.bottom" /></p>
  <p><spring:message code="jsp.index.introduction.text.support" /></p>


   <script type="text/javascript">
        jQuery(function() {
            jQuery('#keyword').focus();
        });
   </script>

