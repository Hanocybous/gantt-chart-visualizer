package com.hanocybous.reporter;

import com.hanocybous.model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class HTMLReportGenerator {
    private final String path;
    private final List<Task> tasks;
    private static final String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};

    public HTMLReportGenerator(String path, List<Task> tasks) {
        this.path = path;
        this.tasks = tasks;
    }

    public int generate() {
        try (FileWriter myWriter = new FileWriter(path)) {
            myWriter.write("<html>\n<head>\n<title>Report</title>\n</head>\n<body>\n<table border=\"1\">\n<tr>\n");
            for (String columnName : columnNames) {
                myWriter.write("<th>" + columnName + "</th>\n");
            }
            myWriter.write("</tr>\n");
            for (Task task : tasks) {
                myWriter.write("<tr>\n<td>" + task.getId() + "</td>\n<td>" + task.getName() + "</td>\n<td>" +
                        task.getMamaId() + "</td>\n<td>" + task.getStart() + "</td>\n<td>" + task.getEnd() + "</td>\n<td>" +
                        task.getCost() + "</td>\n</tr>\n");
            }
            myWriter.write("</table>\n</body>\n</html>\n");
            myWriter.flush();
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }
}
