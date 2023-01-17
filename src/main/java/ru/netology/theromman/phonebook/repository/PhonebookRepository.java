package ru.netology.theromman.phonebook.repository;

import ru.netology.theromman.phonebook.model.Contact;

import java.io.IOException;
import java.util.Collection;

public interface PhonebookRepository {
    void createContact(Contact contact);
    Collection<Contact> getAllContacts() throws IOException;
}
