/**
 *
 */
package org.jrecruiter.web.actions;

import java.util.List;
import java.util.Map;

import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.model.Job;
import com.sun.syndication.feed.synd.SyndEntry;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jrecruiter.common.Constants;
import org.jrecruiter.model.ServerSettings;
import twitter4j.org.json.JSONException;
import twitter4j.org.json.JSONObject;

/**
 * @author Summers Pittman
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public class JobsJsonAction extends BaseAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -211033648423551648L;
    private JSONObject json = new JSONObject();
    private InputStream inputStream;

    private ServerSettings serverSettings;
    
    public InputStream getInputStream() {
        return inputStream;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    @Override
    public String execute() {

        final List<SyndEntry> entries = CollectionUtils.getArrayList();

        final Map<String, String> sortOrders = CollectionUtils.getHashMap();
        sortOrders.put("updateDate", "DESC"); //FIXME
        List<Job> jobs = jobService.getJobs(20, 1, sortOrders, null);

        List<JSONObject> jsonJobs = new ArrayList<JSONObject>(jobs.size());

        for (Job job : jobs) {
            try {
                JSONObject jsonJob = new JSONObject();

                jsonJob.put("title", job.getJobTitle());
                jsonJob.put("company", job.getBusinessName());
                jsonJob.put("location", job.getBusinessCity());
                jsonJob.put("date", job.getUpdateDate().getDate());
                jsonJob.put("month", job.getUpdateDate().getMonth() + 1);
                final String jobUrl = this.serverSettings.getServerAddress() + Constants.ServerActions.JOB_DETAIL.getPath() + "?jobId=" + job.getId();

                jsonJob.put("link", jobUrl);
                jsonJobs.add(jsonJob);
            } catch (JSONException ex) {
                Logger.getLogger(JobsJsonAction.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            json.put("jobs", jsonJobs);
            inputStream = new ByteArrayInputStream(json.toString().getBytes("UTF-8"));
        } catch (JSONException ex) {
            Logger.getLogger(JobsJsonAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(JobsJsonAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

        return SUCCESS;
    }

    public ServerSettings getServerSettings() {
        return serverSettings;
    }

    public void setServerSettings(ServerSettings serverSettings) {
        this.serverSettings = serverSettings;
    }
    
    
}
