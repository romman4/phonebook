package ru.netology.theromman.phonebook.service;

import lombok.RequiredArgsConstructor;
import ru.netology.theromman.phonebook.model.Contact;
import ru.netology.theromman.phonebook.repository.PhonebookRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class PhonebookServiceFile implements PhonebookService {

    private final PhonebookRepository phonebookRepository;

    @Override
    public void createContact(Contact contact) {
        phonebookRepository.createContact(contact);
    }

    @Override
    public Collection<Contact> getAllContacts() {
        try {
            return phonebookRepository.getAllContacts();
        } catch (IOException e) {
            System.out.println("Can not get all contacts, reason: " + e.getLocalizedMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
