<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><fmt:message key="admin.add.job.title" /></h2>
<form:form method="post" id="addJobForm">
	<form:errors path="*" cssClass="formError"/>

	<table class="jobposting">
	    <tr>
	      	<td><label for="jobTitle"><fmt:message key="field.jobTitle" /></label>*</td>
	      	<td>
	      		<form:input  path="jobTitle" id="jobTitle" size="11" tabindex="1"   onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
	      		<form:errors path="jobTitle" cssClass="fieldError"/>
	      	</td>
	      	<td><label for="businessName"><fmt:message key="field.businessName" /></label>*</td>
	      	<td>
	      		<form:input  path="businessName" id="businessName" size="11" tabindex="2" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessName" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td><label for="businessLocation"><fmt:message key="field.location" /></label></td>
	      	<td>
	      		<form:input  path="businessLocation" id="businessLocation" size="11" tabindex="3" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessLocation" cssClass="fieldError"/>
	      	</td>
	      	<td><label for="businessAddress1"><fmt:message key="field.address" /></label></td>
	      	<td>
	      		<form:input  path="businessAddress1" id="businessAddress1" size="11" tabindex="4" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessAddress1" cssClass="fieldError"/>
	      	</td>
	    </tr>
	    <tr>
	      	<td><label for="industry"><fmt:message key="field.industry" /></label></td>
	      	<td>
	      		<form:input  path="industry" id="industry" size="11" tabindex="5" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="industry" cssClass="fieldError"/>
	      	</td>
	      	<td><label for="businessAddress2"><fmt:message key="field.address" /></label></td>
	      	<td>
	      		<form:input  path="businessAddress2" id="businessAddress2" size="11" tabindex="6" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessAddress2" cssClass="fieldError"/>
	      	</td>
	    </tr>
	    <tr>
	      	<td><label for="salary"><fmt:message key="field.salary" /></label></td>
	      	<td>
	      		<form:input  path="salary" id="salary" size="11" tabindex="7" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="salary" cssClass="fieldError"/>
	      	</td>
	      	<td><label for="businessCity"><fmt:message key="field.city" /></label></td>
	      	<td>
	      		<form:input  path="businessCity" id="businessCity" size="11" tabindex="8" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessCity" cssClass="fieldError"/>
	      	</td>
	    </tr>
	    <tr>
	      	<td><label for="businessPhone"><fmt:message key="field.phone" /></label></td>
	      	<td>
	      		<form:input  path="businessPhone" id="businessPhone" size="11" tabindex="9" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessPhone" cssClass="fieldError"/>
	      	</td>
	      	<td><label for="businessState"><fmt:message key="field.state" /></label></td>
	      	<td>
	      		<form:input  path="businessState" id="businessState" size="11" tabindex="10" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessState" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td><label for="businessEmail"><fmt:message key="field.email" /></label>*</td>
	      	<td>
	      		<form:input  path="businessEmail" id="businessEmail" size="11" tabindex="11" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessEmail" cssClass="fieldError"/>
	      	<td><label for="businessZip"><fmt:message key="field.zip" /></label></td>
	      	<td>
	      		<form:input  path="businessZip" id="businessZip" size="11" tabindex="12" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessZip" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td>Longitude/Latitude</td>
	      	<td>
	      		<form:input  path="longitude" id="longitude" size="5" tabindex="12" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="longitude" cssClass="fieldError"/>
                <form:input  path="latitude" id="latitude" size="5" tabindex="12" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="latitude" cssClass="fieldError"/>
            </td>
	      	<td><label for="website"><fmt:message key="field.website" /></label></td>
	      	<td>
	      		<form:input  path="website" id="website" size="11" tabindex="13" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="website" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td>&nbsp;</td>
	      	<td><input type="button" value="Generate" onclick="getLocation();"/><input type="button" value="Show" onclick="getLocation();"/></td>
	      	<td colspan="2"><fmt:message key="field.website.note" /></td>
	    </tr>
	    <tr>
	      	<td><label for="description"><fmt:message key="field.jobDescription" /></label>*</td>
	      	<td colspan="3">
	      		<form:textarea  path="description" id="description" rows="15" tabindex="14" cssStyle="width: 500px" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors    path="description" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td><label for="jobRestrictions"><fmt:message key="field.jobRestrictions" /></label></td>
	      	<td colspan="3">
	      		<form:textarea  path="jobRestrictions" id="jobRestrictions" rows="10" tabindex="15" cssStyle="width: 500px"  onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors    path="jobRestrictions" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td>&nbsp;</td>
	      	<td colspan="3"><fmt:message key="jobposting.texarea.note" /></td>
	    </tr>
	    <tr>
	      	<td>&nbsp;</td>
	      	<td>
	      		<input type="submit" name="submit" value="<fmt:message key='jobposting.button.add'/>"    onclick="$('addJobForm').submit();"/>
	      		<input type="submit" name="cancel" value="<fmt:message key='jobposting.button.cancel'/>" onclick="$('addJobForm').submit();"/>
		  	</td>
	      	<td>&nbsp;</td>
	      	<td>&nbsp;</td>
	    </tr>
	    <tr>
	      	<td>&nbsp; </td>
	      	<td colspan="3"><fmt:message key="jobposting.add.label.mandatory" /></td>
	    </tr>
	  </table>

<div id="map" style="width: 500px; height: 300px"></div>

<script type="text/javascript">

    var map;
    var geocoder;

        function load() {
      if (GBrowserIsCompatible()) {
          map = new GMap2(document.getElementById("map"));
          map.addControl(new GSmallMapControl());
          map.addControl(new GMapTypeControl());
          
          map.setCenter(new GLatLng(33.74, -84.38), 13);
          geocoder = new GClientGeocoder();
      }
    }    
         load();

     function addAddressToMap(response) {
      map.clearOverlays();
      if (!response || response.Status.code != 200) {
        alert("Sorry, we were unable to geocode that address");
      } else {
        place = response.Placemark[0];
          alert(place);
        point = new GLatLng(place.Point.coordinates[1],
                            place.Point.coordinates[0]);
        $('longitude').value=place.Point.coordinates[1];
        $('latitude').value=place.Point.coordinates[0];

        marker = new GMarker(point);
        map.addOverlay(marker);
        marker.openInfoWindowHtml(place.address + '<br>' +
          '<b>Country code:</b> ' + place.AddressDetails.Country.CountryNameCode);
      }
    }

    function getLocation() {
      var address = $F('businessAddress1') + ',' + $F('businessCity') + ',' +  $F('businessState') + ',' + $F('businessZip');
      alert(address);
      geocoder.getLocations(address, addAddressToMap);
    }


</script>
  </form:form>
