package com.club.fitness.customer.service;

import java.util.List;
import java.util.Optional;

import com.club.fitness.customer.model.Customer;
import com.club.fitness.customer.model.CustomerId;
import com.club.fitness.customer.model.CustomerSearchCriteria;
import com.club.fitness.customer.model.Username;

public interface CustomerService {
	Customer saveCustomer(Customer customer);
	Optional<Customer> findCustomerByUsername(Username username);
	Optional<Customer> findCustomerById(CustomerId id);
	List<Customer> findAllBy(CustomerSearchCriteria customerSearchCriteria);
}
