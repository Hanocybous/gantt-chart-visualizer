package com.hanocybous.backend;

import com.hanocybous.dto.SimpleTableModel;
import com.hanocybous.model.Task;
import com.hanocybous.parser.FileManager;

import java.util.ArrayList;
import java.util.List;

class TaskManager implements IMainController {

    private static final String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};
    private static TaskManager instance = null;
    private ArrayList<Task> tasks;

    private TaskManager() {
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public SimpleTableModel load(String fileName, String delimiter) {
        FileManager taskCreator = new FileManager(fileName, delimiter);
        tasks = (ArrayList<Task>) taskCreator.giveTasks();
        TaskSorter.sortTasks(tasks);
        return TaskTableModelConverter.convertToTableModel(tasks, columnNames, "Comp", "Comp");
    }

    public SimpleTableModel getTasksByPrefix(String prefix) {
        return TaskFilter.getTasksByPrefix(tasks, prefix, columnNames);
    }

    public SimpleTableModel getTaskById(int id) {
        return TaskFilter.getTaskById(tasks, id, columnNames);
    }

    public SimpleTableModel getTopLevelTasks() {
        return TaskFilter.getTopLevelTasks(tasks, columnNames);
    }

    public int createReport(String path, ReportType type) {
        IReporter reporter = ReporterFactory.getReporter(path, tasks, type);
        return reporter.createReport();
    }

    public void deleteTask(int selectedId) {
        TaskModifier.deleteTask(tasks, selectedId);
    }

    public void updateTask(int selectedId, int choice, String newValue) {
        TaskModifier.updateTask(tasks, selectedId, choice, newValue);
    }

    public SimpleTableModel getTaskList() {
        return TaskTableModelConverter.convertToTableModel(tasks, columnNames, "TaskList", "TaskList");
    }

    public void addTask(String taskText, int mamaId, String startDateString, String endDateString, double cost) {
        TaskModifier.addTask(tasks, taskText, mamaId, startDateString, endDateString, cost);
    }

    public void addTask(String taskText, int mamaId, String startDateString, String endDateString, double cost, int taskId) {
        TaskModifier.addTask(tasks, taskText, mamaId, startDateString, endDateString, cost, taskId);
    }

    @Override
    public String[] getTaskData(int selectedId) {
        return TaskDataRetriever.getTaskData(tasks, selectedId);
    }

    @Override
    public void updateMamaTask(int mamaId, String mamaStartDateString, String mamaEndDateString, Double mamaCost) {
        TaskModifier.updateMamaTask(tasks, mamaId, mamaStartDateString, mamaEndDateString, mamaCost);
    }

    @Override
    public void deleteSubTasks(int selectedId) {
        TaskModifier.deleteSubTasks(tasks, selectedId);
    }
}
