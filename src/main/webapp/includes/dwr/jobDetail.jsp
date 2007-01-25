<%@ include file="/includes/taglibs.jsp"%>

<% pageContext.setAttribute("lf", "\n"); %>

       <table id="jobDetail">
       		<tr>
                <td colspan=61" style=""><a href="javascript:closeJobDetail();" title="Return to the Job List">Back</a></td>
            </tr>
            <tr>
                <td class="c1" style=""><fmt:message key="field.jobNumber"/>:</td>
                <td class="c2" style="">${jobDetail.id}</td>
                <td class="c3" style=""><fmt:message key="field.jobTitle"/>:</td>
                <td class="c4" style="" rowspan="2">${jobDetail.jobTitle}</td>
                <td class="c5" style=""><fmt:message key="field.jobPostDate"/>:</td>
                <td class="c6" style=""><fmt:formatDate value="${jobDetail.registerDate}" type="date" pattern="${datePattern}"/></td>
            </tr>
            <tr>
                <td class="c1_2" style="" colspan="3"></td>
                <td class="c5" style=""><fmt:message key="field.jobUpdateDate"/>:</td>
                <td class="c6" style=""><fmt:formatDate value="${jobDetail.updateDate}" type="date" pattern="${datePattern}"/></td>
            </tr>
        </table>
        <table id="jobDetail2">
            <tr>
                <td class="c1" style="">
                     <div id="rounddiv1" style="">
                         <table id="jobDetails" style="">
                            <tr>
                                <td><fmt:message key="field.location"/>:</td>
                                <td>${jobDetail.businessLocation}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="field.industry"/>:</td>
                                <td>${jobDetail.industry}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="field.salary"/>:</td>
                                <td>${jobDetail.salary}</td>
                            </tr>
                            <tr>
                                <td class="devider">&nbsp;</td>
                                <td class="devider">&nbsp;</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="field.phone"/>:</td>
                                <td>${jobDetail.businessPhone}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="field.email"/>:</td>
                                <td><a href="mailto:${jobDetail.businessEmail}">${jobDetail.businessEmail}</a></td>
                            </tr>
                        </table>
                    </div>
                </td>
                <td class="c2" style="">
                    <div id="rounddiv2" style="">
                    <table id="businessDetails" style="">
                        <tr style="">
                            <td><fmt:message key="field.businessName"/>:</td>
                            <td>${jobDetail.businessName}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="field.address"/>:</td>
                            <td>${jobDetail.businessAddress1}</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>${jobDetail.businessAddress2}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="field.city"/>:</td>
                            <td>${jobDetail.businessCity}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="field.state"/>:</td>
                            <td>${jobDetail.businessState}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="field.zip"/>:</td>
                            <td>${jobDetail.businessZip}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="field.website"/>:</td>
                            <td>
                                <c:if test="${not fn:contains(jobDetail.website, 'http')}">
                                  <a href="http://${jobDetail.website}">${jobDetail.website}</a>
                                </c:if>
                                <c:if test="${fn:contains(jobDetail.website, 'http')}">
                                  <a href="${jobDetail.website}">${jobDetail.website}</a>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                    </div>
                </td>
             </tr>
        </table>
        <table id="jobDescription">
            <tr>
                <td class="description-header"
                    style="">
                    <fmt:message key="field.jobDescription"/>
                </td>
            </tr>
            <tr>
                <c:set var="restr"><c:out value="${jobDetail.description}" escapeXml="true"/></c:set>
                <c:set var="restr"><c:out value="${fn:replace(restr, lf, '<br/>')}" escapeXml="false"/></c:set>
                <td class="textfield" style="padding-top: 10px; padding-left: 10px; padding-right: 10px; padding-bottom: 10px;">
                    <c:choose>
                        <c:when test="${not empty restr}">
                            <c:out value="${restr}" escapeXml="false"/>
                        </c:when>
                        <c:otherwise>
                            N/A
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td class="retriction-header"
                    style="">
                <fmt:message key="field.jobRestrictions"/></td>
            </tr>
            <tr>
              <c:set var="restr"><c:out value="${jobDetail.jobRestrictions}" escapeXml="true"/></c:set>
              <c:set var="restr"><c:out value="${fn:replace(restr, lf, '<br/>')}" escapeXml="false"/></c:set>
                <td class="textfield" style="padding-top: 10px; padding-left: 10px; padding-right: 10px; padding-bottom: 10px;">
                    <c:choose>
                        <c:when test="${not empty restr}">
                            <c:out value="${restr}" escapeXml="false"/>
                        </c:when>
                        <c:otherwise>
                            N/A
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>

