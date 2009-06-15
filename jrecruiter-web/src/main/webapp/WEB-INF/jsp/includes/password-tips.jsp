<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

        <div id="passwordTips" style="display: none;">
            <div  class="required infoBox" style="border: none;">
                <ol>
                   <li><spring:message code="jsp.signup.text.password.tip1" /></li>
                   <li><spring:message code="jsp.signup.text.password.tip2" /></li>
                   <li><spring:message code="jsp.signup.text.password.tip3" /></li>
                   <li><spring:message code="jsp.signup.text.password.tip4" /></li>
                   <li><spring:message code="jsp.signup.text.password.tip5" /></li>
                   <li><spring:message code="jsp.signup.text.password.tip6" /></li>
                   <li><spring:message code="jsp.signup.text.password.tip7" /></li>
                </ol>
            </div>
        </div>

<script type="text/javascript">

    jQuery(function() {

      jQuery('#password').bind('keyup', function(event) {
          if ($('#password').val().length > 0) {
                testPassword($('#password').val());
          } else {
                $('#passwordStrength').html('<spring:message code="jsp.signup.label.insert.password" />');
          }
      });
      jQuery('#email').focus();
    });

    var passwordTips = $('#passwordTips').html();
    var qtipParams = {
            content: passwordTips,
            show: 'mouseover',
            hide: 'mouseout',
            position: {
                   corner: {
                      target: 'rightMiddle',
                      tooltip: 'leftMiddle'
                   }
             },
            style: {
                tip: 'leftMiddle' // Notice the corner value is identical to the previously mentioned positioning corners
            }
    };

    $('#password').qtip(qtipParams);
    $('#password2').qtip(qtipParams);
</script>