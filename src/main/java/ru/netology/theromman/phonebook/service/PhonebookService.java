package ru.netology.theromman.phonebook.service;

import ru.netology.theromman.phonebook.model.Contact;

import java.util.Collection;

public interface PhonebookService {
    void createContact(Contact contact);
    Collection<Contact> getAllContacts();
    void removeContact(String phoneNumber);
}
