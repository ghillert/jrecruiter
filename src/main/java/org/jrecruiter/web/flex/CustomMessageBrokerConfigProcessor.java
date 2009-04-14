package org.jrecruiter.web.flex;

import org.springframework.flex.messaging.config.MessageBrokerConfigProcessor;

import flex.messaging.MessageBroker;
import flex.messaging.services.RemotingService;

public class CustomMessageBrokerConfigProcessor implements
		MessageBrokerConfigProcessor {
	public MessageBroker processAfterStartup(MessageBroker broker) {
		RemotingService remotingService = (RemotingService) broker
				.getServiceByType(RemotingService.class.getName());
		if (remotingService.isStarted()) {
			System.out.println("The Remoting Service has been started with "
					+ remotingService.getDestinations().size()
					+ " Destinations.");
		}
		return broker;
	}

	public MessageBroker processBeforeStartup(MessageBroker broker) {
		return broker;
	}
}
