<%@page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<c:set var="datePattern"><spring:message code="date.format"/></c:set>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>

<!DOCTYPE html
        PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
  <head>
    <title>Welcome to jRecruiter</title>

    <meta name="author"      content="Gunnar Hillert" />
    <meta name="keywords"    content="Jobs, java, Atlanta, j2ee, java ee, user group" />
    <meta name="description" content="Job Posting Service of the Atlanta Java User Group (AJUG)" />

   <jwr:script src="/js/swfobject.js" />

<style type="text/css" media="screen">
  html, body, #containerA, #containerB { height:100%; }
  body { margin:0; padding:0; overflow:hidden; }
</style>

   <script type="text/javascript">
        swfobject.embedSWF("jobs.swf", "flexContent", "100%", "100%", "9.0.0", "expressInstall.swf");
    </script>

  </head>
  <body>
    <div id="flexContent">
      <p>jRecruiter FX</p>
    </div>
  </body>
</html>

