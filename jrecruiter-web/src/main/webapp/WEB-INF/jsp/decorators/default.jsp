<%@include file="/WEB-INF/jsp/includes/taglibs-spring.jsp"%>

<!DOCTYPE html
        PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
  <head>
    <title><decorator:title default="Welcome to jRecruiter" /></title>

    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma"        content="no-cache" />
    <meta http-equiv="Expires"       content="0" />
    <meta http-equiv="content-type"  content="text/html; charset=utf-8" />

    <meta name="author"      content="Gunnar Hillert" />
    <meta name="keywords"    content="Jobs, java, Atlanta, j2ee, java ee, user group" />
    <meta name="description" content="Job Posting Service of the Atlanta Java User Group (AJUG)" />

    <link rel="alternate"     href="<c:url value='/rss/jobs.rss'/>" type="application/rss+xml" title="jRecruiter RSS Feed" />
    <link rel="icon"          href="<c:url value='/favicon.ico'/>"  type="image/x-icon" />
    <link rel="shortcut icon" href="<c:url value='/favicon.ico'/>"  type="image/x-icon" />

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
    <div class="container"><div class="outer-header">
      <div class="header"><span class="ajug">AJUG</span> <span class="separator">|</span> Jobs</div></div>
      <div class="header_menu">
          <ul><li class="icon"><a class="icon-home"    href="<c:url value='/'/>"                                                                                                                 >&nbsp;<span><spring:message  code="jsp.decorators.default.menu.home"/></span></a></li>
              <li class="icon"><a class="icon-search"  href="<c:url value='/search.html'/>"                                                                                                      >&nbsp;<span><spring:message  code="jsp.decorators.default.menu.search.jobs"/></span></a></li>
              <li class="icon"><a class="icon-admin"   href="<c:url value='/admin/index.html'/>"                                                                                                 >&nbsp;<span><spring:message  code="jsp.decorators.default.menu.admin"/></span></a></li>
              <li class="icon"><a class="icon-contact" href="#"  id="contact"                                                                                                                    >&nbsp;<span><spring:message  code="jsp.decorators.default.menu.about"/></span></a></li>
              <li class="icon"><a class="icon-rss"     href="<c:url value='/rss/jobs.rss'/>"                      title="<spring:message code="jsp.decorators.default.menu.rss.title"/>"    >&nbsp;<span><spring:message code="jsp.decorators.default.menu.rss"/></span></a></li>
              <li class="icon"><a class="icon-twitter" href="<c:url value='http://www.twitter.com/ajug_jobs/'/>"  title="<spring:message code="jsp.decorators.default.menu.twitter.title"/>">&nbsp;<span><spring:message code="jsp.decorators.default.menu.twitter"/></span></a></li>
              <li class="icon"><a class="icon-xml"     href="<c:url value='/s/indeed.xml'/>"                      title="<spring:message code="jsp.decorators.default.menu.xml.title"/>"    >&nbsp;<span>&nbsp;</span></a></li>
              <li style="margin-right: 1em; float: right;padding: 0.2em 0em;">
                  <c:if test="${pageContext.request.secure}">Site is SSL secured</c:if>
                  <c:if test="${'DemoContextConfiguration' == springContextMode.code}">[Demo Mode - Embedded Database]</c:if></li>
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
        title="Main website of the jRecruiter project"><span>j</span>Recruiter <spring:message code="jrecruiter.build.version"/>.<spring:message code="jrecruiter.build.number"/></a>
      </div>
    </div>

       <div id="baseModal" style="display:none">
        <div class="contents" style="background-color: white; margin: 1em;">
                Loading...
        </div>
      </div>

        <c:url value="/contact.html" var="contactUrl"/>

        <script type="text/javascript">

        $(document).ready(function () {

            $('.outer-header').add_layer("url('${ctx}/images/icons/beta_2.0_badge.png') no-repeat 90% 50%");

            $("#contact").click(function() {

                $("#baseModal").dialog({
                    title: 'About...',
                    bgiframe: true,
                    width: 400,
                    height: 500,
                    show: 'blind',
                    hide: 'blind',
                    modal: true,
                    buttons: {
                        Ok: function() {
                            $(this).dialog('close');
                        }
                    }
                });

                $("#baseModal").dialog('open').load('${contactUrl}', '#baseModal .contents');

            });


            $('fieldset').mouseover(function() {
                    $(this).css("border-color", "#E07125");
                }).mouseout(function() {
                    $(this).css("border-color", "");
                });

        });

        </script>

        <script type="text/javascript">
        var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
        document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
        </script>
        <script type="text/javascript">
        try {
        var pageTracker = _gat._getTracker("UA-177507-3");
        pageTracker._trackPageview();
        } catch(err) {}</script>

  </body>
</html>

