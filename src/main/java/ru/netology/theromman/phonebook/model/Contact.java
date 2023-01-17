package ru.netology.theromman.phonebook.model;

public record Contact(
        String phoneNumber,
        String firstName,
        String lastName
) {
    @Override
    public String toString() {
        return phoneNumber + ";" + firstName + ";" + lastName + ";";
    }

    public static Contact fromString(String contactString) {
        var contactAttributes = contactString.split(";");
        return new Contact(contactAttributes[0], contactAttributes[1], contactAttributes[2]);
    }
}
