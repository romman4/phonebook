package ru.netology.theromman.phonebook;

import ru.netology.theromman.phonebook.config.PhonebookProperties;
import ru.netology.theromman.phonebook.executor.CommandExecutor;
import ru.netology.theromman.phonebook.executor.CommandExecutorCreate;
import ru.netology.theromman.phonebook.executor.CommandExecutorPrintAll;
import ru.netology.theromman.phonebook.facade.PhonebookConsoleReader;
import ru.netology.theromman.phonebook.repository.PhonebookRepository;
import ru.netology.theromman.phonebook.repository.PhonebookRepositoryFile;
import ru.netology.theromman.phonebook.service.PhonebookService;
import ru.netology.theromman.phonebook.service.PhonebookServiceFile;

import java.io.IOException;
import java.util.List;

public class Start {
    public static void main(String[] args) throws IOException {
        String fileName;
        if (args.length < 1) {
            fileName = "src/main/resources/phonebook.txt";
        } else fileName = args[0];
        PhonebookProperties properties = PhonebookProperties.getInstance(fileName);
        PhonebookRepository phonebookRepository = PhonebookRepositoryFile.getInstance(properties);
        PhonebookService phonebookService = new PhonebookServiceFile(phonebookRepository);
        List<CommandExecutor> commandExecutors = List.of(
                new CommandExecutorCreate(phonebookService),
                new CommandExecutorPrintAll(phonebookService)
        );
        PhonebookConsoleReader phonebookConsoleReader = new PhonebookConsoleReader(commandExecutors);
        phonebookConsoleReader.readConsoleWhileNotInterrupted();
    }
}
