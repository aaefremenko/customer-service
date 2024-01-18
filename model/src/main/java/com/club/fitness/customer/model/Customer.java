package com.club.fitness.customer.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer {
	
	private final Long customerId;
	private final String username;
	private final String firstName;
	private final String lastName;
	private final LocalDate registrationDate;
	private final LocalDateTime lastModifiedDate;
	private final CustomerStatus customerStatus;
	private final String address;
	private final String phoneNumber;
	private final String email;
	private final Membership membership;
	
	public Customer(final Long customerId,
					final String username,
					final String firstName, final String lastName,
					final LocalDate registrationDate,
					final LocalDateTime lastModifiedDate,
					final CustomerStatus customerStatus,
					final String address,
					final String phoneNumber,
					final String email,
					final Membership membership) {
		this.customerId = customerId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = registrationDate;
		this.lastModifiedDate = lastModifiedDate;
		this.customerStatus = customerStatus;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.membership = membership;
		
		validate();
	}
	
	public boolean isUsernameChanged(final Customer newCustomer) {
		return !username.equals(newCustomer.username);
	}
	
	public Customer update(final Customer newCustomer) {
		return new Customer(customerId,
							newCustomer.username,
							newCustomer.firstName,
							newCustomer.lastName,
							registrationDate,
							newCustomer.lastModifiedDate,
							customerStatus,
							newCustomer.address,
							newCustomer.phoneNumber,
							newCustomer.email,
							membership.update(newCustomer.membership));
	}
	
	private void validate() {
		
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	
	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public CustomerStatus getCustomerStatus() {
		return customerStatus;
	}
	
	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Membership getMembership() {
		return membership;
	}
	
}
