package org.jrecruiter.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class ContextStartedEventListener implements
                                    ApplicationListener < ContextStartedEvent > {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextStartedEventListener.class);

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {

    }

}
