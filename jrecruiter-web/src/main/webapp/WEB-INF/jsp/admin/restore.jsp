<%@ include file="/WEB-INF/jsp/includes/taglibs-spring.jsp"%>

  <h2><spring:message code="jsp.get-password.title"/></h2>

    <form method="post" action="${ctx}/s/admin/restore" enctype="multipart/form-data">

        <p>
            Restore Backup
        </p>
            <input type="file" name="file"/>
            <input type="submit"/>
    </form>

<script type="text/javascript">
    jQuery(function() {

      jQuery(':input').bind('focus', function(event) { jQuery(event.target).addClass('selected'); });
      jQuery(':input').bind('blur', function(event) { jQuery(event.target).removeClass('selected'); });
      jQuery('#username').focus();

    });
</script>
