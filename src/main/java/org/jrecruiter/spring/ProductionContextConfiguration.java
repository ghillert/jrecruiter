package org.jrecruiter.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProductionContextConfiguration {

        private static final Logger LOGGER = LoggerFactory.getLogger(DemoContextConfiguration.class);

        public ProductionContextConfiguration() {
            super();
            LOGGER.info("Loading Production Spring Configuration");
        }

}
