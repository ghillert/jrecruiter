<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<style>

#modalContainer {
  height: 150px;
}

</style>

<h2>Job Administration</h2>
<p class="info">
    Located below are all jobs you have access to. You can either select a job for
    deletion or click on a job posted in order to modify it.
</p>

<s:url id="showJobsUrl"  action="show-jobs-ajax" includeParams="none"/>

<div id="main">
  <s:form id="jobListForm" action="delete-jobs">
    <s:if test="jobs != null && jobs.size() > 0">
        <div id="jobListTableDiv" style="margin-bottom: 1em;">
            <%@include file="/WEB-INF/jsp/admin/joblistTable.jsp"%>
        </div>
    </s:if>
    <s:else>
        <p>
            <s:text name="jsp.admin.show-jobs.message.noAvailableJobs"/>
        </p>
    </s:else>
    <div class="submit">
      <s:submit method="cancel" value="%{getText('jsp.admin.show-jobs.button.cancel')}"/>
      <s:if test="jobs != null && jobs.size() > 0">
          <s:submit id="deleteButton" method="delete" value="%{getText('jsp.admin.show-jobs.button.delete')}"/>
      </s:if>
    </div>
  </s:form>
</div>

<div id="confirm" style="display:none">
    <h1 style="padding-left: 1em;">Confirm</h1>
    <p class="message"></p>
    <div class="buttons">
      <a href="#" class="button close"><span>&nbsp;</span>Yes</a>
      <a href="#" class="modalClose button cancel"><span>&nbsp;</span>No</a>
    </div>
</div>


<script type="text/javascript">

$(function() {
    $('input.checkbox').click(function (e) {
        $(e).parent().parent().css('background-color', 'red');
    });
});

jQuery(document).ready(function () {

/*         jQuery('#deleteButton').click(function(event) {
                event.preventDefault();
                confirm("Are you sure that you wnat to delete " + jQuery("#jobListForm input:checked").length + " job posting(s)?", function () {
                    $('#jobListForm').submit();
                });
                return false;
         }); */
});

function onInvokeAction(id) {

    jQuery.jmesa.setExportToLimit(id, '');

    var parameterString = jQuery.jmesa.createParameterStringForLimit(id);
    var url;

    url = '<s:property value="%{#showJobsUrl}"/>';

    jQuery.get(url + '?' + parameterString, function(data) {
        jQuery("#" + id + 'Div').html(data);
    });
}
</script>