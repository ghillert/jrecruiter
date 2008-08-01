<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
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