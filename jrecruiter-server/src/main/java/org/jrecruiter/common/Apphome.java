package org.jrecruiter.common;

import org.jrecruiter.common.Constants.AppHomeSource;

public final class Apphome {

    private String appHomePath;
    private AppHomeSource appHomeSource;

    /**
     * @return the appHomePath
     */
    public String getAppHomePath() {
        return appHomePath;
    }
    /**
     * @param appHomePath the appHomePath to set
     */
    public void setAppHomePath(String appHomePath) {
        this.appHomePath = appHomePath;
    }
    /**
     * @return the appHomeSource
     */
    public AppHomeSource getAppHomeSource() {
        return appHomeSource;
    }
    /**
     * @param appHomeSource the appHomeSource to set
     */
    public void setAppHomeSource(AppHomeSource appHomeSource) {
        this.appHomeSource = appHomeSource;
    }

}

