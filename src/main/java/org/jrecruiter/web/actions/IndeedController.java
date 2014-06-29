/*
 * Copyright 2006-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrecruiter.web.actions;

import org.jrecruiter.model.ServerSettings;
import org.jrecruiter.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 */
@Controller
public class IndeedController {

	private @Autowired JobService jobService;
	private @Autowired ServerSettings serverSettings;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	@RequestMapping("/indeed.xml")
	public String execute(ModelMap model) {

		model.addAttribute("jobs", jobService.getJobs());
		model.addAttribute("serverAddress", serverSettings.getServerAddress());

		return "indeedView";
	}

}
