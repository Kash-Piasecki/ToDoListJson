package dao;

import com.google.gson.Gson;
import task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataWriter {
        private final List<Task> tasks;
        private final Path path = Paths.get("taskList.json");

    public DataWriter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void saveToFile(){
        createFile();
        Gson gson = new Gson();
        String jsonString = gson.toJson(tasks);
        try {
            Files.writeString(path, jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFile(){
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
