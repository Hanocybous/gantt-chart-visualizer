package com.hanocybous.model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ComplexTaskTest {

    @Test
    fun addSubTask_updatesStartAndEnd_whenStartAndEndAreUnset() {
        val complexTask = ComplexTask(1, "Complex Task", 0)
        val subTask = SimpleTask(2, "Sub Task", 1, 5, 10, 100.0)

        complexTask.addSubTask(subTask)

        assertEquals(5, complexTask.start)
        assertEquals(10, complexTask.end)
        assertEquals(100.0, complexTask.cost)
    }

    @Test
    fun addSubTask_updatesStartAndEnd_whenStartAndEndAreSet() {
        val complexTask = ComplexTask(1, "Complex Task", 0)
        complexTask.start = 10
        complexTask.end = 20
        val subTask = SimpleTask(2, "Sub Task", 1, 5, 25, 100.0)

        complexTask.addSubTask(subTask)

        assertEquals(5, complexTask.start)
        assertEquals(25, complexTask.end)
        assertEquals(100.0, complexTask.cost)
    }

    @Test
    fun addSubTask_increasesCost() {
        val complexTask = ComplexTask(1, "Complex Task", 0)
        val subTask = SimpleTask(2, "Sub Task", 1, 5, 10, 100.0)

        complexTask.addSubTask(subTask)

        assertEquals(100.0, complexTask.cost)
    }
}
