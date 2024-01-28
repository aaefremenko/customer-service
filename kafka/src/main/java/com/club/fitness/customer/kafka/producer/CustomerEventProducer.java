package com.club.fitness.customer.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.club.fitness.customer.kafka.event.CustomerEvent;

@Service
public class CustomerEventProducer {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerEventProducer.class);
	
	private final KafkaTemplate<String, CustomerEvent> kafkaTemplate;
	private final String topicName;

	public CustomerEventProducer(final KafkaTemplate<String, CustomerEvent> kafkaTemplate,
								 final @Value("${kafka.topic-name}") String topicName) {
		this.kafkaTemplate = kafkaTemplate;
		this.topicName = topicName;
	}
	
	public void sendCustomerEvent(final CustomerEvent customerEvent) {
		logger.debug("sending customer event: {} to the following topic: {}", customerEvent, topicName);
		final var future = kafkaTemplate.send(topicName, customerEvent);
		future.addCallback(event -> logger.debug("sending customer event: {} is successfull", event.getProducerRecord().value()),
						ex -> {
							logger.debug("sending customer event: {} is failure. {}", customerEvent, ex);
							throw new RuntimeException("sending customer event: " + customerEvent + " is failure.");
						});
	}
	
	
}

