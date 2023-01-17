package ru.netology.theromman.phonebook.executor;

import lombok.RequiredArgsConstructor;
import ru.netology.theromman.phonebook.service.PhonebookService;

@RequiredArgsConstructor
public class CommandExecutorRemoveContact implements CommandExecutor {

    private final PhonebookService phonebookService;

    @Override
    public void runCommand(String[] parameters) {
        phonebookService.removeContact(parameters[0]);
    }

    @Override
    public String getSupportedOperation() {
        return "REMOVE";
    }
}
