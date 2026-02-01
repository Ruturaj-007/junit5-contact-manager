package com.contactManager.demo;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManager {
    // ConcurrentHashMap is thread-safe & allows concurrent reads, writes & prevents race condition
    private Map<String, Contact> contactList = new ConcurrentHashMap<>();

    public void addContact(String firstName, String lastName, String phoneNumber) {
    Contact contact = new Contact(firstName, lastName, phoneNumber);
    validateContact(contact);
    checkIfContactAlreadyExists(contact);
    contactList.put(generateKey(contact), contact);
    }

    public Collection<Contact> getAllContacts() {
        return contactList.values();
    }

    private void validateContact(Contact contact) {
        contact.validateFirstName();
        contact.validateLastName();
        contact.validatePhoneNumber();
    }

    private void checkIfContactAlreadyExists(Contact contact) {
        // Duplicate entries are prevented
        if (contactList.containsKey(generateKey(contact))) {
            throw new RuntimeException("Contact already exists");
        }
    }

    private String generateKey(Contact contact) {
        return String.format("%s-%s", contact.getFirstName(), contact.getLastName());
    }

    public void deleteContact(String firstName, String lastName){
        String key = String.format("%s-%s", firstName, lastName);

        if (!contactList.containsKey(key)) {
            throw new RuntimeException("Contact does not exist");
        }

        contactList.remove(key);
    }
}
