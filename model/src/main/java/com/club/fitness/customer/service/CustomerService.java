package com.club.fitness.customer.service;

import java.util.List;
import java.util.Optional;

import com.club.fitness.customer.model.Customer;
import com.club.fitness.customer.model.CustomerSearchCriteria;

public interface CustomerService {
	Customer saveCustomer(Customer customer);
	Optional<Customer> findCustomerByUsername(String username);
	Optional<Customer> findCustomerById(Long id);
	List<Customer> findAllBy(CustomerSearchCriteria customerSearchCriteria);
}
