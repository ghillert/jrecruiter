package org.jrecruiter.spring;

import org.jrecruiter.service.SystemSetupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Event Listener that is triggered when the application context is loaded.
 * This listener waits until all services are available and then populates
 * the database with demo data. This also allows for populating seed and test data
 * for integration testing.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class ContextRefreshedEventListener implements
                                    ApplicationListener < ContextRefreshedEvent > {

	/** Special Dao for creating and setting up the database */
    private @Autowired SystemSetupService systemSetupService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextRefreshedEventListener.class);

    /**
     *
     *
     */
    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //From: http://forum.springsource.org/showthread.php?t=84312&page=2

        if (event.getApplicationContext().getParent() == null) {

            LOGGER.info("Parent Spring Context started.");

            if (!systemSetupService.isDatabaseSetup()) {

                LOGGER.info("jRecruiter Database is not setup, yet. Initializing DB...");

                systemSetupService.createDatabase();

                LOGGER.info("jRecruiter Database is not setup, yet. Populating Seed Data...");

                systemSetupService.loadAndRestoreSeedData();

                LOGGER.info("jRecruiter Database is not setup, yet. Populating Test Data...");
            }

        }

    }

}
