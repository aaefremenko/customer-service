package com.club.fitness.customer.service.impl;

import org.springframework.stereotype.Service;

import com.club.fitness.customer.kafka.event.CustomerEvent;
import com.club.fitness.customer.kafka.producer.CustomerEventProducer;
import com.club.fitness.customer.model.Customer;
import com.club.fitness.customer.service.CustomerEventService;

@Service
public class CustomerEventServiceImpl implements CustomerEventService {

	private final CustomerEventProducer customerEventProducer;

	public CustomerEventServiceImpl(final CustomerEventProducer customerEventProducer) {
		this.customerEventProducer = customerEventProducer;
	}

	@Override
	public void sendCustomerCreatedEvent(Customer customer) {
		customerEventProducer
				.sendCustomerEvent(new CustomerEvent(customer.getCustomerId().getValue(), "created"));
	}

	@Override
	public void sendCustomerUpdatedEvent(Customer customer) {
		customerEventProducer
				.sendCustomerEvent(new CustomerEvent(customer.getCustomerId().getValue(), "updated"));
	}

	@Override
	public void sendCustomerActivatedEvent(Customer customer) {
		customerEventProducer
				.sendCustomerEvent(new CustomerEvent(customer.getCustomerId().getValue(), "activated"));
	}

	@Override
	public void sendCustomerDeactivatedEvent(Customer customer) {
		customerEventProducer
				.sendCustomerEvent(new CustomerEvent(customer.getCustomerId().getValue(), "deactivated"));
	}

}
