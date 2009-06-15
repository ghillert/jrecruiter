<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<s:form action="search">

    <div class="required">
       <label for="keyword">Search terms</label>
       <s:textfield name="keyword" id="keyword" size="30" tabindex="1" cssClass="searchBox"/>
       <s:submit value="Search" method="search" cssStyle="margin-left: 1em;"/>
    </div>
    <p style="clear: both;"/>
    <s:if test="jobs != null && !jobs.empty">
       <jmesa:tableFacade
            id="tag"
            items="${jobs}"
            var="job"
            stateAttr="restore"
            >
          <jmesa:htmlTable>
            <jmesa:htmlRow>
                    <jmesa:htmlColumn  property="usesMap" title="&nbsp;" filterable="false">
                        <s:url id="jobDetailUrl" action="job-detail" includeParams="false">
                          <s:param name="jobId" value="%{#attr.job.id}"/>
                        </s:url>
                        <a title="Job Detail - ${job.jobTitle}" href="${jobDetailUrl}">
                            <img alt="Job Detail" title="Job Detail - ${job.jobTitle}" src="${ctx}/images/icons/crystal/viewmag.png" style="border-width: 0;"/>
                        </a>
                    </jmesa:htmlColumn>
                    <jmesa:htmlColumn property="details" title="&nbsp;" filterable="false">

                        <s:if test="#attr.job.usesMap">
                            <img alt="Uses Map" title="This job posting uses the Google maps feature." src="${ctx}/images/icons/crystal/globe.png" style="border-width: 0;"/>
                        </s:if>
                        <s:else>&nbsp;</s:else>
                    </jmesa:htmlColumn>
                    <jmesa:htmlColumn property="jobTitle"     titleKey="jsp.show.jobs.job.title" />
                    <jmesa:htmlColumn property="businessName" titleKey="jsp.show.jobs.business.name"/>
                    <jmesa:htmlColumn property="region.name"  titleKey="jsp.show.jobs.job.location" filterable="false"/>
                    <jmesa:htmlColumn property="updateDate"   titleKey="jsp.show.jobs.date" pattern="${datePattern}" cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
            </jmesa:htmlRow>
            </jmesa:htmlTable>
        </jmesa:tableFacade>
    </s:if>
</s:form>



<script type="text/javascript">

    jQuery(function() {

      jQuery(':input').bind('focus', function(event) { jQuery(event.target).addClass('selected'); });
      jQuery(':input').bind('blur', function(event) { jQuery(event.target).removeClass('selected'); });

      jQuery('#keyword').focus();

    });

</script>
