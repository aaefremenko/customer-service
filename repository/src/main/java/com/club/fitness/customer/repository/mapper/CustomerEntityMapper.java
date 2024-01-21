package com.club.fitness.customer.repository.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.club.fitness.customer.model.Address;
import com.club.fitness.customer.model.Customer;
import com.club.fitness.customer.model.CustomerId;
import com.club.fitness.customer.model.CustomerStatus;
import com.club.fitness.customer.model.Email;
import com.club.fitness.customer.model.FirstName;
import com.club.fitness.customer.model.LastName;
import com.club.fitness.customer.model.PhoneNumber;
import com.club.fitness.customer.model.Username;
import com.club.fitness.customer.repository.entity.CustomerEntity;

@Component
public class CustomerEntityMapper {
	
	private final MembershipEntityMapper membershipEntityMapper;
	
	public CustomerEntityMapper(final MembershipEntityMapper membershipEntityMapper) {
		this.membershipEntityMapper = membershipEntityMapper;
	}

	public Customer fromEntity(final CustomerEntity entity) {
		return new Customer(Optional.ofNullable(entity.getId())
									.map(CustomerId::new)
									.orElse(null),
							new Username(entity.getUsername()),
							new FirstName(entity.getFirstName()),
							new LastName(entity.getLastName()),
							entity.getRegistrationDate(),
							entity.getLastModifiedDate(),
							CustomerStatus.valueOf(entity.getStatus()),
							Optional.ofNullable(entity.getAddress())
									.map(Address::new)
									.orElse(null),
							new PhoneNumber(entity.getPhoneNumber()),
							new Email(entity.getEmail()),
							membershipEntityMapper.fromEntity(entity.getMembership()));
	}
	
	public CustomerEntity fromModel(final Customer customer) {
		return new CustomerEntity(Optional.ofNullable(customer.getCustomerId())
										  .map(CustomerId::getValue)
										  .orElse(null),
								  customer.getUsername().getValue(),
								  customer.getFirstName().getValue(),
								  customer.getLastName().getValue(),
								  customer.getRegistrationDate(),
								  customer.getLastModifiedDate(),
								  customer.getCustomerStatus().toString(),
								  Optional.ofNullable(customer.getAddress())
								  		  .map(Address::getValue)
								  		  .orElse(null),
								  customer.getPhoneNumber().getValue(),
								  customer.getEmail().getValue(),
								  membershipEntityMapper.fromModel(customer.getMembership()));
	}
}
