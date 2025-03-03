package com.hanocybous.backend.task;

import com.hanocybous.model.SimpleTask;
import com.hanocybous.model.Task;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class TaskModifier {

    private TaskModifier() {
    }

    public static void deleteTask(@NotNull List<Task> tasks, int selectedId) {
        tasks.removeIf(task -> task.getId() == selectedId);
    }

    public static void updateTask(@NotNull List<Task> tasks,
                                  int selectedId,
                                  int choice,
                                  String newValue) {
        for (Task task : tasks) {
            if (task.getId() == selectedId) {
                switch (choice) {
                    case 0 -> task.setTaskId(Integer.parseInt(newValue));
                    case 1 -> task.setTaskDescription(newValue);
                    case 2 -> task.setMamaId(Integer.parseInt(newValue));
                    case 3 -> task.setStart(Integer.parseInt(newValue));
                    case 4 -> task.setEnd(Integer.parseInt(newValue));
                    case 5 -> task.setCost(Double.parseDouble(newValue));
                    default -> throw  new IllegalStateException("Unexpected value: " + choice);
                }
            }
        }
    }

    public static void addTask(@NotNull List<Task> tasks,
                               String taskText,
                               int mamaId,
                               String startDateString,
                               String endDateString,
                               double cost) {
        int id = tasks.get(tasks.size() - 1).getId() + 1;
        addTask(tasks,
                taskText,
                mamaId,
                startDateString,
                endDateString,
                cost,
                id);
    }

    public static void addTask(@NotNull List<Task> tasks,
                               String taskText,
                               int mamaId,
                               String startDateString,
                               String endDateString,
                               double cost,
                               int taskId) {
        int start = Integer.parseInt(startDateString);
        int end = Integer.parseInt(endDateString);
        Task newTask = new SimpleTask(taskId,
                taskText,
                mamaId,
                start,
                end,
                cost);
        tasks.add(newTask);
    }

    public static void updateMamaTask(@NotNull List<Task> tasks,
                                      int mamaId,
                                      String mamaStartDateString,
                                      String mamaEndDateString,
                                      Double mamaCost) {
        int mamaStartDate = Integer.parseInt(mamaStartDateString);
        int mamaEndDate = Integer.parseInt(mamaEndDateString);
        for (Task task : tasks) {
            if (task.getId() == mamaId) {
                task.setStart(mamaStartDate);
                task.setEnd(mamaEndDate);
                task.setCost(mamaCost);
                break;
            }
        }
    }

    public static void deleteSubTasks(@NotNull List<Task> tasks, int selectedId) {
        List<Task> subTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getMamaId() == selectedId) {
                subTasks.add(task);
            }
        }
        tasks.removeAll(subTasks);
    }
}
