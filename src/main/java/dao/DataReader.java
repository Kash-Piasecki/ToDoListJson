package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import task.Task;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
        private final Path path = Paths.get("taskList.json");

    public List<Task> readDataFromFile() {
        List<Task> tasks = new ArrayList<>();
        if (Files.exists(path)) {
            try {
                final String readFile = Files.readString(path);
                tasks = jsonConvertToList(readFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }

    public List<Task> jsonConvertToList(String read) {
        Type taskListType = new TypeToken<ArrayList<Task>>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(read, taskListType);
    }
}

