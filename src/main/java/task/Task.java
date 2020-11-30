package task;

import java.time.LocalDate;

public class Task {
    private final String description;
    private final LocalDate deadline;
    private final boolean isImportant;

    public Task(String description, LocalDate deadline, boolean isImportant) {
        this.description = description;
        this.deadline = deadline;
        this.isImportant = isImportant;
    }

    @Override
    public String toString() {
        return "Task: " + description + " deadline: " + deadline + " isImportant: " + isImportant;
    }
}

