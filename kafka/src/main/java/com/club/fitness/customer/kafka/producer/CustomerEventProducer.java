package com.club.fitness.customer.kafka.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.club.fitness.customer.kafka.event.CustomerEvent;

@Service
public class CustomerEventProducer {
	
	private final KafkaTemplate<String, CustomerEvent> kafkaTemplate;
	private final String topicName;

	public CustomerEventProducer(final KafkaTemplate<String, CustomerEvent> kafkaTemplate,
								 final @Value("${kafka.topic-name}") String topicName) {
		this.kafkaTemplate = kafkaTemplate;
		this.topicName = topicName;
	}
	
	public void sendCustomerEvent(final CustomerEvent customerEvent) {
		kafkaTemplate.send(topicName, customerEvent);
	}
	
	
}

