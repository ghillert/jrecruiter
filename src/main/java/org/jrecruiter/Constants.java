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
package org.jrecruiter;

/**
 * Collection of commonmly used enumerations, constants etc.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class Constants {

    /**
     *
     */
    public Constants() {
        super();
    }

    public enum StatsMode { PAGE_HITS, UNIQUE_HITS }

    public enum OfferedBy {

        recruiter(1, "Recruiter", "offeredBy.recruiter.description"),
        company  (2,   "Company",   "offeredBy.company.description");

        Integer id;
        String name;
        String descriptionKey;

        OfferedBy(final Integer id,
                  final String name,
                  final String descriptionKey) {
            this.id = id;
            this.name = name;
            this.descriptionKey = descriptionKey;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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
}
