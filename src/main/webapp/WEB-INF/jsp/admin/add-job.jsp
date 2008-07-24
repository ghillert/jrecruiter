<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><fmt:message key="jsp.addjobposting.title" /></h2>

<s:form id="addUserForm">

    <div class="required">
       <label for="jobTitle"><fmt:message key="field.jobTitle" /></label>
       <s:textfield name="jobTitle" id="jobTitle" size="40" tabindex="1"/>
    </div>
    <div class="required">
       <label for="businessName"><fmt:message key="field.businessName" /></label>
       <s:textfield name="businessName" id="businessName" size="40" tabindex="2"/>
    </div>
    <div class="required">
       <label for="industry"><fmt:message key="field.industry" /></label>
       <s:select name="industry.id" id="industry" tabindex="3"
                    list="industries" headerKey="" headerValue="Please select an industry."
                    listValue="name" listKey="id"/>
    </div>
    <div class="optional">
       <label for="salary"><fmt:message key="field.salary" /></label>
       <s:textfield name="salary" id="salary" size="11" tabindex="4"/>
    </div>

    <div class="required">
       <label for="offered By"><fmt:message key="field.offered.by" /></label>
       <s:select name="offeredBy" id="offeredBy" tabindex="5"
                    list="offeredBySet" headerKey="" headerValue="Please select..."/>
    </div>

    <fieldset style="clear: left;">
        <legend>
            <fmt:message key="jsp.addjobposting.fieldset.contact.information" />
        </legend>
           <div class="required">
               <label for="businessEmail"><fmt:message key="field.email" /></label>
               <s:textfield name="businessEmail" id="businessEmail" size="22" tabindex="6"/>
           </div>
           <div class="optional">
               <label for="website"><fmt:message key="field.website" /></label>
               <s:textfield name="website" id="website" size="22" tabindex="7" />
               <fmt:message key="field.website.note" />
           </div>
           <div class="optional">
               <label for="businessPhone"><fmt:message key="field.phone" /></label>
               <s:textfield name="businessPhone" id="businessPhone" size="15" tabindex="8"/>
           </div>
    </fieldset>

    <fieldset style="clear: left;">
        <legend>
            <fmt:message key="jsp.addjobposting.job.fieldset.job.location" />
        </legend>
        <div class="required">
           <label for="region"><fmt:message key="field.location" /></label>
           <s:select name="region.id" id="region" tabindex="9"
                        list="regions" headerKey="" headerValue="Please select a region..."
                        listValue="name" listKey="id"/>
        </div>
        <div class="optional">
           <label for="businessAddress1"><fmt:message key="field.address" /></label>
           <s:textfield name="businessAddress1" id="businessAddress1" size="22" tabindex="10"/>
        </div>
        <div class="optional">
           <label for="businessAddress2"><fmt:message key="field.address" /></label>
           <s:textfield name="businessAddress2" id="businessAddress2" size="22" tabindex="11"/>
        </div>
        <div class="optional">
           <label for="businessCity"><fmt:message key="field.city" /></label>
           <s:textfield name="businessCity" id="businessCity" size="11" tabindex="12"/>
        </div>
        <div class="optional">
           <label for="businessState"><fmt:message key="field.state" /></label>
           <s:textfield name="businessState" id="businessState" size="11" tabindex="13"/>
        </div>

        <div class="optional">
           <label for="businessZip"><fmt:message key="field.zip" /></label>
           <s:textfield name="businessZip" id="businessZip" size="11" tabindex="14"/>
        </div>
    </fieldset>

    <fieldset style="clear: left;">
        <legend>
            <fmt:message key="jsp.addjobposting.job.fieldset.geographic.information" />
        </legend>
        <div class="optional">
           <label for="usesMap">
                   Give your jobs more exposure! Do you like to show the job posting on a map?</label>
           <s:select name="usesMap" id="usesMap" list="yesNoList" tabindex="15"/>
        </div>

        <div id="usesMapDiv" style="display: block;">
            <div class="required">
                <label for="longitude"><fmt:message key="field.longitude" /></label>
                <s:textfield name="longitude" id="longitude" size="5" tabindex="16"/>
            </div>
            <div class="required">
            <label for="longitude"><fmt:message key="field.latitude" /></label>
            <s:textfield name="latitude" id="latitude" size="5" tabindex="17"/>
            </div>
            <div class="required">
            <label for="zoomLevel"><fmt:message key="field.zoomLevel" /></label>
            <s:textfield name="zoomLevel" id="zoomLevel" size="5" tabindex="18"/>
            </div>
            <div class="submit">
                <input id="convertAddressButton"    type="button" value="Convert Address" class="submitBtn"
                /><input id="showCoordinatesButton" type="button" value="Show Coordinates" class="submitBtn"/>
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
           <s:textarea name="description" id="description" rows="15" tabindex="21" cssStyle="width: 500px"/>
        </div>
        <div class="optional">
           <label for="jobRestrictions"><fmt:message key="field.jobRestrictions" /></label>
           <s:textarea name="jobRestrictions" id="jobRestrictions" rows="10" tabindex="22" cssStyle="width: 500px"/>
           <fmt:message key="jobposting.texarea.note" />
        </div>
    </fieldset>
    <fieldset>
      <div class="submit">
          <s:submit value="Submit" method="save" tabindex="23" cssClass="submitBtn"/><s:submit value="Cancel" method="cancel" tabindex="24" cssClass="submitBtn"/>
      </div>
    </fieldset>
    <fmt:message key="jobposting.add.label.mandatory" />
</s:form>
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

    jQuery(document).unload(function() {GUnload();});



</script>

