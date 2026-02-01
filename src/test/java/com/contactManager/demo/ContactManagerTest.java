package com.contactManager.demo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {

	private ContactManager contactManager;

	@BeforeEach
	public void setup() {
		System.out.println("Instantiating Contact Manager");
		contactManager =  new ContactManager();
	}

	@BeforeAll
	public static void setupAll() {
		System.out.println("===Starting all tests===");
	}

	@Test
	public void shouldCreateContact() {
		contactManager.addContact("John", "Doe", "0123456789");

		assertFalse(contactManager.getAllContacts().isEmpty());
		assertEquals(1, contactManager.getAllContacts().size());
	}

	@Test
	public void shouldCreateContactObject() {
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
	public void shouldThrowExceptionWhenContactAlreadyExists() {
		contactManager.addContact("John", "Doe", "0123456789");

		assertThrows(RuntimeException.class, () -> {
			contactManager.addContact("John", "Doe", "0123456789");
		});
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

	@Nested
	@DisplayName("Phone Number Validation Tests")
	class PhoneNumberTests {

		@Test
		@DisplayName("Should throw exception when phone is null")
		public void shouldThrowExceptionWhenNumberIsNull() {
			Contact contact = new Contact("John", "Doe", null);

			assertThrows(RuntimeException.class, () -> {
				contact.validatePhoneNumber();
			});
		}

		@Test
		@DisplayName("Should throw exception when phone is empty")
		public void shouldThrowExceptionWhenNumberIsEmpty() {
			Contact contact = new Contact("John", "Doe", "");

			assertThrows(RuntimeException.class, () -> {
				contact.validatePhoneNumber();
			});
		}

		@ParameterizedTest // run this tests multiple times
		@ValueSource(strings = {"0123456789", "0987654321", "0111111111"})
		@DisplayName("Should accept valid phone numbers")
		public void ShouldAcceptValidPhoneNumbers(String phoneNumber) {
			contactManager.addContact("John", "Doe", phoneNumber);
			assertFalse(contactManager.getAllContacts().isEmpty());
		}
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

	@Test
	@DisplayName("Should delete contact")
	public void ShouldDeleteContact() {
		contactManager.addContact("Raju", "Mistri", "0113456789");
		contactManager.deleteContact("Raju", "Mistri");

		assertEquals(0, contactManager.getAllContacts().size());
	}

	@ParameterizedTest
	@CsvSource({
			"John, Doe, 0123456789",
			"Jane, Smith, 0987654321",
			"Bob, Johnson, 0555555555"
	})
	public void ShouldCreateMultipleContacts(String firstName, String lastName, String phone) {
		contactManager.addContact(firstName, lastName, phone);
		assertFalse(contactManager.getAllContacts().isEmpty());
	}

	@Test
	@DisplayName("Should throw exception when deleting non-existent contact")
	public void shouldThrowExceptionWhenDeletingNonExistentContact() {
		assertThrows(RuntimeException.class, () -> {
			contactManager.deleteContact("NonExistent", "Person");
		});
	}

	@AfterEach
	public void tearDown() {
		System.out.println("Test Completed - cleaning up");
	}

	@AfterAll
	public static void tearDownAll() {
		System.out.println("===All tests Completed===");
	}
}
