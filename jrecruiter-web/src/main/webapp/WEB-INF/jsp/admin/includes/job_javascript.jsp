<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<script type="text/javascript">

    jQuery(function() {


      jQuery('#usesMap').bind('change', function(event) { usesMapChange(); });

      jQuery(':input').bind('focus', function(event) { jQuery(event.target).addClass('selected'); });
      jQuery(':input').bind('blur', function(event) { jQuery(event.target).removeClass('selected'); });

      jQuery('#convertAddressButton').bind('click', function(event) { getCoordinatesFromAddress(); });

      jQuery('#industry').bind('change', function(event) {
        value = jQuery('#industry').val();

        if (value == 1) {
            document.getElementById('industryOtherDiv').style.display = 'block';
        } else {
            document.getElementById('industryOtherDiv').style.display = 'none';
        }
      });

      jQuery('#region').bind('change', function(event) {
        value = jQuery('#region').val();

        if (value == 1) {
            document.getElementById('regionOtherDiv').style.display = 'block';
        } else {
            document.getElementById('regionOtherDiv').style.display = 'none';
        }
      });


   jQuery('#convertAddressButton').bind('click', function(event) {

      getCoordinatesFromAddress(); });
      usesMapChange();

    });

jQuery('#showCoordinatesButton').bind('click', function(event) {
    showJob('map', jQuery('#latitude').val(), jQuery('#longitude').val(), jQuery('#zoomLevel').val());

  });




</script>