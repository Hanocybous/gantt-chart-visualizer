package com.hanocybous.backend.task;

import com.hanocybous.model.SimpleTask;
import com.hanocybous.model.Task;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskDataRetrieverTest {

    @Test
    void getTaskData_returnsCorrectData_whenTaskExists() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0),
                new SimpleTask(2, "Task 2", 1, 6, 9, 50.0)
        );

        String[] taskData = TaskDataRetriever.getTaskData(tasks, 1);

        assertArrayEquals(new String[]{"Task 1", "5", "10", "100.0", "1"}, taskData);
    }

    @Test
    void getTaskData_returnsEmptyArray_whenTaskDoesNotExist() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0),
                new SimpleTask(2, "Task 2", 1, 6, 9, 50.0)
        );

        String[] taskData = TaskDataRetriever.getTaskData(tasks, 3);

        assertArrayEquals(new String[0], taskData);
    }

    @Test
    void getTaskData_returnsEmptyArray_whenTaskListIsEmpty() {
        List<Task> tasks = Arrays.asList();

        String[] taskData = TaskDataRetriever.getTaskData(tasks, 1);

        assertArrayEquals(new String[0], taskData);
    }
}
