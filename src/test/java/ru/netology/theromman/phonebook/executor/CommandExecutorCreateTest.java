package ru.netology.theromman.phonebook.executor;

import org.junit.jupiter.api.Test;
import ru.netology.theromman.phonebook.model.Contact;
import ru.netology.theromman.phonebook.service.PhonebookService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CommandExecutorCreateTest {
    private final String phoneNumber = "+7228";
    private final String firstName = "firstName";
    private final String lastName = "lastName";

    private final PhonebookService mockPhonebookService = mock(PhonebookService.class);

    private final CommandExecutorCreate sut = new CommandExecutorCreate(mockPhonebookService);

    @Test
    void createContactOk() {
        sut.runCommand(new String[]{phoneNumber, firstName, lastName});

        verify(mockPhonebookService).createContact(new Contact(phoneNumber, firstName, lastName));
    }

    @Test
    void getSupportedOperationOk() {
        var expected = "CREATE";

        var actual = sut.getSupportedOperation();

        assertEquals(expected, actual);
    }
}