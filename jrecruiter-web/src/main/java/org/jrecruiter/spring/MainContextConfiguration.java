package org.jrecruiter.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@ImportResource({"classpath:spring/mainApplicationContext.xml",
                 "classpath:spring/applicationContext-mail.xml",
                 "classpath:spring/applicationContext-security.xml"})
public class MainContextConfiguration {

       // private static final Logger LOGGER = LoggerFactory.getLogger(DemoContextConfiguration.class);

}
