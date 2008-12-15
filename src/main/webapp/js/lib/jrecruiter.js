
// Show the document's title on the status bar
    window.defaultStatus=document.title;

    var map;
    var geocoder;

    var defaultMapLatitude  = 33.74;
    var defaultMapLongitude = -84.38;
    var defaultMapZoomLevel = 10;

function init() {
    DWRUtil.useLoadingMessage();
}

function usesMapChange() {
    var usesMapValue = jQuery('#usesMap').val();

    if (usesMapValue == 'true') {
        document.getElementById('usesMapDiv').style.display = 'block';
        document.getElementById('longitude').value=defaultMapLongitude;
        document.getElementById('latitude').value=defaultMapLatitude;
        document.getElementById('zoomLevel').value=defaultMapZoomLevel;


        longitude = jQuery('#longitude').val();
        latitude  = jQuery('#latitude').val();
        zoomLevel = jQuery('#zoomLevel').val();
        jobDivId  = 'map';

        showJob(jobDivId, longitude, latitude, zoomLevel);

    } else {
        document.getElementById('usesMapDiv').style.display = 'none';
    }
}

function showJob(jobDivId, latitude, longitude, zoomLevel) {

  if (GBrowserIsCompatible()) {
      var point = new GLatLng(parseFloat(latitude), parseFloat(longitude));

    if (map == null) {

          map = new GMap2(document.getElementById(jobDivId));
          map.checkResize();
          map.addControl(new GSmallMapControl());
          map.addControl(new GMapTypeControl());
          map.enableScrollWheelZoom();
          map.setCenter(point, parseFloat(zoomLevel));

    } else {
        map.clearOverlays();
        map.setCenter(point, parseFloat(zoomLevel));
        map.panTo(point);
    }

    map.addOverlay(new GMarker(point));

  } else {
    alert("Sorry but your browser is not compatible with Google Maps,");
    return;
  }
}

function getCoordinatesFromAddress() {

      if (geocoder == null) {
           geocoder = new GClientGeocoder();
      }

      var address = jQuery("#businessAddress1").val()
            + ',' + jQuery('#businessCity').val()
            + ',' + jQuery('#businessState').val()
            + ',' + jQuery('#businessZip').val();

      geocoder.getLocations(address, addAddressToMapCallBack);

}

function addAddressToMapCallBack(response) {
  if (!response || response.Status.code != 200) {
    alert("Sorry, we were unable to geocode that address");
  } else {
    var place = response.Placemark[0];
    document.getElementById('longitude').value=place.Point.coordinates[0];
    document.getElementById('latitude').value=place.Point.coordinates[1];
    showJob('map', place.Point.coordinates[1], place.Point.coordinates[0], jQuery('#zoomLevel').val());
  }
}

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
