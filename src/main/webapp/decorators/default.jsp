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
    <jwr:style src="/bundles/all-IE.css" />

    <!-- Java Script Imports -->
    <jwr:script src="/bundles/lib.js"/>

        <script type="text/javascript">
        jQuery(init());

        jQuery(function() {
            jQuery(':input').bind('focus', function(event) { jQuery(event.target).addClass('selected'); });
            jQuery(':input').bind('blur', function(event) { jQuery(event.target).removeClass('selected'); });
        });
    </script>

  </head>
  <body>
    <div class="container">
      <div class="header"><span class="ajug">AJUG</span> <span class="separator">|</span> Jobs</div>
      <div class="header_menu">
          <ul><li><a href="<c:url value='/'/>"><spring:message code="jsp.decorators.default.menu.home"/></a></li>
            <li>
                <a href="<c:url value='/search.html'/>"><spring:message code="jsp.decorators.default.menu.search.jobs"/></a></li>
            <li>
                <a href="<c:url value='/admin/index.html'/>"><spring:message code="jsp.decorators.default.menu.admin"/></a></li>
            <li>
                <a href="#" id="contact"><spring:message code="jsp.decorators.default.menu.about"/></a></li>
            <li class="icon"><a href="<c:url value='/rss/jobs.rss'/>" class="icon" title="<spring:message code="jsp.decorators.default.menu.rss.title"/>">&nbsp;<span><spring:message code="jsp.decorators.default.menu.rss"/></span></a></li>
            <li>             <a href="<c:url value='/s/indeed.xml'/>"              title="<spring:message code="jsp.decorators.default.menu.xml.title"/>"><spring:message code="jsp.decorators.default.menu.xml"/></a></li>
            <li style="margin-right: 1em; float: right;padding: 0.2em 0em;">
                              <c:if test="${pageContext.request.secure}">
          Site is SSL secured
      </c:if></li>
            </ul>

      </div>
      <div class="content"><security:authorize ifAnyGranted="MANAGER, ADMIN">
        <div style="text-align: right; margin-top: -0.5em;">You are logged in as
        <security:authentication property="principal.firstName"/> <security:authentication property="principal.lastName"/> (<security:authentication property="principal.email"/>) | <a href="<c:url value='/logout.html'/>" ><spring:message code="jsp.decorators.default.logout"/></a></div>
      </security:authorize><%@ include
        file="/WEB-INF/jsp/includes/messages.jsp"%> <decorator:body />
      </div>
      <div class="footer"><a class="footerLogo"
        href="http://www.jrecruiter.org"
        title="Main website of the jRecruiter project"><span>j</span>Recruiter (Build: <spring:message code="jrecruiter.build.number"/>)</a>
      </div>
    </div>

       <div id="baseModal" style="display:none">

        <h1 style="padding-left: 1em;">About...</h1>
        <div class="contents" style="background-color: white; margin: 1em;">
                Loading...
        </div>
        <div class="buttons">
          <div class="modalClose"><a href="#" class="button close"><span>&nbsp;</span>Close</a></div>
        </div>
      </div>

        <script type="text/javascript">

        $(document).ready(function () {
            $('#contact').click(function (e) {
                e.preventDefault();
                $('#baseModal').modal({
                    onShow: function (dialog) {
                        $("#baseModal .contents").load("${contactUrl}");
                    },
                    onOpen: function (dialog) {

                        dialog.overlay.fadeIn(200, function () {
                        dialog.container.fadeIn(200, function () {
                        dialog.data.fadeIn(200, function () {

                                });
                            });
                        });

                    },
                    onClose: function (dialog) {

                        dialog.data.fadeOut(200, function () {
                        dialog.container.fadeOut(200, function () {
                        dialog.overlay.fadeOut(200, function () {
                        $.modal.close();
                             });
                            });
                        });
                    }
                });
            });

            $('fieldset').mouseover(function() {
                    $(this).css("border-color", "#E07125");
                }).mouseout(function() {
                    $(this).css("border-color", "");
                });

        });

        </script>

  </body>
</html>

