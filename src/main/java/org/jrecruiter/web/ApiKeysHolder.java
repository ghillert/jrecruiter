package org.jrecruiter.web;


/**
 * Holder Object for Api keys such as for webservices like Google Maps.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class ApiKeysHolder {

    private String googleMapsKey;

    private String reCaptchaKey;



    public String getGoogleMapsKey() {
        return googleMapsKey;
    }

    public void setGoogleMapsKey(String googleMapsKey) {
        this.googleMapsKey = googleMapsKey;
    }

    public String getReCaptchaKey() {
        return reCaptchaKey;
    }

    public void setReCaptchaKey(String reCaptchaKey) {
        this.reCaptchaKey = reCaptchaKey;
    }



}
