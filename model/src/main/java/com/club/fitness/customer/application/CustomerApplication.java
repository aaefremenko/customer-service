package com.club.fitness.customer.application;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.club.fitness.customer.exception.NotFoundException;
import com.club.fitness.customer.exception.ValidationException;
import com.club.fitness.customer.model.Customer;
import com.club.fitness.customer.model.CustomerId;
import com.club.fitness.customer.model.CustomerSearchCriteria;
import com.club.fitness.customer.service.CustomerEventService;
import com.club.fitness.customer.service.CustomerService;

@Component
public class CustomerApplication {

	private static final String CUSTOMER_NOT_FOUND_BY_ID = "Customer cannot be found by id: {0}";
	private static final String USERNAME_IS_NOT_UNIQUE = "Username ''{0}'' is not unique";
	private static final String CUSTOMER_IS_DEACTIVATED = "Customer with id: {0} is deactivated";
	private static final String CUSTOMER_CAN_NOT_BE_ACTIVATED = "Customer with id: {0} cannot be activated";

	private final CustomerService customerService;
	private final CustomerEventService customerEventService;

	public CustomerApplication(final CustomerService customerService,
							   final CustomerEventService customerEventService) {
		this.customerService = customerService;
		this.customerEventService = customerEventService;
	}

	public Customer createCustomer(final Customer customer) {
		checkUsernameIsUnique(customer);

		final var savedCustomer = customerService.saveCustomer(customer);
		customerEventService.sendCustomerCreatedEvent(savedCustomer);
		
		return savedCustomer;
	}

	public Customer updateCustomer(final Customer customer) {
		final var oldCustomer = getCustomerById(customer.getCustomerId());

		checkCustomerIsNotDeactivated(oldCustomer);
		if (oldCustomer.isUsernameChanged(customer)) {
			checkUsernameIsUnique(customer);
		}
		
		final var savedCustomer = customerService.saveCustomer(oldCustomer.update(customer));
		customerEventService.sendCustomerUpdatedEvent(savedCustomer);
		
		return savedCustomer;
	}

	public Customer getCustomerById(final CustomerId id) {
		return customerService.findCustomerById(id)
				.orElseThrow(NotFoundException.supplier(CUSTOMER_NOT_FOUND_BY_ID, id.getValue()));
	}

	public void activateCustomerWithId(final CustomerId id) {
		final var customer = getCustomerById(id);
		
		if (customer.isActive()) {
			return;
		}
		
		checkCustomerCanBeActivated(customer);
		
		final var savedCustomer = customerService.saveCustomer(customer.activateCustomer());
		customerEventService.sendCustomerActivatedEvent(savedCustomer);
 	}
	
	public void deactivateCustomerWithId(final CustomerId id) {
		final var customer = getCustomerById(id);
		
		if (customer.isDeactivated()) {
			return;
		}
		
		final var savedCustomer = customerService.saveCustomer(customer.deactivateCustomer());
		customerEventService.sendCustomerDeactivatedEvent(savedCustomer);
 	}
	
	public List<Customer> findAllBy(final CustomerSearchCriteria customerSearchCriteria) {
		return customerService.findAllBy(customerSearchCriteria);
	}
	
	private void checkUsernameIsUnique(final Customer customer) {
		customerService.findCustomerByUsername(customer.getUsername())
				.map(Customer::getUsername)
				.ifPresent(username -> {
					throw new ValidationException(USERNAME_IS_NOT_UNIQUE,
												  customer.getUsername().getValue());
				});
	}

	private void checkCustomerIsNotDeactivated(final Customer customer) {
		if (!customer.isDeactivated()) {
			return;
		}
		throw new ValidationException(CUSTOMER_IS_DEACTIVATED, customer.getCustomerId().getValue());
	}

	private void checkCustomerCanBeActivated(final Customer customer) {
		if ((customer.isDeactivated() || customer.isRegistered())
				&& customer.hasValidMembership(LocalDate.now())) {
			return;
		}
		throw new ValidationException(CUSTOMER_CAN_NOT_BE_ACTIVATED, customer.getCustomerId().getValue());
	}
}
