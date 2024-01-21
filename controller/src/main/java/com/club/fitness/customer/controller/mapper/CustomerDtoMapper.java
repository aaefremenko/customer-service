package com.club.fitness.customer.controller.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.club.fitness.customer.controller.dto.input.CustomerInputDto;
import com.club.fitness.customer.controller.dto.output.CustomerOutputDto;
import com.club.fitness.customer.model.Address;
import com.club.fitness.customer.model.Customer;
import com.club.fitness.customer.model.CustomerId;
import com.club.fitness.customer.model.CustomerStatus;
import com.club.fitness.customer.model.Email;
import com.club.fitness.customer.model.FirstName;
import com.club.fitness.customer.model.LastName;
import com.club.fitness.customer.model.PhoneNumber;
import com.club.fitness.customer.model.Username;

@Component
public class CustomerDtoMapper {
	
	private final MembershipDtoMapper membershipDtoMapper;
	
	public CustomerDtoMapper(final MembershipDtoMapper membershipDtoMapper) {
		this.membershipDtoMapper = membershipDtoMapper;
	}
	
	public Customer fromDtoWithId(final Long id, final CustomerInputDto inputDto) {
		return new Customer(Optional.ofNullable(id)
									.map(CustomerId::new)
									.orElse(null),
							new Username(inputDto.username()),
							new FirstName(inputDto.firstName()),
							new LastName(inputDto.lastName()),
							LocalDate.now(),
							LocalDateTime.now(),
							CustomerStatus.REGISTERED,
							Optional.ofNullable(inputDto.address())
									.map(Address::new)
									.orElse(null),
							new PhoneNumber(inputDto.phoneNumber()),
							new Email(inputDto.email()),
							membershipDtoMapper.fromDtoWithId(null, inputDto.membershipInputDto()));
	}
	
	public CustomerOutputDto fromModel(final Customer customer) {
		return new CustomerOutputDto(Optional.ofNullable(customer.getCustomerId())
											 .map(CustomerId::getValue)
											 .orElseThrow(null),
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
									 membershipDtoMapper.fromModel(customer.getMembership()));
	}
	
}
