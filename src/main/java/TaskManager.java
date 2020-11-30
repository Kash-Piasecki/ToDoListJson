import menu.MenuViewer;
import task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final List<Task> tasks = new ArrayList<>();
        final MenuViewer menuViewer = new MenuViewer(scanner, tasks);
        menuViewer.run();
    }
}
