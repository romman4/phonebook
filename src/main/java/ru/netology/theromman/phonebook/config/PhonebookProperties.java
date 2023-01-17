package ru.netology.theromman.phonebook.config;


public final class PhonebookProperties {

    private final String fileName;

    private PhonebookProperties(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    //region region for singleton realization
    public static PhonebookProperties getInstance(String fileName) {
        if (instance == null) {
            return new PhonebookProperties(fileName);
        } else return instance;
    }

    private static PhonebookProperties instance;
    //endregion
}
