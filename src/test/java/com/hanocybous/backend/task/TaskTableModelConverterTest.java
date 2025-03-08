package com.hanocybous.backend.task;

import com.hanocybous.dto.SimpleTableModel;
import com.hanocybous.model.SimpleTask;
import com.hanocybous.model.Task;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskTableModelConverterTest {

    @Test
    void convertToTableModel_returnsCorrectModel_whenTasksAreProvided() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0),
                new SimpleTask(2, "Task 2", 1, 6, 9, 50.0)
        );
        String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};

        SimpleTableModel result = TaskTableModelConverter.convertToTableModel(tasks, columnNames, "TestName", "TestPrjName");

        assertEquals(2, result.getRowCount());
        assertEquals("Task 1", result.getValueAt(0, 1));
        assertEquals("Task 2", result.getValueAt(1, 1));
    }

    @Test
    void convertToTableModel_returnsEmptyModel_whenNoTasksAreProvided() {
        List<Task> tasks = Arrays.asList();
        String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};

        SimpleTableModel result = TaskTableModelConverter.convertToTableModel(tasks, columnNames, "TestName", "TestPrjName");

        assertEquals(0, result.getRowCount());
    }

    @Test
    void convertToTableModel_handlesNullColumnNames() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0)
        );

        SimpleTableModel result = TaskTableModelConverter.convertToTableModel(tasks, null, "TestName", "TestPrjName");

        assertEquals(1, result.getRowCount());
        assertEquals("Task 1", result.getValueAt(0, 1));
    }

    @Test
    void convertToTableModel_handlesNullNameAndPrjName() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0)
        );
        String[] columnNames = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};

        SimpleTableModel result = TaskTableModelConverter.convertToTableModel(tasks, columnNames, null, null);

        assertEquals(1, result.getRowCount());
        assertEquals("Task 1", result.getValueAt(0, 1));
    }
}
