package org.jrecruiter.gwt.server;

import org.jrecruiter.gwt.client.GreetingService;
import org.jrecruiter.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	private @Autowired JobService jobService;

	public String greetServer(String input) {
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		return "jobService= " + jobService + ";;;" + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>WHYYYYYYYYYYYYYYYYYYYY:<br>" + userAgent;
	}
}
