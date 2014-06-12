package org.jrecruiter.spring;

import org.jrecruiter.service.SystemSetupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Spring Event Listener that is triggered when the application context is loaded.
 * This listener waits until all services are available and then populates
 * the database with demo data. This also allows for populating seed and test data
 * for integration testing.
 *
 * @author Gunnar Hillert
 *
 */
public class ContextRefreshedEventListener implements
									ApplicationListener < ContextRefreshedEvent > {

	/** Special Dao for creating and setting up the database */
	private @Autowired SystemSetupService systemSetupService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ContextRefreshedEventListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {

			LOGGER.info("Parent Spring Context started.");

			if (event.getApplicationContext().getParent() == null) {

				if (!systemSetupService.isDatabaseSetup()) {
					LOGGER.info("Setting up database...");
					systemSetupService.setupDatabase();
				}
				else {
					LOGGER.info("Database already setup.");
				}

			}
		}
	}
}
