package ru.netology.theromman.phonebook.executor;

import lombok.RequiredArgsConstructor;
import ru.netology.theromman.phonebook.model.Contact;
import ru.netology.theromman.phonebook.service.PhonebookService;

@RequiredArgsConstructor
public class CommandExecutorCreate implements CommandExecutor {

    private final PhonebookService phonebookService;

    @Override
    public void runCommand(String[] parameters) {
        phonebookService.createContact(new Contact(parameters[0], parameters[1], parameters[2]));
    }

    @Override
    public String getSupportedOperation() {
        return "CREATE";
    }
}
