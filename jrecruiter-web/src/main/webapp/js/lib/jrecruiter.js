
// Show the document's title on the status bar
    window.defaultStatus=document.title;

    var map;
    var geocoder;

    var defaultMapLatitude  = 33.74;
    var defaultMapLongitude = -84.38;
    var defaultMapZoomLevel = 10;

function init() {
   // DWRUtil.useLoadingMessage();
}

function usesMapChange() {
    var usesMapValue = jQuery('#usesMap').val();

    if (usesMapValue == 'true') {

        document.getElementById('usesMapDiv').style.display = 'block';

        if ($('latitude').val() == '' && $('longitude').val()) {
            document.getElementById('longitude').value=defaultMapLongitude;
            document.getElementById('latitude').value=defaultMapLatitude;
            document.getElementById('zoomLevel').value=defaultMapZoomLevel;
        }

        longitude = jQuery('#longitude').val();
        latitude  = jQuery('#latitude').val();
        zoomLevel = jQuery('#zoomLevel').val();
        jobDivId  = 'map';

        if (isNumeric(longitude) && isNumeric(latitude) && isNumeric(zoomLevel)) {
            showJob(jobDivId, latitude, longitude, zoomLevel);
        }

    } else {
        document.getElementById('usesMapDiv').style.display = 'none';
    }
}

function isNumeric(strString)
//  check for valid numeric strings
{
var strValidChars = "0123456789.-";
var strChar;
var blnResult = true;

if (strString.length == 0) return false;

//  test strString consists of valid characters listed above
for (i = 0; i < strString.length && blnResult == true; i++)
   {
   strChar = strString.charAt(i);
   if (strValidChars.indexOf(strChar) == -1)
      {
      blnResult = false;
      }
   }
return blnResult;
}

function showJob(jobDivId, latitude, longitude, zoomLevel) {

  var latlng = new google.maps.LatLng(latitude, longitude);
  var myOptions = {
      zoom: zoomLevel,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  var map = new google.maps.Map(document.getElementById(jobDivId),
        myOptions);

  var marker = new google.maps.Marker({
      position: latlng,
      map: map
  });
}

function getCoordinatesFromAddress() {

      if (geocoder == null) {
           geocoder = new google.maps.Geocoder();
      }

      var address = jQuery("#businessAddress1").val()
            + ',' + jQuery('#businessCity').val()
            + ',' + jQuery('#businessState').val()
            + ',' + jQuery('#businessZip').val();

      geocoder.geocode( { 'address': address}, function(results, status) {
          if (status == google.maps.GeocoderStatus.OK) {
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location
            });

            document.getElementById('longitude').value=results[0].geometry.location.longitude;
            document.getElementById('latitude').value=results[0].geometry.location.latitude;
            showJob('map', results[0].geometry.location.longitude, results[0].geometry.location.latitude, jQuery('#zoomLevel').val());

          } else {
            alert("Geocode was not successful for the following reason: " + status);
          }
        });


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
        onShow: function (dialog) {
            dialog.data.find('.message').append(message);

            // if the user clicks "yes"
            dialog.data.find('.close').click(function () {
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

