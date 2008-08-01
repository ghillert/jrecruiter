<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<script type="text/javascript">

    jQuery(function() {


      jQuery('#usesMap').bind('change', function(event) { usesMapChange(); });

      jQuery(':input').bind('focus', function(event) { jQuery(event.target).addClass('selected'); });
      jQuery(':input').bind('blur', function(event) { jQuery(event.target).removeClass('selected'); });

      jQuery('#convertAddressButton').bind('click', function(event) { getCoordinatesFromAddress(); });
      jQuery('#showCoordinatesButton').bind('click', function(event) { showJob('map', jQuery('#longitude').val(), jQuery('#latitude').val(), jQuery('#zoomLevel').val()); });

      usesMapChange();
      document.getElementById('jobTitle').focus();

    });





</script>