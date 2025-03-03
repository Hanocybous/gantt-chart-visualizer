package com.hanocybous.backend.task;

import com.hanocybous.model.Task;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

class TaskSorter {

    private TaskSorter() {
    }

    public static void sortTasks(@NotNull List<Task> tasks) {
        Comparator<Task> comparator = (o1, o2) -> {
            if (o1.getMamaId() != 0
                    && o2.getMamaId() != 0
                    && o1.getMamaId() == o2.getMamaId()
                    && o1.getStart() != o2.getStart()) {
                return o1.getStart() - o2.getStart();
            } else if (o1.getMamaId() != 0
                    && o2.getMamaId() != 0
                    && o1.getMamaId() == o2.getMamaId()
                    && o1.getStart() == o2.getStart()) {
                return o1.getEnd() - o2.getEnd();
            }
            return o1.getId() - o2.getId();
        };
        tasks.sort(comparator);
    }
}
