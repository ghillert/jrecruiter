<%@ page language="java" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>


<!DOCTYPE html
        PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
  <head>
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma"        content="no-cache" />
    <meta http-equiv="Expires"       content="0" />
    <meta http-equiv="content-type"  content="text/html; charset=utf-8" />

    <meta name="author"      content="Gunnar Hillert" />
    <meta name="keywords"    content="Jobs, java, Atlanta, j2ee, java ee, user group" />
    <meta name="description" content="Job Posting Service of the Atlanta Java User Group (AJUG)" />

    <link href="<c:url value='/rss/jobs.rss'/>" rel="alternate" type="application/rss+xml" title="jRecruiter RSS Feed" />

    <link rel="icon" href="<c:url value='/favicon.ico'/>"
      type="image/x-icon" />
    <link rel="shortcut icon" href="<c:url value='/favicon.ico'/>"
      type="image/x-icon" />

    <title>Error</title>

<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/global.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/jobDetail.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/jobList.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/showStatistics.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/login.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/form.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/jmesa-pdf.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/displaytag.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/userList.css"></link>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/jmesa.css"></link>

  </head>
  <body>
    <div class="container">
      <div class="header"><span class="ajug">AJUG</span> <span class="separator">|</span> Jobs</div>
      <div class="header_menu">
          <ul><li><a href="<c:url value='/'/>">Home</a></li>
            </ul>
      </div>
      <div class="content" style="overflow: auto;">

        An Error has occurred in this application.

    <%
     org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("Application");
     LOGGER.error("Error JSP Page triggered!", exception);
    %>

      </div>
      <div class="footer"><a class="footerLogo"
        href="http://www.jrecruiter.org"
        title="Main website of the jRecruiter project"><span>j</span>Recruiter</a>
      </div>
    </div>
  </body>
</html>
