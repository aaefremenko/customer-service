package com.club.fitness.customer.service;

import java.util.Optional;

import com.club.fitness.customer.model.Customer;

public interface CustomerService {
	Customer saveCustomer(Customer customer);
	Optional<Customer> findCustomerByUsername(String username);
	Optional<Customer> findCustomerById(Long id);
}
