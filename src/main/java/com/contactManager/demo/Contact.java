package com.contactManager.demo;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void validatePhoneNumber() {
        if (this.phoneNumber == null || this.phoneNumber.isBlank()) {
            throw new RuntimeException("Phone Number cannot be null or empty");
        }

        if (this.phoneNumber.length()!=10) {
            throw new RuntimeException("Phone Number should be 10 Digits Long");
        }

        if (!this.phoneNumber.matches("\\d+")) {
            throw new RuntimeException("Phone Number Contain only digits");
        }

        if (!this.phoneNumber.startsWith("0")) {
            throw new RuntimeException("Phone Number must Should start with 0");
        }
    }


}


