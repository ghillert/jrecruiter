/**
 *
 */
package org.jrecruiter.dao;
 
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * @author Gunnar Hillert
 * 
 */
public class ConfigurationDaoTNG extends BaseDaoTNG {

	private SettingsDAO settingDao;

	/**
	 * Initialize Logging.
	 */
	public static final Logger LOGGER = Logger
			.getLogger(ConfigurationDaoTNG.class);

	@Test(groups = { "daoIntegrationTest" })
	public void getAllConfigurations() {

		List<org.jrecruiter.model.Configuration> conf = settingDao
				.getAllConfigurations();

		for (org.jrecruiter.model.Configuration setting : conf) {

			LOGGER.info(setting);

		}

	}

	public SettingsDAO getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingsDAO settingDao) {
		this.settingDao = settingDao;
	}

}
