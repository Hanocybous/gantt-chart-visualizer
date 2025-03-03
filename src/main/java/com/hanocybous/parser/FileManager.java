package com.hanocybous.parser;

import com.hanocybous.model.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private String path;
    private String delimiter;
    private final TaskParser taskParser;

    public FileManager(String path, String delimiter) {
        this.path = path;
        this.delimiter = delimiter;
        this.taskParser = new TaskParser();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getPath() {
        return path;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public List<Task> giveTasks() {
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine().trim();
                taskParser.parseLine(line, delimiter);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return taskParser.getReturnTaskList();
    }
}
