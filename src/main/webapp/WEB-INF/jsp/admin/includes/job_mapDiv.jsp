<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
    <fieldset style="clear: left;">
        <legend>
            <spring:message code="jsp.admin.add-job.job.fieldset.geographic.information" />
        </legend>
        <div class="optional">
           <label for="usesMap"><spring:message code="jsp.job_mapDiv.text.uses.map" /></label>
           <s:select name="model.job.usesMap" id="usesMap" list="yesNoList" tabindex="15"/>
        </div>

        <div id="usesMapDiv" style="display: block;">
            <div class="required">
                <label for="longitude"><spring:message code="jsp._ALL.job.field.longitude" />*</label>
                <s:textfield name="model.job.longitude" id="longitude" size="10" tabindex="16"/>
            </div>
            <div class="required">
            <label for="longitude"><spring:message code="jsp._ALL.job.field.latitude" />*</label>
            <s:textfield name="model.job.latitude" id="latitude" size="10" tabindex="17"/>
            </div>
            <div class="required">
            <label for="zoomLevel"><spring:message code="jsp._ALL.job.field.zoomLevel" />*</label>
            <s:textfield name="model.job.zoomLevel" id="zoomLevel" size="3" tabindex="18"/>
            </div>
            <div class="submit">
                <input id="convertAddressButton"    type="button" value="<spring:message code='jsp.job_mapDiv.button.convert.address'/>"
                /><input id="showCoordinatesButton" type="button" value="<spring:message code='jsp.job_mapDiv.button.show.coordinates'/>"/>
            </div>

            <div id="map"
                 style="clear: both; width: 500px; height: 300px; margin: 1em auto 0 auto; border: 1px solid black;"></div>
        </div>
    </fieldset>