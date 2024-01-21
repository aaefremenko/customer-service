package com.club.fitness.customer.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.club.fitness.customer.exception.ValidationException;

public class Customer {
	
	private final CustomerId customerId;
	private final Username username;
	private final FirstName firstName;
	private final LastName lastName;
	private final LocalDate registrationDate;
	private final LocalDateTime lastModifiedDate;
	private final CustomerStatus customerStatus;
	private final Address address;
	private final PhoneNumber phoneNumber;
	private final Email email;
	private final Membership membership;
	
	public Customer(final CustomerId customerId,
					final Username username,
					final FirstName firstName,
					final LastName lastName,
					final LocalDate registrationDate,
					final LocalDateTime lastModifiedDate,
					final CustomerStatus customerStatus,
					final Address address,
					final PhoneNumber phoneNumber,
					final Email email,
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
		return !username.getValue().equals(newCustomer.username.getValue());
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
	
	public boolean isRegistered() {
		return customerStatus == CustomerStatus.REGISTERED;
	}
	
	public boolean isActive() {
		return customerStatus == CustomerStatus.ACTIVE;
	}
	
	public boolean isDeactivated() {
		return customerStatus == CustomerStatus.DEACTIVATED;
	}
	
	public boolean hasValidMembership(final LocalDate currentDate) {
		return membership.isValid(currentDate);
	}
	
	public Customer activateCustomer() {
		return new Customer(customerId,
							username,
							firstName,
							lastName,
							registrationDate,
							LocalDateTime.now(),
							CustomerStatus.ACTIVE,
							address,
							phoneNumber,
							email,
							membership);
	}
	
	public Customer deactivateCustomer() {
		return new Customer(customerId,
							username,
							firstName,
							lastName,
							registrationDate,
							LocalDateTime.now(),
							CustomerStatus.DEACTIVATED,
							address,
							phoneNumber,
							email,
							membership);
	}
	
	private void validate() {
		if (username == null) {
			throw new ValidationException("username is null");
		}
		if (firstName == null) {
			throw new ValidationException("firstName is null");
		}
		if (lastName == null) {
			throw new ValidationException("lastName is null");
		}
		if (registrationDate == null) {
			throw new ValidationException("registrationDate is null");
		}
		if (lastModifiedDate == null) {
			throw new ValidationException("lastModifiedDate is null");
		}
		if (customerStatus == null) {
			throw new ValidationException("customerStatus is null");
		}
		if (phoneNumber == null) {
			throw new ValidationException("phoneNumber is null");
		}
		if (email == null) {
			throw new ValidationException("email is null");
		}
		if (membership == null) {
			throw new ValidationException("memebership is null");
		}
	}
	
	public CustomerId getCustomerId() {
		return customerId;
	}
	
	public Username getUsername() {
		return username;
	}
	
	public FirstName getFirstName() {
		return firstName;
	}
	
	public LastName getLastName() {
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
	
	public Address getAddress() {
		return address;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	
	public Email getEmail() {
		return email;
	}
	
	public Membership getMembership() {
		return membership;
	}
	
}
