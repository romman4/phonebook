package ru.netology.theromman.phonebook.executor;

import lombok.RequiredArgsConstructor;
import ru.netology.theromman.phonebook.service.PhonebookService;

@RequiredArgsConstructor
public class CommandExecutorPrintAll implements CommandExecutor {

    private final PhonebookService phonebookService;

    @Override
    public void runCommand(String[] parameters) {
        System.out.println(phonebookService.getAllContacts());
    }

    @Override
    public String getSupportedOperation() {
        return "ALL";
    }
}
