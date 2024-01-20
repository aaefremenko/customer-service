package com.club.fitness.customer.controller.rest;

import java.util.List;

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
import com.club.fitness.customer.model.CustomerSearchCriteria;

@RestController
@RequestMapping("/api/v1/customers")
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
	ResponseEntity<CustomerOutputDto> createCustomer(final @RequestBody CustomerInputDto customerInputDto) {
		logger.debug("Calling createCustomer with params: {}", customerInputDto);
		
		final var customer = customerDtoMapper.fromDtoWithId(null, customerInputDto);
		final var createdCustomer = customerApplication.createCustomer(customer);
		
		return ResponseEntity.ok(customerDtoMapper.fromModel(createdCustomer));
	}
	
	@PutMapping("/{customerId}")
	ResponseEntity<CustomerOutputDto> updateCustomer(final @PathVariable("customerId") Long customerId,
													 final @RequestBody CustomerInputDto customerInputDto) {
		logger.debug("Calling updateCustomer with id: {} and params: {}", customerId, customerInputDto);
		
		final var newCustomer = customerDtoMapper.fromDtoWithId(customerId, customerInputDto);
		final var updatedCustomer = customerApplication.updateCustomer(newCustomer);
		
		return ResponseEntity.ok(customerDtoMapper.fromModel(updatedCustomer));
	}
	
	@GetMapping("/{customerId}")
	ResponseEntity<CustomerOutputDto> getCustomerById(final @PathVariable("customerId") Long customerId) {
		logger.debug("Calling getCustomerById with id: {}", customerId);
		
		final var customer = customerApplication.getCustomerById(customerId);
		
		return ResponseEntity.ok(customerDtoMapper.fromModel(customer));
	}
	
	@PutMapping("/{customerId}/activate")
	ResponseEntity<Void> activateCustomer(final @PathVariable("customerId") Long customerId) {
		logger.debug("Calling activateCustomer with id: {}", customerId);
		
		customerApplication.activateCustomerWithId(customerId);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{customerId}/deactivate")
	ResponseEntity<Void> deactivateCustomer(final @PathVariable("customerId") Long customerId) {
		logger.debug("Calling deactivateCustomer with id: {}", customerId);
		
		customerApplication.deactivateCustomerWithId(customerId);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/search")
	ResponseEntity<List<CustomerOutputDto>> searchCustomers(final @RequestParam(name = "username", required = false) String username,
															final @RequestParam(name = "firstName", required = false) String firstName,
															final @RequestParam(name = "lastName", required = false) String lastName,
															final @RequestParam(name = "address", required = false) String address,
															final @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
															final @RequestParam(name = "email", required = false) String email) {
		logger.debug("Calling searchCustomers with params: username = {}, firstName = {}, lastName = {},"
					 + "address = {}, phoneNumber = {}, email = {}",
				username, firstName, lastName, address, phoneNumber, email);
		
		final var customerSearchCriteria = new CustomerSearchCriteria(username,
																	  firstName,
																	  lastName,
																	  address,
																	  phoneNumber,
																	  email);
		
		return ResponseEntity.ok(customerApplication.findAllBy(customerSearchCriteria)
													.stream()
													.map(customerDtoMapper::fromModel)
													.toList());
	}
}
