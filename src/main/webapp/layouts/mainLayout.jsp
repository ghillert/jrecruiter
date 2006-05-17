<%@include file="/taglibs.jsp"%>

<!DOCTYPE html
        PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html:html xhtml="false" >

<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <meta name="author" content="Jerzy Puchala, Dorota Puchala, Gunnar Hillert"/>
    <meta name="keywords" content="Jobs, java, Atlanta, j2ee, user group" />
    <meta name="description" content="Job Posting Service of the Atlanta Java User Group (AJUG)" />

    <script type="text/javascript" src="<c:url value='/js/prototype.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/scriptaculous.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/overlibmws.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/ajaxtags.js'/>"></script>
    <script type="text/javascript" src="${ctx}/js/global.js"></script>

    <title>
       <fmt:message >
      <tiles:getAsString name="pageTitle"/>
  </fmt:message >
    </title>
    <link href="<c:url value='/style/stylesheet.css'/>"  rel="stylesheet" type="text/css" />

</head>
<body>
    <tiles:insert attribute="header"/>
    <tiles:insert name="errorMessage" />
    <tiles:insert attribute="body"/>
    <tiles:insert attribute="footer"/>
</body>
</html:html>

