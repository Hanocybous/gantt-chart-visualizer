package com.hanocybous.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleTaskTest {

    @Test
    void constructor_initializesFieldsCorrectly() {
        SimpleTask task = new SimpleTask(1, "Task", 0, 5, 10, 100.0);

        assertEquals(1, task.getId());
        assertEquals("Task", task.getName());
        assertEquals(0, task.getMamaId());
        assertEquals(5, task.getStart());
        assertEquals(10, task.getEnd());
        assertEquals(100.0, task.getCost());
    }

    @Test
    void setStart_updatesStart() {
        SimpleTask task = new SimpleTask(1, "Task", 0, 5, 10, 100.0);
        task.setStart(7);

        assertEquals(7, task.getStart());
    }

    @Test
    void setEnd_updatesEnd() {
        SimpleTask task = new SimpleTask(1, "Task", 0, 5, 10, 100.0);
        task.setEnd(12);

        assertEquals(12, task.getEnd());
    }

    @Test
    void setCost_updatesCost() {
        SimpleTask task = new SimpleTask(1, "Task", 0, 5, 10, 100.0);
        task.setCost(150.0);

        assertEquals(150.0, task.getCost());
    }

    @Test
    void setTaskId_updatesId() {
        SimpleTask task = new SimpleTask(1, "Task", 0, 5, 10, 100.0);
        task.setTaskId(2);

        assertEquals(2, task.getId());
    }

    @Test
    void setTaskDescription_updatesName() {
        SimpleTask task = new SimpleTask(1, "Task", 0, 5, 10, 100.0);
        task.setTaskDescription("Updated Task");

        assertEquals("Updated Task", task.getName());
    }
}
