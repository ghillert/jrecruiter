package org.jrecruiter.common;


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

    private String twitterUsername;
    private String twitterPassword;

    private String bitlyUsername;
    private String bitlyPassword;


    //~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

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

    /**
     * @return the twitterUsername
     */
    public String getTwitterUsername() {
        return twitterUsername;
    }

    /**
     * @param twitterUsername the twitterUsername to set
     */
    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    /**
     * @return the twitterPassword
     */
    public String getTwitterPassword() {
        return twitterPassword;
    }

    /**
     * @param twitterPassword the twitterPassword to set
     */
    public void setTwitterPassword(String twitterPassword) {
        this.twitterPassword = twitterPassword;
    }

    /**
     * @return the bitlyUsername
     */
    public String getBitlyUsername() {
        return bitlyUsername;
    }

    /**
     * @param bitlyUsername the bitlyUsername to set
     */
    public void setBitlyUsername(String bitlyUsername) {
        this.bitlyUsername = bitlyUsername;
    }

    /**
     * @return the bitlyPassword
     */
    public String getBitlyPassword() {
        return bitlyPassword;
    }

    /**
     * @param bitlyPassword the bitlyPassword to set
     */
    public void setBitlyPassword(String bitlyPassword) {
        this.bitlyPassword = bitlyPassword;
    }

}
