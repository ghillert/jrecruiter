<%@ include file="/taglibs.jsp"%>

<div  id="header_menu">
  <html:form style="margin-bottom:0;margin-top:0;" action="searchJobs" method="POST">
      <html:link action="backToWelcomePage" styleClass="button">
        <fmt:message key="all.back.to.welcome.page"/>
      </html:link>
    <html:text property="keyword" styleClass="headerForm" onblur="javascript:this.className='headerForm';"
               onfocus="javascript:this.className='headerFormSelected';" />
    <a href="javascript:document.forms[0].submit();" class="button">Search</a>
  </html:form>
</div>

<div id="main">
  <table>
      <tr>
          <td>
              <bean:message key="user.sent.password.title"/>
          </td>
      </tr>
      <tr>
          <td>
              <bean:message key="user.sent.password.text"/>
          </td>
      </tr>
      <tr>
          <td align="right">
              <ul>
                  <li><html:link action="welcome"><bean:message key="user.sent.password.link"/></html:link></li>
              </ul>
          </td>
      </tr>
  </table>
</div>
