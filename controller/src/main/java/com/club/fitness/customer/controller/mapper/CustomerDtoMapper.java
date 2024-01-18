package com.club.fitness.customer.controller.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.club.fitness.customer.controller.dto.input.CustomerInputDto;
import com.club.fitness.customer.controller.dto.output.CustomerOutputDto;
import com.club.fitness.customer.model.Customer;
import com.club.fitness.customer.model.CustomerStatus;

@Component
public class CustomerDtoMapper {
	
	private final MembershipDtoMapper membershipDtoMapper;
	
	public CustomerDtoMapper(final MembershipDtoMapper membershipDtoMapper) {
		this.membershipDtoMapper = membershipDtoMapper;
	}
	
	public Customer fromDtoWithId(final Long id, final CustomerInputDto inputDto) {
		return new Customer(id,
							inputDto.username(),
							inputDto.firstName(),
							inputDto.lastName(),
							LocalDate.now(),
							LocalDateTime.now(),
							CustomerStatus.REGISTERED,
							inputDto.address(),
							inputDto.phoneNumber(),
							inputDto.email(),
							membershipDtoMapper.fromDtoWithId(null, inputDto.membershipInputDto()));
	}
	
	public CustomerOutputDto fromModel(final Customer customer) {
		return new CustomerOutputDto(customer.getCustomerId(),
				customer.getUsername(),
				customer.getFirstName(),
				customer.getLastName(),
				customer.getRegistrationDate(),
				customer.getLastModifiedDate(),
				customer.getCustomerStatus().toString(),
				customer.getAddress(),
				customer.getPhoneNumber(),
				customer.getEmail(),
				membershipDtoMapper.fromModel(customer.getMembership()));
	}
	
}
