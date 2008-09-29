<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<% pageContext.setAttribute("lf", "\n"); %>

<style media="print">
<!--
    #header { display: none; }
    #header_menu { display: none; }
    #jobHeader { display: none; }
    #footer { display: none; }
-->
</style>
<style>


</style>

<div id="jobHeader">
    <ul>
        <s:url id="showJobsUrl"  action="show-jobs"                    includeParams="none"/>
        <s:url id="exportPdfUrl" action="job-detail" method="exportJobAsPdf" includeParams="false">
            <s:param name="jobId" value="job.id"/>
        </s:url>
        <li    ><a class="back" href="${showJobsUrl}">Back</a></li>
        <li    ><a class="print" href="#" onclick="window.print();return false;">Print</a></li>
        <li    ><a class="pdf" href="${exportPdfUrl}">Pdf</a></li>
    </ul>
</div>

        <s:if test="job.usesMap">
          <fieldset id="jobDetailMap">
              <legend>Map Location</legend>
              <div id="map_canvas" style="width: 100%; height: 300px; background-color: white;">
              ${job.latitude}, ${job.longitude}
              </div>
          </fieldset>
        </s:if>
        <fieldset id="jobDetail">
            <legend>Summary</legend>
            <div class="optional strong">
                <label><fmt:message key="field.jobTitle"/>:</label>
                <s:property value="%{job.jobTitle}" escape="true"/>
            </div>
            <div class="optional strong">
                <label><fmt:message key="field.businessName"/>:</label>
                    ${job.businessName}
            </div>
            <div class="optional">
                <label><fmt:message key="field.offered.by"/>:</label>
                <s:property value="%{getText(job.offeredBy.descriptionKey)}"/>
            </div>
            <s:if test="job.region.name != null">
                <div class="optional">
                    <label><fmt:message key="field.location"/>:</label>
                        ${job.region.name}
                </div>
            </s:if>
            <s:if test="job.industry.name != null">
                <div class="optional">
                  <label><fmt:message key="field.industry"/>:</label>
                      ${job.industry.name}
                </div>
            </s:if>
            <s:if test="job.salary != null">
                <div class="optional"><label><fmt:message key="field.salary"/>:</label>
                    ${job.salary}
                </div>
            </s:if>
            <s:if test="job.businessPhone != null">
                <div class="optional">
                    <label><fmt:message key="field.phone"/>:</label>
                    ${job.businessPhone}
                </div>
            </s:if>
            <s:if test="job.businessEmail != null">
                <div class="optional">
                    <label><fmt:message key="field.email"/>:</label>
                    <a href="mailto:${job.businessEmail}">${job.businessEmail}</a>
                </div>
            </s:if>
            <s:if test="job.businessName != null">
                <div class="optional">
                    <label><fmt:message key="field.businessName"/>:</label>
                      ${job.businessName}
                </div>
            </s:if>
            <s:if test="job.businessAddress1 != null">
                <div class="optional">
                    <label><fmt:message key="field.address"/>:</label>
                    ${job.businessAddress1}
                </div>
            </s:if>
            <s:if test="job.businessAddress2 != null">
                <div class="optional">
                     <label>&nbsp;</label>${job.businessAddress2}
                </div>
            </s:if>
            <s:if test="job.city != null">
                <div class="optional">
                    <label><fmt:message key="field.city"/>:</label>${job.businessCity}
                </div>
            </s:if>
            <s:if test="job.businessState != null">
              <div class="optional">
                 <label><fmt:message key="field.state"/>:</label><s:property value="%{job.businessState}"/>
              </div>
            </s:if>
            <s:if test="job.businessZip != null">
              <div class="optional">
                  <label><fmt:message key="field.zip"/>:</label><s:property value="%{job.businessZip}"/>
              </div>
            </s:if>
            <s:if test="job.website != null">
                <div class="optional">
                   <label><fmt:message key="field.website"/>:</label>
                    <c:if test="${not fn:contains(job.website, 'http')}">
                      <a href="http://${job.website}">${job.website}</a>
                    </c:if>
                    <c:if test="${fn:contains(job.website, 'http')}">
                      <a href="${job.website}">${job.website}</a>
                    </c:if>
                </div>
            </s:if>
        </fieldset>
        <fieldset id="jobDetailDescription">
            <legend><fmt:message key="field.jobDescription"/></legend>
                <c:set var="restr"><c:out value="${job.description}" escapeXml="true"/></c:set>
                <c:set var="restr"><c:out value="${fn:replace(restr, lf, '<br/>')}" escapeXml="false"/></c:set>
                    <c:choose>
                        <c:when test="${not empty restr}">
                            <c:out value="${restr}" escapeXml="false"/>
                        </c:when>
                        <c:otherwise>
                            N/A
                        </c:otherwise>
                    </c:choose>
        </fieldset>
        <s:if test="job.jobRestrictions != null">
          <fieldset id="jobDetailRestriction">
              <legend><fmt:message key="field.jobRestrictions"/></legend>
                <c:set var="restr"><c:out value="${job.jobRestrictions}" escapeXml="true"/></c:set>
                <c:set var="restr"><c:out value="${fn:replace(restr, lf, '<br/>')}" escapeXml="false"/></c:set>
                      <c:choose>
                          <c:when test="${not empty restr}">
                              <c:out value="${restr}" escapeXml="false"/>
                          </c:when>
                          <c:otherwise>
                              N/A
                          </c:otherwise>
                      </c:choose>
          </fieldset>
        </s:if>
        <script type="text/javascript">
<!--

    jQuery(function() {
        showJob('map_canvas',  ${job.latitude}, ${job.longitude}, ${job.zoomLevel});
    });

    jQuery(document).unload(function() {GUnload();});
//-->
</script>