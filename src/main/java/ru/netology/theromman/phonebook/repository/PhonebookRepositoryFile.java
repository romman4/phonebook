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
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

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

    @SneakyThrows
    @Override
    public void removeContact(String phoneNumberToFind) {
        var tempFileName = phonebookProperties.getFileName() + "_temp";
        Path sourceFilePath = Path.of(phonebookProperties.getFileName());
        Path tempFilePath = Path.of(tempFileName);
        var sourceFileLines = Files.readAllLines(Paths.get(phonebookProperties.getFileName()));
        var targetFileContacts = new ArrayList<Contact>();
        for (String line : sourceFileLines) {
            var currentContact = Contact.fromString(line);
            if (!currentContact.phoneNumber().equals(phoneNumberToFind)) {
                targetFileContacts.add(currentContact);
            }
        }
        Files.createFile(tempFilePath);
        Files.writeString(tempFilePath, contactsToString(targetFileContacts));
        Files.move(tempFilePath, tempFilePath.resolveSibling(sourceFilePath.getFileName()), REPLACE_EXISTING);
    }

    private String contactsToString(List<Contact> contacts) {
        var targetStringBuilder = new StringBuilder();
        for (Contact c : contacts) {
            targetStringBuilder.append(c.toString());
            targetStringBuilder.append("\n");
        }
        return targetStringBuilder.toString();
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
