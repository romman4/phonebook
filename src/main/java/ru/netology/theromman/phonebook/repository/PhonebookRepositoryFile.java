package ru.netology.theromman.phonebook.repository;

import ru.netology.theromman.phonebook.config.PhonebookProperties;
import ru.netology.theromman.phonebook.model.Contact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
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

    @Override
    public Collection<Contact> getAllContacts() {
        var resultCollection = new ArrayList<Contact>();
        try (
                FileReader fileReader = new FileReader(phonebookProperties.getFileName());
                BufferedReader reader = new BufferedReader(fileReader)) {
            while (reader.ready()) {
                resultCollection.add(Contact.fromString(reader.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultCollection;
    }

    //region region for singleton realization
    public static PhonebookRepositoryFile getInstance(PhonebookProperties phonebookProperties) throws IOException {
        if (instance == null) {
            return new PhonebookRepositoryFile(phonebookProperties);
        } else return instance;
    }

    private static PhonebookRepositoryFile instance;
    //endregion

}
