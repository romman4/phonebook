package ru.netology.theromman.phonebook.repository;

import lombok.SneakyThrows;
import ru.netology.theromman.phonebook.config.PhonebookProperties;
import ru.netology.theromman.phonebook.model.Contact;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;

public final class PhonebookRepositoryFile implements PhonebookRepository {

    private final PhonebookProperties phonebookProperties;

    private PhonebookRepositoryFile(PhonebookProperties phonebookProperties) throws IOException {
        this.phonebookProperties = phonebookProperties;
        var targetFilePath = Paths.get(phonebookProperties.getFileName());
        if (Files.notExists(targetFilePath)){
            try {
                Files.createFile(targetFilePath);
            } catch (IOException e) {
                System.out.println("Can not create file " + targetFilePath + "!");
                throw e;
            }
        }
    }

    @Override
    public void createContact(Contact contact) {
        try {
            var targetFilePath = Paths.get(phonebookProperties.getFileName());
            Files.write(targetFilePath, (contact.toString() + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public Collection<Contact> getAllContacts() {
        var resultCollection = new ArrayList<Contact>();
        var contacts = Files.readAllLines(Paths.get(phonebookProperties.getFileName()));
        for (String contactString : contacts) {
            resultCollection.add(Contact.fromString(contactString));
        }
        return resultCollection;
    }

    //region region for singleton realization
    @SneakyThrows
    public static PhonebookRepositoryFile getInstance(PhonebookProperties phonebookProperties) {
        if (instance == null) {
            return new PhonebookRepositoryFile(phonebookProperties);
        } else return instance;
    }

    private static PhonebookRepositoryFile instance;
    //endregion

}
