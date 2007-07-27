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
package org.jrecruiter.dao.hibernate;

import java.util.List;

import org.jrecruiter.dao.ConfigurationDao;
import org.jrecruiter.dao.StatisticDao;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Statistic;


/**
 *
 * @author Gunnar Hillert
 * @version @version $Id: SettingsDAOHibernate.java 24 2006-05-18 03:09:15Z ghillert $
 */
public class StatisticDaoHibernate extends GenericDaoHibernate< Statistic, Long>
								   implements StatisticDao {

    /**
     * User Dao.
     */
    private ConfigurationDao configurationDao;

    /**
     * @param userDao The userDao to set.
     */
    public void setConfigurationDao(ConfigurationDao configurationDao) {
        this.configurationDao = configurationDao;
    }

    /**
     * Constructor.
     *
     */
    private StatisticDaoHibernate() {
    	super(Statistic.class);
    }



    public Configuration get(String key) {
        Configuration configuration = (Configuration) getHibernateTemplate().load(Configuration.class, key);
        return configuration;
    }

    /**
     *
     */
    public List<Configuration> getAllConfigurations() {

        List < Configuration > configurations = (List<Configuration>) getHibernateTemplate()
        .find("from Configuration");

       return configurations;

    }

    public void update(Configuration configuration) {
        getHibernateTemplate().update(configuration);

    }



}
