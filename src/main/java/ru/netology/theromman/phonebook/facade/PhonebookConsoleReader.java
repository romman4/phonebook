package ru.netology.theromman.phonebook.facade;

import lombok.RequiredArgsConstructor;
import ru.netology.theromman.phonebook.executor.CommandExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RequiredArgsConstructor
public class PhonebookConsoleReader {

    private final List<CommandExecutor> commandExecutors;

    public void readConsoleWhileNotInterrupted() {
        try (BufferedReader consoleBufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                var command = consoleBufferedReader.readLine();
                if (command.trim().equalsIgnoreCase("exit")) {
                    break;
                }
                var commandArr = command.split(" ");
                var commandData = getCommandData(commandArr);
                var foundExecutors = commandExecutors
                        .stream()
                        .filter(commandExecutor -> commandExecutor
                                .getSupportedOperation()
                                .equalsIgnoreCase(commandArr[0]))
                        .toList();
                if (foundExecutors.size() != 1) {
                    System.out.println("Can not define concrete one executor. Found " + foundExecutors.size());
                } else foundExecutors.get(0).runCommand(commandData);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private String[] getCommandData(String[] commandArr) {
        var commandData = new String[commandArr.length - 1];
        System.arraycopy(commandArr, 1, commandData, 0, commandData.length);
        return commandData;
    }
}
