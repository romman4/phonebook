package ru.netology.theromman.phonebook.facade;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.theromman.phonebook.executor.CommandExecutor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PhonebookConsoleReaderTest {
    private final String phoneNumber = "+7";
    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final InputStream sysInBackup = System.in;
    private final String operationName = "OPERATION";
    private final String commandLine = operationName + " " + phoneNumber + " " + firstName + " " + lastName + "\nexit";
    private final String[] commandArr = new String[]{phoneNumber, firstName, lastName};

    private final CommandExecutor mockCommandExecutor = mock(CommandExecutor.class);
    private final PhonebookConsoleReader sut = new PhonebookConsoleReader(List.of(mockCommandExecutor));

    @BeforeEach
    void beforeEach() {
        System.setIn(new ByteArrayInputStream(commandLine.getBytes()));
        doReturn(operationName).when(mockCommandExecutor).getSupportedOperation();
    }

    @AfterEach
    void afterEach() {
        System.setIn(sysInBackup);
    }

    @Test
    public void readConsoleWhileNotInterruptedOk() {
        sut.readConsoleWhileNotInterrupted();

        verify(mockCommandExecutor).runCommand(commandArr);
    }

}