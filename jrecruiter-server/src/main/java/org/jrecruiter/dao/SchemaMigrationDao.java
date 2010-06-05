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
package org.jrecruiter.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.jrecruiter.model.Job;
import org.jrecruiter.model.SchemaMigration;

/**
 * Interface for any schemaMigration-related persistence calls.
 *
 * @author Jerzy Puchala, Gunnar Hillert
 * @version @version $Id: JobDao.java 561 2010-03-04 15:12:02Z ghillert $
 */
public interface SchemaMigrationDao extends GenericDao < SchemaMigration, Long >{

}
