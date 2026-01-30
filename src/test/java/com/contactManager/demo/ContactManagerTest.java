package com.contactManager.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {

	@Test
	public void shouldCreateContact() {
		// Test data
		String firstname = "John";
		String lastname = "Doe";
		String phone = "0123456789";

		Contact contact = new Contact(firstname, lastname, phone);

		assertEquals("John", contact.getFirstName());
		assertEquals("Doe", contact.getLastName());
		assertEquals("0123456789", contact.getPhoneNumber());
	}

	@Test
	public void shouldThrowExceptionWhenNumberIsInvalid() {
		// Invalid data
		Contact contact = new Contact("John", "Doe", "12345");

		// This should throw an exception
		assertThrows(RuntimeException.class, () -> {
			contact.validatePhoneNumber();
		});
	}
}
