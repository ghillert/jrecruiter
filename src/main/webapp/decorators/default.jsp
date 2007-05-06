<%@include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<!DOCTYPE html
        PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
	<head>
	<meta http-equiv="Cache-Control" content="no-store" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	
	<meta name="author" content="Gunnar Hillert" />
	<meta name="keywords"
		content="Jobs, java, Atlanta, j2ee, java ee, user group" />
	<meta name="description"
		content="Job Posting Service of the Atlanta Java User Group (AJUG)" />
	
	<script type="text/javascript" src="<c:url value='/js/prototype.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value='/js/scriptaculous/scriptaculous.js'/>"></script>
	
	<script type="text/javascript" src="<c:url value='/js/overlibmws.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/ajaxtags.js'/>"></script>
	
	<script type="text/javascript" src="<c:url value='/js/rico.js'/>"></script>
	
	<!-- DWR specific -->
	<script src="<c:url value='/dwr/interface/ajaxService.js'/>" type=""></script>
	<script src="<c:url value='/dwr/engine.js'/>" type=""></script>
	<script src="<c:url value='/dwr/util.js'/>" type=""></script>
	
	<!-- Any jRecruiter specific scripts -->
	<script type="text/javascript" src="<c:url value='/js/jrecruiter.js'/>"></script>
	
	<link rel="icon" href="<c:url value='/favicon.ico'/>"
		type="image/x-icon" />
	<link rel="shortcut icon" href="<c:url value='/favicon.ico'/>"
		type="image/x-icon" />
	
	<title><decorator:title default="Welcome to jRecruiter" /></title>
	<link href="<c:url value='/style/stylesheet.css'/>" rel="stylesheet"
		type="text/css" />
	
	<!-- Google Maps -->
	<script
		src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAaRkHCsiKIvvB_UEon-SKORRDnPHMi8enZNdcVVCphbEA7JeURRRhskeG0Rbs1V5Bog4q8OVhIFC3Ww"
		type="text/javascript"></script>
	
	<script src="http://www.google-analytics.com/urchin.js"
		type="text/javascript">
		    </script>
	</head>
	<body onload="init();">
		<div id="container">
			<div id="header">AJUG Jobs</div>
			<div id="header_menu">
					<ul><li><a href="<c:url value='/'/>">Home</a></li>
						<li><a href="#">Search Jobs</a></li>
						<li><a href="#">Admin</a></li>
						<li><a href="#">Contact</a></li></ul>
			</div>
			<div id="content"><%@ include
				file="/WEB-INF/jsp/includes/messages.jsp"%> <decorator:body />
			</div>
			<div id="footer"><a class="footerLogo"
				href="http://www.jrecruiter.org"
				title="Main website of the jRecruiter project">jRecruiter</a>
			</div>
		</div>
	
		<script type="text/javascript">
	        _uacct = "UA-177507-3";
	        urchinTracker();
	    </script>
	</body>
</html>

