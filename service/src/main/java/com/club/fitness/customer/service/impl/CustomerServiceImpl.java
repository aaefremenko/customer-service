package com.club.fitness.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.club.fitness.customer.model.Customer;
import com.club.fitness.customer.model.CustomerId;
import com.club.fitness.customer.model.CustomerSearchCriteria;
import com.club.fitness.customer.model.Username;
import com.club.fitness.customer.repository.entity.CustomerEntity;
import com.club.fitness.customer.repository.jpa.CustomerRepository;
import com.club.fitness.customer.repository.mapper.CustomerEntityMapper;
import com.club.fitness.customer.repository.specification.CustomerSpecifications;
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
	public Optional<Customer> findCustomerByUsername(final Username username) {
		return customerRepository.findOneByUsername(username.getValue()).map(customerEntityMapper::fromEntity);
	}

	@Override
	public Optional<Customer> findCustomerById(final CustomerId id) {
		return customerRepository.findById(id.getValue()).map(customerEntityMapper::fromEntity);
	}
	

	@Override
	public List<Customer> findAllBy(final CustomerSearchCriteria customerSearchCriteria) {
		Specification<CustomerEntity> spec = null;
		
		if (customerSearchCriteria.getUsername() != null) {
			spec = (spec == null)
					? Specification.where(CustomerSpecifications.hasUsername(customerSearchCriteria.getUsername().getValue())) 
					: spec.and(CustomerSpecifications.hasUsername(customerSearchCriteria.getUsername().getValue()));
		}
		if (customerSearchCriteria.getFirstName() != null) {
			spec = (spec == null)
					? Specification.where(CustomerSpecifications.hasFirstName(customerSearchCriteria.getFirstName().getValue())) 
					: spec.and(CustomerSpecifications.hasFirstName(customerSearchCriteria.getFirstName().getValue()));
		}
		if (customerSearchCriteria.getLastName() != null) {
			spec = (spec == null)
					? Specification.where(CustomerSpecifications.hasLastName(customerSearchCriteria.getLastName().getValue()))
					: spec.and(CustomerSpecifications.hasLastName(customerSearchCriteria.getLastName().getValue()));
		}
		if (customerSearchCriteria.getAddress() != null) {
			spec = (spec == null)
					? Specification.where(CustomerSpecifications.hasAddress(customerSearchCriteria.getAddress().getValue()))
					: spec.and(CustomerSpecifications.hasAddress(customerSearchCriteria.getAddress().getValue()));
		}
		if (customerSearchCriteria.getPhoneNumber() != null) {
			spec = (spec == null)
					? Specification.where(CustomerSpecifications.hasPhoneNumber(customerSearchCriteria.getPhoneNumber().getValue()))
					: spec.and(CustomerSpecifications.hasPhoneNumber(customerSearchCriteria.getPhoneNumber().getValue()));
		}
		if (customerSearchCriteria.getEmail() != null) {
			spec = (spec == null)
					? Specification.where(CustomerSpecifications.hasEmail(customerSearchCriteria.getEmail().getValue()))
					: spec.and(CustomerSpecifications.hasEmail(customerSearchCriteria.getEmail().getValue()));
		}
		
		return customerRepository.findAll(spec)
								 .stream()
								 .map(customerEntityMapper::fromEntity)
								 .toList();
	}
	
	
}
