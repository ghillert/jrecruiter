<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

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
   var flashvars = {
           amfBrokerUrl: '<s:property value="%{amfBrokerUrl}" escape="false"/>'
         };
         var params = {
         };
         var attributes = {
         };
         swfobject.embedSWF("jobs.swf", "flexContent", "100%", "100%", "9.0.0", "expressInstall.swf", flashvars, params, attributes);
   </script>

  </head>
  <body>
    <div id="flexContent">
      <p>jRecruiter FX</p>
    </div>
  </body>
</html>

