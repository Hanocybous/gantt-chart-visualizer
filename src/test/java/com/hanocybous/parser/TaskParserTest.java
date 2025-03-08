package com.hanocybous.parser;

import com.hanocybous.model.ComplexTask;
import com.hanocybous.model.SimpleTask;
import com.hanocybous.model.Task;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskParserTest {

    @Test
    void parseLine_addsSimpleTask_whenLineHasSixElements() {
        TaskParser parser = new TaskParser();
        parser.parseLine("1,Task 1,0,5,10,100.0", ",");

        List<Task> tasks = parser.getReturnTaskList();
        assertEquals(1, tasks.size());
        assertInstanceOf(SimpleTask.class, tasks.get(0));
        assertEquals(1, tasks.get(0).getId());
        assertEquals("Task 1", tasks.get(0).getName());
    }

    @Test
    void parseLine_addsComplexTask_whenLineHasThreeElements() {
        TaskParser parser = new TaskParser();
        parser.parseLine("2,Task 2,1", ",");

        List<Task> tasks = parser.getReturnTaskList();
        assertEquals(1, tasks.size());
        assertInstanceOf(ComplexTask.class, tasks.get(0));
        assertEquals(2, tasks.get(0).getId());
        assertEquals("Task 2", tasks.get(0).getName());
    }

    @Test
    void parseLine_doesNotAddTask_whenLineHasInvalidNumberOfElements() {
        TaskParser parser = new TaskParser();
        parser.parseLine("3,Task 3", ",");

        List<Task> tasks = parser.getReturnTaskList();
        assertTrue(tasks.isEmpty());
    }

    @Test
    void getReturnTaskList_returnsEmptyList_whenNoTasksParsed() {
        TaskParser parser = new TaskParser();

        List<Task> tasks = parser.getReturnTaskList();
        assertTrue(tasks.isEmpty());
    }
}
