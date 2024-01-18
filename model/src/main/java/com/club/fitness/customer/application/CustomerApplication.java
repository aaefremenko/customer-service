package com.club.fitness.customer.application;

import org.springframework.stereotype.Component;

import com.club.fitness.customer.exception.NotFoundException;
import com.club.fitness.customer.exception.ValidationException;
import com.club.fitness.customer.model.Customer;
import com.club.fitness.customer.model.CustomerStatus;
import com.club.fitness.customer.service.CustomerService;

@Component
public class CustomerApplication {

	private static final String CUSTOMER_NOT_FOUND_BY_ID = "Customer cannot be found by id: {0}";
	private static final String USERNAME_IS_NOT_UNIQUE = "Username ''{0}'' is not unique";
	private static final String CUSTMORER_IS_DEACTIVATED = "Customer with id: {0} is deactivated";

	private final CustomerService customerService;

	public CustomerApplication(final CustomerService customerService) {
		this.customerService = customerService;
	}

	public Customer createCustomer(final Customer customer) {
		checkUsernameIsUnique(customer);

		return customerService.saveCustomer(customer);
	}

	public Customer updateCustomer(final Customer customer) {
		final var oldCustomer = getCustomerById(customer.getCustomerId());

		checkCustomerIsNotDeactivated(oldCustomer);
		if (oldCustomer.isUsernameChanged(customer)) {
			checkUsernameIsUnique(customer);
		}
		
		return customerService.saveCustomer(oldCustomer.update(customer));
	}

	public Customer getCustomerById(final Long id) {
		return customerService.findCustomerById(id)
				.orElseThrow(NotFoundException.supplier(CUSTOMER_NOT_FOUND_BY_ID, id));
	}

	private void checkUsernameIsUnique(final Customer customer) {
		customerService.findCustomerByUsername(customer.getUsername())
				.map(Customer::getUsername)
				.ifPresent(username -> {
					throw new ValidationException(USERNAME_IS_NOT_UNIQUE,
												  customer.getUsername());
				});
	}

	private void checkCustomerIsNotDeactivated(final Customer customer) {
		if (customer.getCustomerStatus() != CustomerStatus.DEACTIVATED) {
			return;
		}
		throw new ValidationException(CUSTMORER_IS_DEACTIVATED, customer.getCustomerId());
	}

}
