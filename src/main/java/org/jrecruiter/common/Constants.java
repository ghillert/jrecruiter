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
package org.jrecruiter.common;

/**
 * Collection of commonly used enumerations, constants etc.
 *
 * @author Gunnar Hillert
 */
public class Constants {

	/** Constructor. */
	public Constants() {
		super();
	}

	/**
	 */
	public enum AppHomeSource {

		SYSTEM_PROPERTY,
		ENVIRONMENT_VARIABLE,
		USER_DIRECTORY()

	}

	/** Defines the roles of the system */
	public enum Roles { ADMIN, MANAGER }

	/** Defines the roles of the system */
	public enum CommongKeyIds {

		OTHER(Long.valueOf(1)),
		UNDEFINED(Long.valueOf(-1));

		private Long id;

		private CommongKeyIds(Long id) {
			this.id = id;
		}

		public Long getId() {
			return id;
		}

	}

	/**
	 * Defines if a job was posted by a company (direct-hire) or
	 * by a recruiter.
	 */
	public enum OfferedBy {

		UNDEFINED("-1", "Undefined"),
		RECRUITER("Recruiter", "class.enum.offeredBy.recruiter.description"),
		COMPANY  ("Company",   "class.enum.offeredBy.company.description");

		String name;
		String descriptionKey;

		/**
		 * Constructor.
		 *
		 * @param name The name for display puposes.
		 * @param descriptionKey Provides description from the resource bundle.
		 */
		OfferedBy(final String name,
				  final String descriptionKey) {
			this.name = name;
			this.descriptionKey = descriptionKey;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescriptionKey() {
			return descriptionKey;
		}

		public void setDescriptionKey(String descriptionKey) {
			this.descriptionKey = descriptionKey;
		}
	}

	/**
	 * Denotes whether the job posting is active or disabled
	 * (not shown in the search results)
	 */
	public enum JobStatus {

		ACTIVE("Active",     "jobStatus.active.description"),
		DISABLED("Disabled", "jobStatus.disabled.description");

		String name;
		String descriptionKey;

		/**
		 * Constructor.
		 *
		 * @param name The name for display purposes.
		 * @param descriptionKey Provides description from the resource bundle.
		 */
		JobStatus(
				final String name,
				final String descriptionKey) {
			this.name = name;
			this.descriptionKey = descriptionKey;
		}

		public String getName() {
			return name;
		}

		public String getDescriptionKey() {
			return descriptionKey;
		}

	}


	/**
	 * Denotes whether the job posting is active or disabled
	 * (not shown in the search results)
	 */
	public enum UserAuthenticationType {

		USERNAME_PASSWORD(1L,  "userAuthenticationType.label.username_password"),
		OPEN_ID(          2L,  "userAuthenticationType.label.open_id");

		Long   id;
		String labelKey;

		/**
		 * Constructor.
		 *
		 * @param name The name for display purposes.
		 * @param descriptionKey Provides description from the resource bundle.
		 */
		UserAuthenticationType(
				final Long   id,
				final String labelKey) {
			this.id = id;
			this.labelKey = labelKey;
		}

		public Long getId() {
			return id;
		}

		public String getLabelKey() {
			return labelKey;
		}

		public static UserAuthenticationType fromId(final Long id) {

			if (id == null) {
				throw new IllegalArgumentException("Id must not be null.");
			}

			for (UserAuthenticationType e : UserAuthenticationType.values()) {
				if (e.id.equals(id)) {
					return e;
				}
			}

			return null;
		}

	}

	/**
	 * Defines if a job was posted by a company (direct-hire) or
	 * by a recruiter.
	 */
	public enum ServerActions {

		ACCOUNT_VERIFICATION("/registration/account-verification.html"),
		JOB_DETAIL("/job-detail.html");

		private String path;

		/**
		 * Constructor.
		 *
		 * @param path
		 */
		ServerActions(final String path) {
			this.path = path;
		}

		public String getPath() {
			return this.path;
		}

	}


}

