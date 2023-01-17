package ru.netology.theromman.phonebook.executor;

public interface CommandExecutor {
    void runCommand(String[] parameters);
    String getSupportedOperation();
}
