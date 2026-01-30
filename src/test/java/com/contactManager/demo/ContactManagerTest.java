package com.contactManager.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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

}
