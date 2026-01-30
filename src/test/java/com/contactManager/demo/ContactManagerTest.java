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

	@Test
	public void shouldThrowExceptionWhenNumberIsNull() {
		Contact contact = new Contact("John", "Doe", null);

		assertThrows(RuntimeException.class, () -> {
			contact.validatePhoneNumber();
		});
	}

	@Test
	public void shouldThrowExceptionWhenNumberIsEmpty() {
		Contact contact = new Contact("John", "Doe", "");

		assertThrows(RuntimeException.class, () -> {
			contact.validatePhoneNumber();
		});
	}

	@Test
	public void shouldThrowExceptionWhenPhoneNumberHasLetters() {
		Contact contact = new Contact("John", "Doe", "012345678a");

		assertThrows(RuntimeException.class, () -> {
			contact.validatePhoneNumber();
		});
	}

	@Test
	public void shouldThrowExceptionWhenPhoneNumberDoesNotStartWith0() {
		Contact contact = new Contact("John", "Doe", "9123456789");

		assertThrows(RuntimeException.class, () -> {
			contact.validatePhoneNumber();
		});
	}

	@Test
	public void shouldThrowExceptionWhenFirstNameIsNull() {
		Contact contact = new Contact(null, "Doe", "0123456789");

		assertThrows(RuntimeException.class, ()-> {
			contact.validateFirstName();
		});
	}

	@Test
	public void shouldThrowExceptionWhenFirstNameIsBlank() {
		Contact contact = new Contact("", "Doe", "0123456789");

		assertThrows(RuntimeException.class, ()-> {
			contact.validateFirstName();
		});
	}

	@Test
	public void shouldThrowExceptionWhenLastNameIsNull() {
		Contact contact = new Contact("John", null, "0123456789");

		assertThrows(RuntimeException.class, ()-> {
			contact.validateLastName();
		});
	}

	@Test
	public void shouldThrowExceptionWhenLastNameIsBlank() {
		Contact contact = new Contact("John", "", "0123456789");

		assertThrows(RuntimeException.class, ()-> {
			contact.validateLastName();
		});
	}




}
