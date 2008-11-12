<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2>Job Administration</h2>
<p class="info">
    Located below are all jobs you have access to. You can either select a job for
    deletion or click on a job posted in order to modify it.
</p>

<div id="main">
  <s:form id="jobListId">
    <s:if test="jobs != null && jobs.size() > 0">
        <%@include file="/WEB-INF/jsp/admin/joblistTable.jsp"%>
    </s:if>
    <s:else>
        <p>
            <s:text name="message.noAvailableJobs"/>
        </p>
    </s:else>
      <br/><br/>
      <s:submit method="cancel" value="%{getText('jobposting.button.cancel')}"/>
      <s:if test="jobs != null && jobs.size() > 0">
          <s:submit id="deleteButton" method="delete" value="%{getText('jobposting.button.delete')}"/>
      </s:if>
  </s:form>
</div>



<div id="confirm" style="display:none">
    <a href="#" title="Close" class="modalCloseX modalClose">x</a>
    <div class="header"><span>Confirm</span></div>
    <p class="message"></p>
    <div class="buttons">
        <div class="no modalClose">No</div><div class="yes">Yes</div>
    </div>
</div>

<script type="text/javascript">

 var confirmHelper = { open: function (dialog) {

                     dialog.overlay.fadeIn(200, function () {
                    dialog.container.fadeIn(200, function () {
                    dialog.data.fadeIn(200, function () {

                            });
                        });
                    });

                 },
                 close: function (dialog) {

                    dialog.data.fadeOut(200, function () {
                    dialog.container.fadeOut(200, function () {
                    dialog.overlay.fadeOut(200, function () {
                        $.modal.close();
                            });
                        });
                    });

                 }
               };

jQuery(document).ready(function () {

        jQuery('#deleteButton').click(function(event) {
                event.preventDefault();
                confirm("Are you sure that you wnat to delete " + jQuery("#jobListId input:checked").length + " job posting(s)?", function () {
                                      alert(true);});
                return false;
         });
});

function confirm(message, callback) {
    $('#confirm').modal({
        close:false,
        overlayId:'confirmModalOverlay',
        containerId:'confirmModalContainer',
        onShow: function (dialog) {
            dialog.data.find('.message').append(message);

            // if the user clicks "yes"
            dialog.data.find('.yes').click(function () {
                // call the callback
                if ($.isFunction(callback)) {
                    callback.apply();
                }
                // close the dialog
                $.modal.close();
            });
        },
        onOpen:  confirmHelper.open,
        onClose: confirmHelper.close
    });
}
</script>