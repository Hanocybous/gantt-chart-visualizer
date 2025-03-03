package com.hanocybous.backend.task;

import com.hanocybous.dto.SimpleTableModel;
import com.hanocybous.model.Task;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

class TaskFilter {

    private TaskFilter() {
    }

    @Contract("_, _, _ -> new")
    public static @NotNull SimpleTableModel getTasksByPrefix(@NotNull List<Task> tasks,
                                                             String prefix,
                                                             String[] columnNames) {
        List<String[]> prefixData = new ArrayList<>();
        for (Task checkingTask : tasks) {
            if (checkingTask.getName().startsWith(prefix)) {
                prefixData.add(checkingTask.stringTask());
            }
        }
        return new SimpleTableModel("Prefix",
                "Prefix",
                columnNames,
                prefixData);
    }

    public static @Nullable SimpleTableModel getTaskById(@NotNull List<Task> tasks,
                                                         int id,
                                                         String[] columnNames) {
        List<String[]> idData = new ArrayList<>();
        for (Task idTask : tasks) {
            if (idTask.getId() == id) {
                idData.add(idTask.stringTask());
                return new SimpleTableModel("Id",
                        "Id",
                        columnNames,
                        idData);
            }
        }
        return null;
    }

    @Contract("_, _ -> new")
    public static @NotNull SimpleTableModel getTopLevelTasks(@NotNull List<Task> tasks,
                                                             String[] columnNames) {
        List<String[]> topLevelData = new ArrayList<>();
        for (Task topLevelTask : tasks) {
            if (topLevelTask.getMamaId() == 0) {
                topLevelData.add(topLevelTask.stringTask());
            }
        }
        return new SimpleTableModel("TopLevel",
                "TopLevel",
                columnNames,
                topLevelData);
    }
}
