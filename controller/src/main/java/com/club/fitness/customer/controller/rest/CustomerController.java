package com.club.fitness.customer.controller.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.club.fitness.customer.application.CustomerApplication;
import com.club.fitness.customer.controller.dto.input.CustomerInputDto;
import com.club.fitness.customer.controller.dto.output.CustomerOutputDto;
import com.club.fitness.customer.controller.mapper.CustomerDtoMapper;
import com.club.fitness.customer.model.Address;
import com.club.fitness.customer.model.CustomerId;
import com.club.fitness.customer.model.CustomerSearchCriteria;
import com.club.fitness.customer.model.Email;
import com.club.fitness.customer.model.FirstName;
import com.club.fitness.customer.model.LastName;
import com.club.fitness.customer.model.PhoneNumber;
import com.club.fitness.customer.model.Username;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Клиенты", description = "Методы для работы с клиентами")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	private final CustomerApplication customerApplication;
	private final CustomerDtoMapper customerDtoMapper;
	
	public CustomerController(final CustomerApplication customerApplication, 
							  final CustomerDtoMapper customerDtoMapper) {
		this.customerApplication = customerApplication;
		this.customerDtoMapper = customerDtoMapper;
	}
	
	@PostMapping
	@Operation(summary = "Создание нового клиента")
	ResponseEntity<CustomerOutputDto> createCustomer(final @RequestBody CustomerInputDto customerInputDto) {
		logger.debug("Calling createCustomer with params: {}", customerInputDto);
		
		final var customer = customerDtoMapper.fromDtoWithId(null, customerInputDto);
		final var createdCustomer = customerApplication.createCustomer(customer);
		
		return ResponseEntity.ok(customerDtoMapper.fromModel(createdCustomer));
	}
	
	@PutMapping("/{customerId}")
	@Operation(summary = "Обновление информации о клиенте")
	ResponseEntity<CustomerOutputDto> updateCustomer(final @PathVariable("customerId") Long customerId,
													 final @RequestBody CustomerInputDto customerInputDto) {
		logger.debug("Calling updateCustomer with id: {} and params: {}", customerId, customerInputDto);
		
		final var newCustomer = customerDtoMapper.fromDtoWithId(customerId, customerInputDto);
		final var updatedCustomer = customerApplication.updateCustomer(newCustomer);
		
		return ResponseEntity.ok(customerDtoMapper.fromModel(updatedCustomer));
	}
	
	@GetMapping("/{customerId}")
	@Operation(summary = "Получение информации о клиенте по его уникальному идентификатору")
	ResponseEntity<CustomerOutputDto> getCustomerById(
			@PathVariable("customerId")
			@Parameter(description = "Уникальный идентификатор клиента")
			final Long customerId) {
		logger.debug("Calling getCustomerById with id: {}", customerId);
		
		final var customer = customerApplication.getCustomerById(new CustomerId(customerId));
		
		return ResponseEntity.ok(customerDtoMapper.fromModel(customer));
	}
	
	@PutMapping("/{customerId}/activate")
	@Operation(summary = "Активация клиента")
	ResponseEntity<Void> activateCustomer(
			@PathVariable("customerId")
			@Parameter(description = "Уникальный идентификатор клиента")
			final Long customerId) {
		logger.debug("Calling activateCustomer with id: {}", customerId);
		
		customerApplication.activateCustomerWithId(new CustomerId(customerId));
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{customerId}/deactivate")
	@Operation(summary = "Деактивация клиента")
	ResponseEntity<Void> deactivateCustomer(
			@PathVariable("customerId")
			@Parameter(description = "Уникальный идентификатор клиента")
			final Long customerId) {
		logger.debug("Calling deactivateCustomer with id: {}", customerId);
		
		customerApplication.deactivateCustomerWithId(new CustomerId(customerId));
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/search")
	@Operation(summary = "Поиск клиентов по различным критериям")
	ResponseEntity<List<CustomerOutputDto>> searchCustomers(
			@RequestParam(name = "username", required = false)
			@Parameter(description = "Уникальное имя пользователя")
			final String username,
			@RequestParam(name = "firstName", required = false)
			@Parameter(description = "Имя")
			final String firstName,
			@RequestParam(name = "lastName", required = false)
			@Parameter(description = "Фамилия")
			final String lastName,
			@RequestParam(name = "address", required = false)
			@Parameter(description = "Адрес")
			final String address,
			@RequestParam(name = "phoneNumber", required = false)
			@Parameter(description = "Номер телефона")
			final String phoneNumber,
			@RequestParam(name = "email", required = false)
			@Parameter(description = "Электронная почта")
			final String email) {
		logger.debug("Calling searchCustomers with params: username = {}, firstName = {}, lastName = {},"
					 + "address = {}, phoneNumber = {}, email = {}",
				username, firstName, lastName, address, phoneNumber, email);
		
		final var customerSearchCriteria = new CustomerSearchCriteria(Optional.ofNullable(username).map(Username::new).orElse(null),
																	  Optional.ofNullable(firstName).map(FirstName::new).orElse(null),
																	  Optional.ofNullable(lastName).map(LastName::new).orElse(null),
																	  Optional.ofNullable(address).map(Address::new).orElse(null),
																	  Optional.ofNullable(phoneNumber).map(PhoneNumber::new).orElse(null),
																	  Optional.ofNullable(email).map(Email::new).orElse(null));
		
		return ResponseEntity.ok(customerApplication.findAllBy(customerSearchCriteria)
													.stream()
													.map(customerDtoMapper::fromModel)
													.toList());
	}
}
