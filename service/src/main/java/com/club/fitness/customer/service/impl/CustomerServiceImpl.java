package com.club.fitness.customer.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.club.fitness.customer.model.Customer;
import com.club.fitness.customer.repository.jpa.CustomerRepository;
import com.club.fitness.customer.repository.mapper.CustomerEntityMapper;
import com.club.fitness.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerEntityMapper customerEntityMapper;

	public CustomerServiceImpl(final CustomerRepository customerRepository,
			final CustomerEntityMapper customerEntityMapper) {
		this.customerRepository = customerRepository;
		this.customerEntityMapper = customerEntityMapper;
	}

	@Override
	public Customer saveCustomer(final Customer customer) {
		final var customerEntity = customerEntityMapper.fromModel(customer);

		return customerEntityMapper.fromEntity(customerRepository.save(customerEntity));
	}

	@Override
	public Optional<Customer> findCustomerByUsername(final String username) {
		return customerRepository.findOneByUsername(username).map(customerEntityMapper::fromEntity);
	}

	@Override
	public Optional<Customer> findCustomerById(final Long id) {
		return customerRepository.findById(id).map(customerEntityMapper::fromEntity);
	}

}
