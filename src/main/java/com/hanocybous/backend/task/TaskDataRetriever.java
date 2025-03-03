package com.hanocybous.backend;

import com.hanocybous.model.Task;
import org.jetbrains.annotations.NotNull;

import java.util.List;

class TaskDataRetriever {

    private TaskDataRetriever() {
    }

    public static String @NotNull [] getTaskData(@NotNull List<Task> tasks,
                                                 int selectedId) {
        String[] taskData = new String[5];
        for (Task task : tasks) {
            if (task.getId() == selectedId) {
                taskData[0] = task.getName();
                taskData[1] = Integer.toString(task.getStart());
                taskData[2] = Integer.toString(task.getEnd());
                taskData[3] = Double.toString(task.getCost());
                taskData[4] = Integer.toString(task.getId());
                return taskData;
            }
        }
        return new String[0];
    }
}
