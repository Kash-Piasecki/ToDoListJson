package task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TaskDataHandler {
    private static final Scanner scanner = new Scanner(System.in);


    public static Task createNewTask() {
        System.out.println("Please add task description");
        String description = scanner.nextLine();
        System.out.println("Please add task due date in format YYYY-MM-DD");
        LocalDate dueDate = createDate();
        System.out.println("Is your task important: true/false");
        boolean isImportant = createBoolean();
        return new Task(description, dueDate, isImportant);
    }


    private static LocalDate createDate() {
        boolean isRightFormat = false;
        LocalDate deadline = null;
        do {
            String dueDate = scanner.nextLine();
            try {
                deadline = LocalDate.parse(dueDate);
                LocalDate localDate = LocalDate.now();
                if (deadline.isBefore(localDate)) {
                    throw new IllegalArgumentException();
                }
                isRightFormat = true;
            } catch (DateTimeParseException e) {
                System.err.println("Wrong time format, try again.");
            } catch (IllegalArgumentException e) {
                System.err.println("Deadline cannot happen before actual date, try again.");
            }
        } while (!isRightFormat);
        return deadline;
    }

    private static boolean createBoolean() {
        boolean isBoolean = false;
        boolean isImportant = false;
        do {
            String input = scanner.nextLine();
            switch (input) {
                case "true" -> {
                    isImportant = true;
                    isBoolean = true;
                }
                case "false" -> isBoolean = true;
                default -> System.err.println("Not a boolean value, try true/false");
            }
        } while (!isBoolean);
        return isImportant;
    }

    private static int getInteger() {
        System.out.println("Please select number to remove");
        boolean isInteger = false;
        int index = 0;
        do {
            String input = scanner.nextLine();
            try {
                index = Integer.parseInt(input);
                isInteger = true;
            } catch (Exception e) {
                System.err.println("Not an intiger, try again");
            }
        } while (!isInteger);
        return index;
    }

    public static void printTaskList(List<Task> tasks){
        if (tasks.isEmpty()){
            System.err.println("Empty list. Add some tasks to print them.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("Index: " + i + " " + tasks.get(i));
            }
            System.out.println();
        }
    }

    public static void removeTask(List<Task> tasks){
        if (tasks.isEmpty()) {
            System.err.println("Empty list. Add some tasks to remove them.");
        } else {
            int index = TaskDataHandler.getInteger();
            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
                System.out.println("Value was successfully deleted.");
            } else {
                System.err.println("Integer must be in range of task list indexes.");
            }
        }
    }
}
