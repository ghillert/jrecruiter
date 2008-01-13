<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><fmt:message key="jsp.addjobposting.title" /></h2>

<s:form id="addUserForm">

    <div class="required">
       <label for="jobTitle"><fmt:message key="field.jobTitle" /></label>
       <s:textfield name="jobTitle" id="jobTitle" size="11" tabindex="1"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="businessName"><fmt:message key="field.businessName" /></label>
       <s:textfield name="businessName" id="businessName" size="11" tabindex="2"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="required">
       <label for="industry"><fmt:message key="field.industry" /></label>
       <s:select name="industry.id" id="industry" tabindex="3"
                    list="industries" headerKey="" headerValue="Please select an industry."
                    listValue="name" listKey="id"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>

    <div class="required">
       <label for="offered By"><fmt:message key="field.offered.by" /></label>
       <s:select name="offeredBy" id="offeredBy" tabindex="4"
                    list="offeredBySet" headerKey="" headerValue="Please select..."
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>

    <fieldset style="clear: left;">
        <legend>
            <fmt:message key="jsp.addjobposting.fieldset.contact.information" />
        </legend>
           <div class="required">
               <label for="businessEmail"><fmt:message key="field.email" /></label>
               <s:textfield name="businessEmail" id="businessEmail" size="11" tabindex="5"
                            onfocus="javascript:this.className='selected';"
                            onblur="javascript:this.className='';" />
           </div>
           <div class="optional">
               <label for="website"><fmt:message key="field.website" /></label>
               <s:textfield name="website" id="website" size="11" tabindex="6"
                            onfocus="javascript:this.className='selected';"
                            onblur="javascript:this.className='';" />
               <fmt:message key="field.website.note" />
           </div>
           <div class="optional">
               <label for="businessPhone"><fmt:message key="field.phone" /></label>
               <s:textfield name="businessPhone" id="businessPhone" size="11" tabindex="7"
                            onfocus="javascript:this.className='selected';"
                            onblur="javascript:this.className='';" />
           </div>
    </fieldset>

    <fieldset style="clear: left;">
        <legend>
            <fmt:message key="jsp.addjobposting.job.fieldset.job.location" />
        </legend>
        <div class="required">
           <label for="region"><fmt:message key="field.location" /></label>
           <s:select name="region.id" id="region" tabindex="8"
                        list="regions" headerKey="" headerValue="Please select a region..."
                        listValue="name" listKey="id"
                        onfocus="javascript:this.className='selected';"
                        onblur="javascript:this.className='';" />
        </div>
        <div class="optional">
           <label for="businessAddress1"><fmt:message key="field.address" /></label>
           <s:textfield name="businessAddress1" id="businessAddress1" size="11" tabindex="9"
                        onfocus="javascript:this.className='selected';"
                        onblur="javascript:this.className='';" />
        </div>
        <div class="optional">
           <label for="businessAddress2"><fmt:message key="field.address" /></label>
           <s:textfield name="businessAddress2" id="businessAddress2" size="11" tabindex="10"
                        onfocus="javascript:this.className='selected';"
                        onblur="javascript:this.className='';" />
        </div>
        <div class="optional">
           <label for="salary"><fmt:message key="field.salary" /></label>
           <s:textfield name="salary" id="salary" size="11" tabindex="11"
                        onfocus="javascript:this.className='selected';"
                        onblur="javascript:this.className='';" />
        </div>
        <div class="optional">
           <label for="businessCity"><fmt:message key="field.city" /></label>
           <s:textfield name="businessCity" id="businessCity" size="11" tabindex="12"
                        onfocus="javascript:this.className='selected';"
                        onblur="javascript:this.className='';" />
        </div>
        <div class="optional">
           <label for="businessState"><fmt:message key="field.state" /></label>
           <s:textfield name="businessState" id="businessState" size="11" tabindex="13"
                        onfocus="javascript:this.className='selected';"
                        onblur="javascript:this.className='';" />
        </div>

        <div class="optional">
           <label for="businessZip"><fmt:message key="field.zip" /></label>
           <s:textfield name="businessZip" id="businessZip" size="11" tabindex="14"
                        onfocus="javascript:this.className='selected';"
                        onblur="javascript:this.className='';" />
        </div>
    </fieldset>

    <fieldset style="clear: left;">
        <legend>
            <fmt:message key="jsp.addjobposting.job.fieldset.geographic.information" />
        </legend>
        <div class="optional">
           <label for="usesMap">
                   Give your jobs more exposure! Do you like to provide geographic
                   details of the job posting's locations?</label>
           <s:select name="usesMap" id="usesMap" list="yesNoList"
                     onchange="javascript:usesMapChange();" tabindex="15"/>
        </div>

        <div id="usesMapDiv" style="display: none;">
            <div class="required">
                <label for="longitude"><fmt:message key="field.longitude" /></label>
                <s:textfield name="longitude" id="longitude" size="5" tabindex="16"
                            onfocus="javascript:this.className='selected';"
                            onblur="javascript:this.className='';" />
            </div>
            <div class="required">
            <label for="longitude"><fmt:message key="field.latitude" /></label>
            <s:textfield name="latitude" id="latitude" size="5" tabindex="17"
                         onfocus="javascript:this.className='selected';"
                         onblur="javascript:this.className='';" />
            </div>
            <div class="submit">
                <input type="button" value="Generate" onclick="getLocation();" tabindex="17"/>
                <input type="button" value="Show" onclick="getLocation();" tabindex="18"/>
            </div>

            <div id="map"
                 style="clear: both; width: 500px; height: 300px; margin: 1em auto 0 auto; border: 1px solid black;"></div>
        </div>
    </fieldset>

    <fieldset style="clear: left;">
        <legend>
            <fmt:message key="jsp.addjobposting.job.fieldset.job.details" />
        </legend>
        <div class="required">
           <label for="description"><fmt:message key="field.jobDescription" /></label>
           <s:textarea name="description" id="description" rows="15" tabindex="19" cssStyle="width: 500px"
                       onfocus="javascript:this.className='selected';"
                       onblur="javascript:this.className='';" />
        </div>
        <div class="optional">
           <label for="jobRestrictions"><fmt:message key="field.jobRestrictions" /></label>
           <s:textarea name="jobRestrictions" id="jobRestrictions" rows="10" tabindex="20" cssStyle="width: 500px"
                       onfocus="javascript:this.className='selected';"
                       onblur="javascript:this.className='';" />
           <fmt:message key="jobposting.texarea.note" />
        </div>
    </fieldset>
    <fieldset>
            <div class="submit">
                <s:submit value="Submit" method="save" tabindex="21"/><s:submit value="Cancel" method="cancel" tabindex="22"/>
            </div>
    </fieldset>
    <fmt:message key="jobposting.add.label.mandatory" />
</s:form>
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

    function usesMapChange() {
        var usesMapValue = document.getElementById('usesMap').value;

        if (usesMapValue == 'true') {
            document.getElementById('usesMapDiv').style.display = 'block';
        } else {
            document.getElementById('usesMapDiv').style.display = 'none';
        }
    }

    usesMapChange();
    $('jobTitle').focus();
</script>

