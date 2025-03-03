package com.hanocybous.reporter;

import com.hanocybous.backend.ReportType;
import com.hanocybous.model.Task;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReporterFactory {

    private static ReporterFactory instance = null;

    private ReporterFactory() {
    }

    public static ReporterFactory getInstance() {
        if (instance == null) {
            instance = new ReporterFactory();
        }
        return instance;
    }

    @Contract(pure = true)
    public static @Nullable IReporter getReporter(String path, List<Task> tasks, ReportType type) {
        Reporter reporter = new Reporter(path, tasks, type);
        if (type == ReportType.TEXT) {
            return reporter;
        } else if (type == ReportType.HTML) {
            return reporter;
        } else if (type == ReportType.MD) {
            return reporter;
        }
        else {
            return null;
        }
    }
}
