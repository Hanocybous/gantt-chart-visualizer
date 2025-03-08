package com.hanocybous.parser;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void giveTasks_returnsEmptyList_whenFileIsEmpty() throws IOException {
        File tempFile = File.createTempFile("empty", ".txt");
        FileManager fileManager = new FileManager(tempFile.getPath(), ",");

        List<Task> tasks = fileManager.giveTasks();

        assertTrue(tasks.isEmpty());
        tempFile.delete();
    }

    @Test
    void giveTasks_returnsTasks_whenFileContainsValidData() throws IOException {
        File tempFile = File.createTempFile("valid", ".txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("1,Task 1,0,5,10,100.0\n");
        writer.write("2,Task 2,1,6,9,50.0\n");
        writer.close();
        FileManager fileManager = new FileManager(tempFile.getPath(), ",");

        List<Task> tasks = fileManager.giveTasks();

        assertEquals(2, tasks.size());
        assertEquals(1, tasks.get(0).getId());
        assertEquals("Task 1", tasks.get(0).getName());
        assertEquals(2, tasks.get(1).getId());
        assertEquals("Task 2", tasks.get(1).getName());
        tempFile.delete();
    }

    @Test
    void giveTasks_ignoresEmptyLines() throws IOException {
        File tempFile = File.createTempFile("emptyLines", ".txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("1,Task 1,0,5,10,100.0\n\n2,Task 2,1,6,9,50.0\n");
        writer.close();
        FileManager fileManager = new FileManager(tempFile.getPath(), ",");

        List<Task> tasks = fileManager.giveTasks();

        assertEquals(2, tasks.size());
        tempFile.delete();
    }

    @Test
    void giveTasks_handlesFileNotFound() {
        FileManager fileManager = new FileManager("nonexistent.txt", ",");

        List<Task> tasks = fileManager.giveTasks();

        assertTrue(tasks.isEmpty());
    }

    @Test
    void setPath_updatesPath() {
        FileManager fileManager = new FileManager("initialPath.txt", ",");
        fileManager.setPath("newPath.txt");

        assertEquals("newPath.txt", fileManager.getPath());
    }

    @Test
    void setDelimiter_updatesDelimiter() {
        FileManager fileManager = new FileManager("path.txt", ",");
        fileManager.setDelimiter("\t");

        assertEquals("\t", fileManager.getDelimiter());
    }
}
