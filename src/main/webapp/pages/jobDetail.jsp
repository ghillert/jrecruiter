<%@ include file="/taglibs.jsp"%>

<% pageContext.setAttribute("lf", "\n"); %>

    <div  id="header_menu">
      <html:form style="margin-bottom:0;margin-top:0;" action="searchJobs" method="POST">
          <html:link action="showJobs" styleClass="button">
            <fmt:message key="all.back.to.welcome.page"/>
          </html:link>
        <html:text property="keyword" styleClass="headerForm" onblur="javascript:this.className='headerForm';"
                       onfocus="javascript:this.className='headerFormSelected';" />
        <a href="javascript:document.forms[0].submit();" class="button">Search</a>
        </html:form>
      </div>

<div id="main">
        <table class="joblist">
            <tr>
                <td class="column1"><fmt:message key="field.jobNumber"/>:</td>
                <td class="column2">${jobDetail.id}</td>
                <td class="column3"><fmt:message key="field.businessName"/>:</td>
                <td class="column4">${jobDetail.businessName}</td>
            </tr>
            <tr>
                <td class="column1"><fmt:message key="field.jobTitle"/>:</td>
                <td class="column2">${jobDetail.jobTitle}</td>
                <td class="column3"><fmt:message key="field.address"/>:</td>
                <td class="column4">${jobDetail.businessAddress1}</td>
            </tr>
            <tr>
                <td class="column1"><fmt:message key="field.location"/>:</td>
                <td class="column2">${jobDetail.businessLocation}</td>
                <td class="column3">&nbsp;</td>
                <td class="column4">${jobDetail.businessAddress2}</td>
            </tr>
            <tr>
                <td class="column1"><fmt:message key="field.industry"/>:</td>
                <td class="column2">${jobDetail.industry}</td>
                <td class="column3"><fmt:message key="field.city"/>:</td>
                <td class="column4">${jobDetail.businessCity}</td>
            </tr>
            <tr>
                <td class="column1"><fmt:message key="field.salary"/>:</td>
                <td class="column2">${jobDetail.salary}</td>
                <td class="column3"><fmt:message key="field.state"/>:</td>
                <td class="column4">${jobDetail.businessState}</td>
            </tr>
            <tr>
                <td class="column1"><fmt:message key="field.phone"/>:</td>
                <td class="column2">${jobDetail.businessPhone}</td>
                <td class="column3"><fmt:message key="field.zip"/>:</td>
                <td class="column4">${jobDetail.businessZip}</td>
            </tr>
            <tr>
                <td class="column1"><fmt:message key="field.email"/>:</td>
                <td class="column2"><a href="mailto:${jobDetail.businessEmail}">${jobDetail.businessEmail}</a></td>
                <td class="column3"><fmt:message key="field.website"/>:</td>
                <td class="column4">
                <c:if test="${not fn:contains(jobDetail.website, 'http')}">
                  <a href="http://${jobDetail.website}">${jobDetail.website}</a>
                </c:if>
                <c:if test="${fn:contains(jobDetail.website, 'http')}">
                  <a href="${jobDetail.website}">${jobDetail.website}</a>
                </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="4" class="textfieldlabel">
                <fmt:message key="field.jobDescription"/>:</td>
            </tr>
            <tr>
                <c:set var="restr"><c:out value="${jobDetail.description}" escapeXml="true"/></c:set>
              <c:set var="restr"><c:out value="${fn:replace(restr, lf, '<br/>')}" escapeXml="false"/></c:set>
                <td colspan="4" class="textfield"><c:out value="${restr}" escapeXml="false"></c:out></td>
            </tr>
            <tr>
                <td colspan="4" class="textfieldlabel"><fmt:message key="field.jobRestrictions"/>:</td>
            </tr>
            <tr>
              <c:set var="restr"><c:out value="${jobDetail.jobRestrictions}" escapeXml="true"/></c:set>
              <c:set var="restr"><c:out value="${fn:replace(restr, lf, '<br/>')}" escapeXml="false"/></c:set>
                <td colspan="4" class="textfield"><c:out value="${restr}" escapeXml="false"/></td>
            </tr>
            <tr>
                <td class="column1"><fmt:message key="field.jobPostDate"/>:</td>
                <td class="column2"><fmt:formatDate value="${jobDetail.updateDate}" type="date" pattern="${datePattern}"/></td>
                <td class="column3"></td>
                <td class="column4"></td>
            </tr>

        </table>
</div>
