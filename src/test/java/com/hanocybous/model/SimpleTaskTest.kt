package com.hanocybous.model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SimpleTaskTest {

    @Test
    fun constructor_initializesFieldsCorrectly() {
        val task = SimpleTask(1, "Task", 0, 5, 10, 100.0)

        assertEquals(1, task.id)
        assertEquals("Task", task.name)
        assertEquals(0, task.mamaId)
        assertEquals(5, task.start)
        assertEquals(10, task.end)
        assertEquals(100.0, task.cost)
    }

    @Test
    fun setStart_updatesStart() {
        val task = SimpleTask(1, "Task", 0, 5, 10, 100.0)
        task.start = 7

        assertEquals(7, task.start)
    }

    @Test
    fun setEnd_updatesEnd() {
        val task = SimpleTask(1, "Task", 0, 5, 10, 100.0)
        task.end = 12

        assertEquals(12, task.end)
    }

    @Test
    fun setCost_updatesCost() {
        val task = SimpleTask(1, "Task", 0, 5, 10, 100.0)
        task.cost = 150.0

        assertEquals(150.0, task.cost)
    }

    @Test
    fun setTaskId_updatesId() {
        val task = SimpleTask(1, "Task", 0, 5, 10, 100.0)
        task.setTaskId(2)

        assertEquals(2, task.id)
    }

    @Test
    fun setTaskDescription_updatesName() {
        val task = SimpleTask(1, "Task", 0, 5, 10, 100.0)
        task.setTaskDescription("Updated Task")

        assertEquals("Updated Task", task.name)
    }
}
