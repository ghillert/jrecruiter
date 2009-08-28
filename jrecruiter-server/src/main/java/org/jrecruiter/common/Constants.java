/*
 *  http://www.jrecruiter.org
 *
 *  Disclaimer of Warranty.
 *
 *  Unless required by applicable law or agreed to in writing, Licensor provides
 *  the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
 *  including, without limitation, any warranties or conditions of TITLE,
 *  NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
 *  solely responsible for determining the appropriateness of using or
 *  redistributing the Work and assume any risks associated with Your exercise of
 *  permissions under this License.
 *
 */
package org.jrecruiter.common;

/**
 * Collection of commonly used enumerations, constants etc.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class Constants {

    /** Constructor. */
    public Constants() {
        super();
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

