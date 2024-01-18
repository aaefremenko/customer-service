package com.club.fitness.customer.repository.mapper;

import org.springframework.stereotype.Component;

import com.club.fitness.customer.model.Customer;
import com.club.fitness.customer.model.CustomerStatus;
import com.club.fitness.customer.repository.entity.CustomerEntity;

@Component
public class CustomerEntityMapper {
	
	private final MembershipEntityMapper membershipEntityMapper;
	
	public CustomerEntityMapper(final MembershipEntityMapper membershipEntityMapper) {
		this.membershipEntityMapper = membershipEntityMapper;
	}

	public Customer fromEntity(final CustomerEntity entity) {
		return new Customer(entity.getId(),
							entity.getUsername(),
							entity.getFirstName(),
							entity.getLastName(),
							entity.getRegistrationDate(),
							entity.getLastModifiedDate(),
							CustomerStatus.valueOf(entity.getStatus()),
							entity.getAddress(),
							entity.getPhoneNumber(),
							entity.getEmail(),
							membershipEntityMapper.fromEntity(entity.getMembership()));
	}
	
	public CustomerEntity fromModel(final Customer customer) {
		return new CustomerEntity(customer.getCustomerId(),
								  customer.getUsername(),
								  customer.getFirstName(),
								  customer.getLastName(),
								  customer.getRegistrationDate(),
								  customer.getLastModifiedDate(),
								  customer.getCustomerStatus().toString(),
								  customer.getAddress(),
								  customer.getPhoneNumber(),
								  customer.getEmail(),
								  membershipEntityMapper.fromModel(customer.getMembership()));
	}
}
