package com.hanocybous.backend.task;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TaskSorterTest {

    @Test
    void sortTasks_sortsByStartWhenMamaIdIsSame() {
        List<Task> tasks = new ArrayList<>(Arrays.asList(
                new SimpleTask(1, "Task 1", 1, 10, 15, 100.0),
                new SimpleTask(2, "Task 2", 1, 5, 10, 50.0)
        ));

        TaskSorter.sortTasks(tasks);

        assertEquals(2, tasks.get(0).getId());
        assertEquals(1, tasks.get(1).getId());
    }

    @Test
    void sortTasks_sortsByEndWhenStartIsSameAndMamaIdIsSame() {
        List<Task> tasks = new ArrayList<>(Arrays.asList(
                new SimpleTask(1, "Task 1", 1, 5, 15, 100.0),
                new SimpleTask(2, "Task 2", 1, 5, 10, 50.0)
        ));

        TaskSorter.sortTasks(tasks);

        assertEquals(2, tasks.get(0).getId());
        assertEquals(1, tasks.get(1).getId());
    }

    @Test
    void sortTasks_sortsByIdWhenMamaIdIsDifferent() {
        List<Task> tasks = new ArrayList<>(Arrays.asList(
                new SimpleTask(1, "Task 1", 1, 5, 10, 100.0),
                new SimpleTask(2, "Task 2", 2, 5, 10, 50.0)
        ));

        TaskSorter.sortTasks(tasks);

        assertEquals(1, tasks.get(0).getId());
        assertEquals(2, tasks.get(1).getId());
    }

    @Test
    void sortTasks_sortsByIdWhenMamaIdIsZero() {
        List<Task> tasks = new ArrayList<>(Arrays.asList(
                new SimpleTask(2, "Task 2", 0, 5, 10, 50.0),
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0)
        ));

        TaskSorter.sortTasks(tasks);

        assertEquals(1, tasks.get(0).getId());
        assertEquals(2, tasks.get(1).getId());
    }
}
