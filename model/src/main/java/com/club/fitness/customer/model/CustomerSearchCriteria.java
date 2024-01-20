package com.club.fitness.customer.model;

public class CustomerSearchCriteria {
	private final String username;
	private final String firstName;
	private final String lastName;
	private final String address;
	private final String phoneNumber;
	private final String email;
	
	public CustomerSearchCriteria(final String username,
								  final String firstName,
								  final String lastName,
								  final String address,
								  final String phoneNumber,
								  final String email) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
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

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}
	
	
}
