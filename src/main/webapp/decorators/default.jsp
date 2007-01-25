<%@include file="/includes/taglibs.jsp"%>

<!DOCTYPE html
        PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
	<head>
        <meta http-equiv="Cache-Control" content="no-store"/>
	    <meta http-equiv="Pragma"        content="no-cache"/>
	    <meta http-equiv="Expires"       content="0"/>
	    <meta http-equiv="content-type"  content="text/html; charset=utf-8"/>
	    
	    <meta name="author"      content="Gunnar Hillert"/>
	    <meta name="keywords"    content="Jobs, java, Atlanta, j2ee, java ee, user group" />
	    <meta name="description" content="Job Posting Service of the Atlanta Java User Group (AJUG)" />

	    <script type="text/javascript" src="<c:url value='/js/prototype.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/js/scriptaculous/scriptaculous.js'/>"></script>

	    <script type="text/javascript" src="<c:url value='/js/overlibmws.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/js/ajaxtags.js'/>"></script>
	    	    
	    <script type="text/javascript" src="<c:url value='/js/rico.js'/>"></script>

		<!-- DWR specific -->
		<script src="<c:url value='/dwr/interface/ajaxService.js'/>"></script>
	   	<script src="<c:url value='/dwr/engine.js'/>"></script>
	   	<script src="<c:url value='/dwr/util.js'/>"></script>

		<!-- Any jRecruiter specific scripts -->
	    <script type="text/javascript" src="<c:url value='/js/jrecruiter.js'/>"></script>
	    
	    <link rel="icon"          href="<c:url value='/favicon.ico'/>" type="image/x-icon" />
	    <link rel="shortcut icon" href="<c:url value='/favicon.ico'/>" type="image/x-icon" />
	
	    <title><decorator:title default="Welcome to jRecruiter"/></title>
	    <link href="<c:url value='/style/stylesheet.css'/>"  rel="stylesheet" type="text/css" />
	
	</head>
<body onload="init();">
    <%@ include file="/pages/global/header.jsp"%>
    <decorator:body/>
    <%@ include file="/pages/global/footer.jsp"%>

    <script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
    </script>
    <script type="text/javascript">
        _uacct = "UA-177507-3";
        urchinTracker();
    </script>
</body>
</html>

