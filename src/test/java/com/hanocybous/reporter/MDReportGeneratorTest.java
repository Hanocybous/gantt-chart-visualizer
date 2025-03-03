package com.hanocybous.reporter;

import com.hanocybous.model.Task;
import com.hanocybous.model.SimpleTask;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MDReportGeneratorTest {

    @Test
    void generate_createsMarkdownFile_withCorrectContent() throws IOException {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0),
                new SimpleTask(2, "Task 2", 1, 6, 9, 50.0)
        );
        File tempFile = File.createTempFile("report", ".md");
        MDReportGenerator generator = new MDReportGenerator(tempFile.getPath(), tasks);

        int result = generator.generate();

        assertEquals(0, result);
        FileReader reader = new FileReader(tempFile);
        char[] buffer = new char[(int) tempFile.length()];
        reader.read(buffer);
        String content = new String(buffer);
        assertTrue(content.contains("TaskId\tTaskText\tMamaId\tStart\tEnd\tCost\t"));
        assertTrue(content.contains("1\tTask 1\t0\t5\t10\t100.0\t"));
        assertTrue(content.contains("2\tTask 2\t1\t6\t9\t50.0\t"));
        reader.close();
        tempFile.delete();
    }

    @Test
    void generate_returnsError_whenIOExceptionOccurs() {
        List<Task> tasks = Arrays.asList(
                new SimpleTask(1, "Task 1", 0, 5, 10, 100.0)
        );
        MDReportGenerator generator = new MDReportGenerator("/invalid/path/report.md", tasks);

        int result = generator.generate();

        assertEquals(1, result);
    }

    @Test
    void generate_createsEmptyTable_whenNoTasksProvided() throws IOException {
        List<Task> tasks = Arrays.asList();
        File tempFile = File.createTempFile("emptyReport", ".md");
        MDReportGenerator generator = new MDReportGenerator(tempFile.getPath(), tasks);

        int result = generator.generate();

        assertEquals(0, result);
        FileReader reader = new FileReader(tempFile);
        char[] buffer = new char[(int) tempFile.length()];
        reader.read(buffer);
        String content = new String(buffer);
        assertTrue(content.contains("TaskId\tTaskText\tMamaId\tStart\tEnd\tCost\t"));
        reader.close();
        tempFile.delete();
    }
}
