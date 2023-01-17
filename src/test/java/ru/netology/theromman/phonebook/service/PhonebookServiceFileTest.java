package ru.netology.theromman.phonebook.service;

import org.junit.jupiter.api.Test;
import ru.netology.theromman.phonebook.model.Contact;
import ru.netology.theromman.phonebook.repository.PhonebookRepository;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PhonebookServiceFileTest {
    private final String phoneNumber = "+7228";
    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final Contact contact = new Contact(phoneNumber, firstName, lastName);

    private final PhonebookRepository mockPhonebookRepository = mock(PhonebookRepository.class);
    private final PhonebookServiceFile sut = new PhonebookServiceFile(mockPhonebookRepository);

    @Test
    void createContactOk() {
        sut.createContact(contact);

        verify(mockPhonebookRepository).createContact(contact);
    }

    @Test
    void getAllContactsOk() throws IOException {
        var expected = List.of(contact);

        when(mockPhonebookRepository.getAllContacts()).thenReturn(List.of(contact));

        var actual = sut.getAllContacts();

        verify(mockPhonebookRepository).getAllContacts();

        assertEquals(expected, actual);
    }
}