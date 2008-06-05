<%@include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

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

    <title><decorator:title default="Welcome to jRecruiter" /></title>
    <jwr:style src="/bundles/all.css" />
  </head>
  <body onload="init();">
    <div id="container">
      <div id="header">AJUG Jobs</div>
      <div id="header_menu">
          <ul><li><a href="<c:url value='/'/>">Home</a></li>
            <li>
                            <s:url id="searchUrl" action="search" namespace="/"/>
                            <a href="${searchUrl}">Search Jobs</a></li>
            <li>
            <s:url id="adminUrl" action="index" namespace="/admin"/>
            <a href="${adminUrl}">Admin</a></li>
            <li>
            <s:url id="contactUrl" action="contact" namespace="/"/>
            <a href="${contactUrl}">Contact</a></li>
            <li class="icon"><a href="<c:url value='/rss/jobs.rss'/>" class="icon" title="Get the latest 20 job offers as RSS feed.">&nbsp;<span>RSS Feed</span></a></li></ul>
      </div>
      <div id="content"><%@ include
        file="/WEB-INF/jsp/includes/messages.jsp"%> <decorator:body />
      </div>
      <div id="footer"><a class="footerLogo"
        href="http://www.jrecruiter.org"
        title="Main website of the jRecruiter project">jRecruiter</a>
      </div>
    </div>

    <!-- Java Script Imports -->
    <jwr:script src="/bundles/lib.js"/>

    <!-- DWR specific -->
    <script type="text/javascript" src="<c:url value='/dwr/interface/ajaxService.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>

    <!-- Google Maps -->
    <script
      src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAaRkHCsiKIvvB_UEon-SKORRDnPHMi8enZNdcVVCphbEA7JeURRRhskeG0Rbs1V5Bog4q8OVhIFC3Ww"
      type="text/javascript"></script>


  </body>
</html>

