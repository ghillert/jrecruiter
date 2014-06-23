<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<style>

#modalContainer {
  height: 150px;
}

</style>

<h2>User Administration</h2>
<p class="info">
    Located below are all user accounts of the system.  You can either select a user for
    deletion or click on a user in order to modify him/her.
</p>

<s:url id="showUsersUrl"  action="show-users-ajax" includeParams="none"/>
<s:url id="deleteUsersUrl" method="deleteUsers" includeParams="none"/>

<script type="text/javascript">

function checkDelete(userId){
alert(333);
  if (confirm("<s:text name='jsp.userlist.delete.confirm'/>")) {
    return true;
  }
  else {
     return false;
  }

}

function onInvokeAction(id) {

    jQuery.jmesa.setExportToLimit(id, '');

    var parameterString = jQuery.jmesa.createParameterStringForLimit(id);
    var url;

    url = '<s:property value="%{#showUsersUrl}"/>';

    jQuery.get(url + '?' + parameterString, function(data) {
        jQuery("#" + id + 'Div').html(data);
    });
}

jQuery(document).ready(function () {

    jQuery("#confirmDeletion").dialog({
        title: 'Confirming Deletion',
        autoOpen: false,
        bgiframe: true,
        width: 400,
        height: 150,
        modal: true
    });

    jQuery('#deleteButton').click(function(event) {
        event.preventDefault();
        $('#confirmDeletion .message').html("Are you sure that you want to delete " + jQuery("#usersTableDiv input:checked").length + " user(s)?");
        $("#confirmDeletion").dialog("open");
        return false;
    });

    $("#yesButton").click(function() {
        $('#userListForm').submit();
    });

    $("#noButton").click(function() {
        $("#confirmDeletion").dialog("close");
    });

});

</script>

  <s:form id="userListForm" action="delete-user" >
      <s:hidden name="restore"  value="true" />
      <s:if test="users != null && users.size > 0">
        <div id="usersTableDiv" style="margin-bottom: 1em;">
            <%@include file="/WEB-INF/jsp/admin/user-list-table.jsp"%>
        </div>
      </s:if>
      <s:else>
        <p>
            <s:text name="jsp.admin.show-users.message.not.found.users"/>
        </p>
      </s:else>
      <div class="submit">
          <s:submit action="show-users" method="cancel" value="%{getText('jsp._ALL.button.cancel')}"/>
          <s:if test="users != null && users.size > 0">
              <s:submit id="deleteButton" value="%{getText('jsp._ALL.button.delete')}" method="addNewLogger"/>
          </s:if>
      </div>
  </s:form>

    <div id="confirmDeletion" style="display:none">
        <p class="message"></p>
        <div class="buttons" style="width: 130px; margin-left: auto; margin-right: auto;">
          <a href="#" id="yesButton" class="button close" style="float: left; margin-right: 5px;"><span>&nbsp;</span>Yes</a>
          <a href="#" id="noButton" class="button cancel" style="margin-left: 75px;"><span>&nbsp;</span>No</a>
        </div>
    </div>
