package com.hanocybous.reporter;

import com.hanocybous.model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class MDReportGenerator {
    private final String path;
    private final List<Task> tasks;
    private static final String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};

    public MDReportGenerator(String path, List<Task> tasks) {
        this.path = path;
        this.tasks = tasks;
    }

    public int generate() {
        try (FileWriter myWriter = new FileWriter(path)) {
            for (int i = 0; i < columnNames.length; i++) {
                myWriter.write(columnNames[i] + (i == columnNames.length - 1 ? "\t\n" : "\t"));
            }
            for (Task task : tasks) {
                myWriter.write(task.getId() + "\t" + task.getName() + "\t" + task.getMamaId() + "\t" +
                        task.getStart() + "\t" + task.getEnd() + "\t" + task.getCost() + "\t\n");
            }
            myWriter.flush();
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }
}
