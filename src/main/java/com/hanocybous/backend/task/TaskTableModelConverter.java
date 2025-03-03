package com.hanocybous.backend.task;

import com.hanocybous.dto.SimpleTableModel;
import com.hanocybous.model.Task;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class TaskTableModelConverter {

    private TaskTableModelConverter() {
    }

    @Contract("_, _, _, _ -> new")
    public static @NotNull SimpleTableModel convertToTableModel(@NotNull List<Task> tasks,
                                                                String[] columnNames,
                                                                String name,
                                                                String prjName) {
        List<String[]> tasksToReturn = new ArrayList<>();
        for (Task task : tasks) {
            tasksToReturn.add(task.stringTask());
        }
        return new SimpleTableModel(name, prjName, columnNames, tasksToReturn);
    }
}
