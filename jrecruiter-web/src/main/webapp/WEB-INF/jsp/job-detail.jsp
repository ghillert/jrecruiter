<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<% pageContext.setAttribute("lf", "\n"); %>

<style media="print">
    <!--
    .header_menu,
    .outer-header,
    .footer { display: none; }
    -->
</style>

<div id="jobHeader">
    <div style="float: right; width: 200px; text-align: right;">Statistics: ${job.statistic.counter} hit(s)</div>
    <ul>
        <s:url var="showJobsUrl"  value="/show-jobs.html?restore=true" />
        <c:url var="exportPdfUrl" value="/s/${job.id}/jobDetail.pdf"   />

        <li    ><a class="back" href="${showJobsUrl}">Back</a></li>
        <li    ><a class="print" href="#" onclick="window.print();return false;">Print</a></li>
        <li    ><a class="pdf" href="${exportPdfUrl}">Pdf</a></li>
    </ul>
</div>

        <s:if test="job.usesMap">
          <fieldset id="jobDetailMap">
              <legend><spring:message code="jsp._ALL.job.field.location"/></legend>
              <div id="map_canvas" style="width: 100%; height: 300px; background-color: white;">
              ${job.latitude}, ${job.longitude}
              </div>
          </fieldset>
        </s:if>
        <fieldset id="jobDetail">
            <legend><spring:message code="jsp.job-detail.label.summary"/></legend>
            <div class="optional">
                <label>Job Id:</label>
                <s:property value="%{job.id}" escape="true"/>
            </div>
            <div class="optional">
                <label><spring:message code="jsp._ALL.job.field.jobUpdateDate"/>:</label>
                <s:property value="%{job.updateDate}" escape="true"/>
            </div>
            <div class="optional strong">
                <label><spring:message code="jsp._ALL.job.field.jobTitle"/>:</label>
                <s:property value="%{job.jobTitle}" escape="true"/>
            </div>
            <div class="optional strong">
                <label><spring:message code="jsp._ALL.job.field.businessName"/>:</label>
                    ${job.businessName}
            </div>
            <s:if test="job.offeredBy != null">
                <div class="optional">
                    <label><spring:message code="jsp._ALL.job.field.offered.by"/>:</label>
                    <s:property value="%{getText(job.offeredBy.descriptionKey)}" default="N/A"/>
                </div>
            </s:if>
            <s:if test="job.regionForDisplay != null && job.regionForDisplay.length() > 0">
                <div class="optional">
                    <label><spring:message code="jsp._ALL.job.field.location"/>:</label>
                        ${job.regionForDisplay}
                </div>
            </s:if>
            <s:if test="job.industryForDisplay != null && job.industryForDisplay.length() > 0">
                <div class="optional">
                  <label><spring:message code="jsp._ALL.job.field.industry"/>:</label>
                      ${job.industryForDisplay}
                </div>
            </s:if>
            <s:if test="job.salary != null && job.salary.length() > 0">
                <div class="optional"><label><spring:message code="jsp._ALL.job.field.salary"/>:</label>
                    ${job.salary}
                </div>
            </s:if>
            <s:if test="job.businessPhone != null && job.businessPhone.length() > 0">
                <div class="optional">
                    <label><spring:message code="jsp._ALL.job.field.phone"/>:</label>
                    ${job.businessPhone}
                </div>
            </s:if>
            <s:if test="job.businessPhoneExtension != null && job.businessPhoneExtension.length() > 0">
                <div class="optional">
                    <label><spring:message code="jsp._ALL.job.field.phone_extension"/>:</label>
                    ${job.businessPhoneExtension}
                </div>
            </s:if>
            <s:if test="job.businessEmail != null && job.businessEmail.length() > 0">
                <div class="optional">
                    <label><spring:message code="jsp._ALL.job.field.email"/>:</label>
                    <a href="mailto:${job.businessEmail}">${job.businessEmail}</a>
                </div>
            </s:if>
            <s:if test="job.businessAddress1 != null && job.businessAddress1.length() > 0">
                <div class="optional">
                    <label><spring:message code="jsp._ALL.job.field.address"/>:</label>
                    ${job.businessAddress1}
                </div>
            </s:if>
            <s:if test="job.businessAddress2 != null  && job.businessAddress2.length() > 0">
                <div class="optional">
                     <label>&nbsp;</label>${job.businessAddress2}
                </div>
            </s:if>
            <s:if test="job.city != null && job.city.length() > 0">
                <div class="optional">
                    <label><spring:message code="jsp._ALL.job.field.city"/>:</label>${job.businessCity}
                </div>
            </s:if>
            <s:if test="job.businessState != null && job.businessState.length() > 0">
              <div class="optional">
                 <label><spring:message code="jsp._ALL.job.field.state"/>:</label><s:property value="%{job.businessState}"/>
              </div>
            </s:if>
            <s:if test="job.businessZip != null && job.businessZip.length() > 0">
              <div class="optional">
                  <label><spring:message code="jsp._ALL.job.field.zip"/>:</label><s:property value="%{job.businessZip}"/>
              </div>
            </s:if>
            <s:if test="job.website != null && job.website.length() > 0">
                <div class="optional">
                   <label><spring:message code="jsp._ALL.job.field.website"/>:</label>
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
            <legend><spring:message code="jsp._ALL.job.field.jobDescription"/></legend>
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
        <s:if test="job.jobRestrictions != null && job.jobRestrictions.length() > 0">
          <fieldset id="jobDetailRestriction">
              <legend><spring:message code="jsp._ALL.job.field.jobRestrictions"/></legend>
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

        <s:if test="job.usesMap">
            <!-- Google Maps -->
			<script type="text/javascript"
			    src="http://maps.google.com/maps/api/js?sensor=false">
			</script>
            <script type="text/javascript">
            <!--
                jQuery(function() {
                    showJob('map_canvas',  ${job.latitude}, ${job.longitude}, ${job.zoomLevel});
                });

                jQuery(document).unload(function() {GUnload();});
            //-->
            </script>
        </s:if>
