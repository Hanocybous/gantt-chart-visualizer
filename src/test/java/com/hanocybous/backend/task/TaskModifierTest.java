package com.hanocybous.backend.task;

import com.hanocybous.model.SimpleTask;
import com.hanocybous.model.Task;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskModifierTest {

    @Test
    void deleteTask_removesTaskWithGivenId() {
        List<Task> tasks = new ArrayList<>(Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0),
                new SimpleTask(2, "Task 2", 1, 6, 9, 50.0)
        ));

        TaskModifier.deleteTask(tasks, 1);

        assertEquals(1, tasks.size());
        assertEquals(2, tasks.get(0).getId());
    }

    @Test
    void updateMamaTask_updatesMamaTaskCorrectly() {
        List<Task> tasks = new ArrayList<>(Arrays.asList(
                new SimpleTask(1, "Mama Task", 0, 5, 10, 100.0)
        ));

        TaskModifier.updateMamaTask(tasks, 1, "7", "8", 200.0);

        assertEquals(7, tasks.get(0).getStart());
        assertEquals(8, tasks.get(0).getEnd());
        assertEquals(200.0, tasks.get(0).getCost());
    }

    @Test
    void deleteSubTasks_removesAllSubTasks() {
        List<Task> tasks = new ArrayList<>(Arrays.asList(
                new SimpleTask(1, "Mama Task", 0, 5, 10, 100.0),
                new SimpleTask(2, "Sub Task 1", 1, 6, 9, 50.0),
                new SimpleTask(3, "Sub Task 2", 1, 7, 8, 75.0)
        ));

        TaskModifier.deleteSubTasks(tasks, 1);

        assertEquals(1, tasks.size());
        assertEquals(1, tasks.get(0).getId());
    }
}
