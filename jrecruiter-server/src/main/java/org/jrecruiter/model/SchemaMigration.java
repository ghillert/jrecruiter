/*
*	http://www.jrecruiter.org
*
*	Disclaimer of Warranty.
*
*	Unless required by applicable law or agreed to in writing, Licensor provides
*	the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
*	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
*	including, without limitation, any warranties or conditions of TITLE,
*	NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
*	solely responsible for determining the appropriateness of using or
*	redistributing the Work and assume any risks associated with Your exercise of
*	permissions under this License.
*
*/
package org.jrecruiter.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
* This class is used for database versioning and setup purposes.
*
* @author Gunnar Hillert
* @version $Id:Industry.java 201 2008-05-05 12:57:04Z ghillert $
*/
@Entity
public class SchemaMigration  implements java.io.Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 5352730251720839547L;

    // Fields

    /** Primary id of the industry */
    @Id
    private Long version;


    /**
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Long version) {
        this.version = version;
    }

}


