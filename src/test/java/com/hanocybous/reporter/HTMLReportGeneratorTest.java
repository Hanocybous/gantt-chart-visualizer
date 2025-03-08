package com.hanocybous.reporter;

import com.hanocybous.model.SimpleTask;
import com.hanocybous.model.Task;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HTMLReportGeneratorTest {

    @Test
    void generate_createsHTMLFile_withCorrectContent() throws IOException {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0),
                new SimpleTask(2, "Task 2", 1, 6, 9, 50.0)
        );
        File tempFile = File.createTempFile("report", ".html");
        HTMLReportGenerator generator = new HTMLReportGenerator(tempFile.getPath(), tasks);

        int result = generator.generate();

        assertEquals(0, result);
        FileReader reader = new FileReader(tempFile);
        char[] buffer = new char[(int) tempFile.length()];
        reader.read(buffer);
        String content = new String(buffer);
        assertTrue(content.contains("<td>1</td>"));
        assertTrue(content.contains("<td>Task 1</td>"));
        assertTrue(content.contains("<td>0</td>"));
        assertTrue(content.contains("<td>5</td>"));
        assertTrue(content.contains("<td>10</td>"));
        assertTrue(content.contains("<td>100.0</td>"));
        assertTrue(content.contains("<td>2</td>"));
        assertTrue(content.contains("<td>Task 2</td>"));
        assertTrue(content.contains("<td>1</td>"));
        assertTrue(content.contains("<td>6</td>"));
        assertTrue(content.contains("<td>9</td>"));
        assertTrue(content.contains("<td>50.0</td>"));
        reader.close();
        tempFile.delete();
    }

    @Test
    void generate_returnsError_whenIOExceptionOccurs() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0)
        );
        HTMLReportGenerator generator = new HTMLReportGenerator("/invalid/path/report.html", tasks);

        int result = generator.generate();

        assertEquals(1, result);
    }

    @Test
    void generate_createsEmptyTable_whenNoTasksProvided() throws IOException {
        List<Task> tasks = Arrays.asList();
        File tempFile = File.createTempFile("emptyReport", ".html");
        HTMLReportGenerator generator = new HTMLReportGenerator(tempFile.getPath(), tasks);

        int result = generator.generate();

        assertEquals(0, result);
        FileReader reader = new FileReader(tempFile);
        char[] buffer = new char[(int) tempFile.length()];
        reader.read(buffer);
        String content = new String(buffer);
        assertTrue(content.contains("<table border=\"1\">"));
        assertTrue(content.contains("<tr>"));
        assertTrue(content.contains("<th>TaskId</th>"));
        assertTrue(content.contains("<th>TaskText</th>"));
        assertTrue(content.contains("<th>MamaId</th>"));
        assertTrue(content.contains("<th>Start</th>"));
        assertTrue(content.contains("<th>End</th>"));
        assertTrue(content.contains("<th>Cost</th>"));
        assertTrue(content.contains("</tr>"));
        reader.close();
        tempFile.delete();
    }
}
