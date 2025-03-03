package com.hanocybous.reporter;

import com.hanocybous.backend.ReportType;
import com.hanocybous.model.Task;

import java.util.ArrayList;
import java.util.List;

class Reporter implements IReporter {

    private final String path;
    private ReportType type;
    private final ArrayList<Task> tasks;

    public Reporter(String path, List<Task> tasks) {
        this.path = path;
        this.tasks = (ArrayList<Task>) tasks;
    }

    public Reporter(String path, List<Task> tasks, ReportType type) {
        this.path = path;
        this.tasks = (ArrayList<Task>) tasks;
        this.type = type;
    }

    @Override
    public int makeReportTXT() {
        return new TXTReportGenerator(path, tasks).generate();
    }

    @Override
    public int makeReportHTML() {
        return new HTMLReportGenerator(path, tasks).generate();
    }

    @Override
    public int makeReportMD() {
        return new MDReportGenerator(path, tasks).generate();
    }

    @Override
    public int createReport() {
        return switch (type) {
            case TEXT -> makeReportTXT();
            case HTML -> makeReportHTML();
            case MD -> makeReportMD();
            default -> 1;
        };
    }
}
