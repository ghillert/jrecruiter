<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<!DOCTYPE html
        PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
  <head>
      <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
      <meta name="author" content="Jerzy Puchala, Dorota Puchala, Gunnar Hillert"/>
      <meta name="keywords" content="Jobs, java, Atlanta, j2ee, user group" />
      <meta name="description" content="Job Posting Service of the Atlanta Java User Group (AJUG)" />

      <title>
      403
      </title>
      <link href="<html:rewrite page="/style/stylesheet.css" />"  rel="stylesheet" type="text/css" />
  </head>
  <body>
      <%@ include file="/pages/global/header.jsp"%>

      <div  id="header_menu">
        <html:link action="backToWelcomePage" styleClass="button">
          <fmt:message key="all.back.to.welcome.page"/>
        </html:link>
      </div>

      <div id="main">
        <p style="padding: 50px;">
          <fmt:message key="403.message"/>
        </p>
       </div>
        <%@ include file="/pages/global/footer.jsp"%>
  </body>
</html>