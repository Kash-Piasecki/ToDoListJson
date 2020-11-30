package menu;

import dao.DataReader;
import dao.DataWriter;
import task.Task;
import task.TaskDataHandler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class MenuViewer {
    private final Scanner scanner;
    private List<Task> tasks;

    public MenuViewer(Scanner scanner, List<Task> tasks) {
        this.scanner = scanner;
        this.tasks = tasks;
    }

    public void run() {
        loadData();
        while (true) {
            menu();
            String input = scanner.nextLine();
            selectOption(input);
        }
    }

    private void loadData() {
        final Path path = Paths.get("taskList.json");
        if (Files.exists(path)){
            final DataReader dataReader = new DataReader();
            tasks = dataReader.readDataFromFile();
        }
    }

    private void menu() {
        System.out.println(ansi().fgBlue().a("Please select an option:").reset());
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

    private void selectOption(String input) {
        switch (input) {
            case "add" -> tasks.add(TaskDataHandler.createNewTask());
            case "remove" -> TaskDataHandler.removeTask(tasks);
            case "list" -> TaskDataHandler.printTaskList(tasks);
            case "exit" -> exit();
            default -> System.err.println("Wrong input.");
        }
    }

    private void exit() {
        final DataWriter dataWriter = new DataWriter(tasks);
        dataWriter.saveToFile();
        System.exit(0);
    }
}
