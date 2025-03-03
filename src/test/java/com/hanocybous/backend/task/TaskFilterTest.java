package com.hanocybous.backend.task;

import com.hanocybous.dto.SimpleTableModel;
import com.hanocybous.model.Task;
import com.hanocybous.model.SimpleTask;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskFilterTest {

    @Test
    void getTasksByPrefix_returnsCorrectTasks_whenPrefixMatches() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0),
                new SimpleTask(2, "Another Task", 1, 6, 9, 50.0)
        );
        String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};

        SimpleTableModel result = TaskFilter.getTasksByPrefix(tasks, "Task", columnNames);

        assertEquals(1, result.getRowCount());
        assertEquals("Task 1", result.getValueAt(0, 1));
    }

    @Test
    void getTasksByPrefix_returnsEmptyModel_whenNoTasksMatchPrefix() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0),
                new SimpleTask(2, "Another Task", 1, 6, 9, 50.0)
        );
        String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};

        SimpleTableModel result = TaskFilter.getTasksByPrefix(tasks, "Nonexistent", columnNames);

        assertEquals(0, result.getRowCount());
    }

    @Test
    void getTaskById_returnsCorrectTask_whenIdExists() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0),
                new SimpleTask(2, "Task 2", 1, 6, 9, 50.0)
        );
        String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};

        SimpleTableModel result = TaskFilter.getTaskById(tasks, 1, columnNames);

        assertNotNull(result);
        assertEquals(1, result.getRowCount());
        assertEquals("Task 1", result.getValueAt(0, 1));
    }

    @Test
    void getTaskById_returnsNull_whenIdDoesNotExist() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0),
                new SimpleTask(2, "Task 2", 1, 6, 9, 50.0)
        );
        String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};

        SimpleTableModel result = TaskFilter.getTaskById(tasks, 3, columnNames);

        assertNull(result);
    }

    @Test
    void getTopLevelTasks_returnsCorrectTasks_whenTasksAreTopLevel() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0),
                new SimpleTask(2, "Task 2", 1, 6, 9, 50.0)
        );
        String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};

        SimpleTableModel result = TaskFilter.getTopLevelTasks(tasks, columnNames);

        assertEquals(1, result.getRowCount());
        assertEquals("Task 1", result.getValueAt(0, 1));
    }

    @Test
    void getTopLevelTasks_returnsEmptyModel_whenNoTopLevelTasksExist() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 1, 5, 10, 100.0),
                new SimpleTask(2, "Task 2", 1, 6, 9, 50.0)
        );
        String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};

        SimpleTableModel result = TaskFilter.getTopLevelTasks(tasks, columnNames);

        assertEquals(0, result.getRowCount());
    }
}
