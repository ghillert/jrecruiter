
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
        <a  id="showJobsButton" href="${showJobsUrl}" class="button"><span class="showJobs">&nbsp;</span><spring:message code="jsp.index.showJobs"/></a>
      </li>
      <li>
        <s:url namespace="/" action="show-jobs" id="showJobsUrl"/>
        <a  id="showJobsFlexButton" href="${ctx}/flex-jobs/jobs.htm" class="button"><span class="showJobsFlex">&nbsp;</span><spring:message code="jsp.index.showJobs"/></a>
      </li>
      <li>
        <s:url namespace="/admin" action="index" id="adminMainUrl"/>
        <a id="manageAccountButton" href="${adminMainUrl}" class="button"><span class="manageAccount">&nbsp;</span><spring:message code="jsp.index.manageJobsAccount" /></a>
      </li>
      <li>
        <s:url namespace="/registration" action="signup" id="signupUrl"/>
        <a id="addUserButton" href="${signupUrl}" class="button"><span class="addUser">&nbsp;</span><spring:message code="jsp.index.createNewUser" /></a>
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

            $('#showJobsButton').qtip({
            	   content: 'Show all available job postings.',
            	   show: 'mouseover',
            	   hide: 'mouseout',
            	   position: {
            		      corner: {
            		         target: 'rightMiddle',
            		         tooltip: 'leftMiddle'
            		      }
            	    },         		            	   
           		   style: { 
	                   tip: 'leftMiddle' // Notice the corner value is identical to the previously mentioned positioning corners
	               }
           
            });
            $('#showJobsFlexButton').qtip({
                   content: 'Show all available job postings using the Adobe Flex GUI.',
                   show: 'mouseover',
                   hide: 'mouseout',
                   position: {
                          corner: {
                             target: 'rightMiddle',
                             tooltip: 'leftMiddle'
                          }
                    },                                 
                   style: { 
                       tip: 'leftMiddle' // Notice the corner value is identical to the previously mentioned positioning corners
                   }
            });
            $('#manageAccountButton').qtip({
                   content: 'Administrate your job postings.',
                   show: 'mouseover',
                   hide: 'mouseout',
                   position: {
                          corner: {
                             target: 'rightMiddle',
                             tooltip: 'leftMiddle'
                          }
                    },                                 
                   style: { 
                       tip: 'leftMiddle' // Notice the corner value is identical to the previously mentioned positioning corners
                   }
            });
            $('#addUserButton').qtip({
                content: 'Register for a new user account.',
                show: 'mouseover',
                hide: 'mouseout',
                position: {
                       corner: {
                          target: 'rightMiddle',
                          tooltip: 'leftMiddle'
                       }
                 },                                 
                style: { 
                    tip: 'leftMiddle' // Notice the corner value is identical to the previously mentioned positioning corners
                }
            });
            
            
            
        });
   </script>

