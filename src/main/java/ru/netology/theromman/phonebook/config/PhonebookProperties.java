package ru.netology.theromman.phonebook.config;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class PhonebookProperties {

    private final String fileName;

    //region region for singleton realization
    public static PhonebookProperties getInstance(String fileName) {
        if (instance == null) {
            return new PhonebookProperties(fileName);
        } else return instance;
    }

    private static PhonebookProperties instance;
    //endregion
}
