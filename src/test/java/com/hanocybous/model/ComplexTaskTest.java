package com.hanocybous.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComplexTaskTest {

    @Test
    void addSubTask_updatesStartAndEnd_whenStartAndEndAreUnset() {
        ComplexTask complexTask = new ComplexTask(1, "Complex Task", 0);
        Task subTask = new SimpleTask(2, "Sub Task", 1, 5, 10, 100.0);

        complexTask.addSubTask(subTask);

        assertEquals(5, complexTask.getStart());
        assertEquals(10, complexTask.getEnd());
        assertEquals(100.0, complexTask.getCost());
    }

    @Test
    void addSubTask_updatesStartAndEnd_whenStartAndEndAreSet() {
        ComplexTask complexTask = new ComplexTask(1, "Complex Task", 0);
        complexTask.setStart(10);
        complexTask.setEnd(20);
        Task subTask = new SimpleTask(2, "Sub Task", 1, 5, 25, 100.0);

        complexTask.addSubTask(subTask);

        assertEquals(5, complexTask.getStart());
        assertEquals(25, complexTask.getEnd());
        assertEquals(100.0, complexTask.getCost());
    }

    @Test
    void addSubTask_increasesCost() {
        ComplexTask complexTask = new ComplexTask(1, "Complex Task", 0);
        Task subTask = new SimpleTask(2, "Sub Task", 1, 5, 10, 100.0);

        complexTask.addSubTask(subTask);

        assertEquals(100.0, complexTask.getCost());
    }
}
