package ru.netology.theromman.phonebook.executor;

import lombok.RequiredArgsConstructor;
import ru.netology.theromman.phonebook.service.PhonebookService;

@RequiredArgsConstructor
public class CommandExecutorFindAndPrint implements CommandExecutor {

    private final PhonebookService phonebookService;

    @Override
    public void runCommand(String[] parameters) {
        var contacts = phonebookService.getAllContacts();
        var filteredContacts = contacts
                .stream()
                .filter(contact ->
                                contact.firstName().equals(parameters[0]) || contact.lastName().equals(parameters[0])
                )
                .toList();
        System.out.println(filteredContacts);
    }

    @Override
    public String getSupportedOperation() {
        return "FIND";
    }
}
