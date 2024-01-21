package com.club.fitness.customer.model;

public class CustomerSearchCriteria {
	private final Username username;
	private final FirstName firstName;
	private final LastName lastName;
	private final Address address;
	private final PhoneNumber phoneNumber;
	private final Email email;
	
	public CustomerSearchCriteria(final Username username,
								  final FirstName firstName,
								  final LastName lastName,
								  final Address address,
								  final PhoneNumber phoneNumber,
								  final Email email) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
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

	public Address getAddress() {
		return address;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public Email getEmail() {
		return email;
	}
	
}
