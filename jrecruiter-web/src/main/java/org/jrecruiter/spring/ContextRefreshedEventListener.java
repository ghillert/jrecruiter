package org.jrecruiter.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements
                                    ApplicationListener < ContextRefreshedEvent > {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }

}
