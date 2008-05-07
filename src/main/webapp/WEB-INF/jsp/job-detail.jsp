<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<% pageContext.setAttribute("lf", "\n"); %>

    <style>
        .horizontalList
        {
        padding: .2em 0;
    margin: 0;
    list-style-type: none;
    background-color: #036;
    color: #FFF;
    width: 100%;
    font: normal 90% arial, helvetica, sans-serif;
    text-align: center;
        }

        .horizontalList li
    {
    display: inline;
    list-style-type: none;
    padding-right: 20px;
    }

    .verticalList li
        {
        clear: left;
        list-style-type: none;
        padding-right: 20px;
        }
        .verticalList li label
        {
        display: block;
        width: 140px;
        float: left;
        }
    </style>

<script type="text/javascript">
<!--
    window.onload = function() { showJob('map_canvas',  ${job.latitude}, ${job.longitude});}
    window.onunload = function() { GUnload(); }
//-->
</script>
       <ul class="horizontalList">
            <s:url id="showJobsUrl" action="show-jobs" includeParams="none"/>
            <li><a href="${showJobsUrl}"><img src="${ctx}/images/icons/crystal/back.png" alt="Back" title="Back"/></a></li>
            <li><label><fmt:message key="field.jobNumber"/>:</label>${job.id}</li>
            <li><label><fmt:message key="field.jobPostDate"/>:</label><fmt:formatDate value="${job.registrationDate}" type="date" pattern="${datePattern}"/></li>
            <li><label><fmt:message key="field.jobUpdateDate"/>:</label><fmt:formatDate value="${job.updateDate}" type="date" pattern="${datePattern}"/></li>
       </ul>

        <fieldset style="width: 300px; float: right; height: 300px;">
            <legend>Map Location</legend>
            <div id="map_canvas" style="width: 100%; height: 100%; background-color: white;">
            ${job.latitude}, ${job.longitude}
            </div>
        </fieldset>

        <fieldset style="height: 300px;">
            <legend>Summary</legend>
          <ul class="verticalList">

                <li><label><fmt:message key="field.location"/>:</label>
                    ${job.region.name}</li>

                <li><label><fmt:message key="field.industry"/>:</label>
                    ${job.industry.name}</li>

                <li><label><fmt:message key="field.salary"/>:</label>
                    ${job.salary}</li>

                <li><label><fmt:message key="field.phone"/>:</label>
                    ${job.businessPhone}</li>

                <li><label><fmt:message key="field.email"/>:</label>
                    <a href="mailto:${job.businessEmail}">${job.businessEmail}</a></li>

                <li><label><fmt:message key="field.businessName"/>:</label>
                    ${job.businessName}</li>
                <li><label><fmt:message key="field.address"/>:</label>
                ${job.businessAddress1}</li>
                <li><label></label>${job.businessAddress2}</li>
                <li><label><fmt:message key="field.city"/>:</label>${job.businessCity}</li>
                <li><label><fmt:message key="field.state"/>:</label>${job.businessState}</li>
                <li><label><fmt:message key="field.zip"/>:</label>${job.businessZip}</li>
                <li><label><fmt:message key="field.website"/>:</label>
                    <c:if test="${not fn:contains(job.website, 'http')}">
                      <a href="http://${job.website}">${job.website}</a>
                    </c:if>
                    <c:if test="${fn:contains(job.website, 'http')}">
                      <a href="${job.website}">${job.website}</a>
                    </c:if>
                </li>
                </ul>
        </fieldset>
        <fieldset style="clear: right;">
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
        <fieldset>
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
