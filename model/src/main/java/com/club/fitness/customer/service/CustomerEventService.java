package com.club.fitness.customer.service;

import com.club.fitness.customer.model.Customer;

public interface CustomerEventService {
	void sendCustomerCreatedEvent(Customer customer);
	void sendCustomerUpdatedEvent(Customer customer);
	void sendCustomerActivatedEvent(Customer customer);
	void sendCustomerDeactivatedEvent(Customer customer);
}
