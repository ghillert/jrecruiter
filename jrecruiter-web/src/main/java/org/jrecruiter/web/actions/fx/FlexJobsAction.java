package org.jrecruiter.web.actions.fx;

import java.net.URI;

import org.jrecruiter.model.ServerSettings;
import org.jrecruiter.web.actions.BaseAction;

/**
 * Provides any information needed for the Adobe Flex-based page to render.
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public class FlexJobsAction extends BaseAction {


    /** serialVersionUID. */
    private static final long serialVersionUID = 1628841236427398250L;

    private ServerSettings serverSettings;

    private String amfBrokerUrl;

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    public String execute() throws Exception {

        final URI amfBrokerURI = new URI(serverSettings.getServerAddress() + "/flex/messagebroker/amf");
        amfBrokerUrl = amfBrokerURI.toASCIIString();
        return SUCCESS;
    }

    //~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return the amfBrokerUrl
     */
    public String getAmfBrokerUrl() {
        return amfBrokerUrl;
    }

    /**
     * @param serverSettings the serverSettings to set
     */
    public void setServerSettings(ServerSettings serverSettings) {
        this.serverSettings = serverSettings;
    }

}
