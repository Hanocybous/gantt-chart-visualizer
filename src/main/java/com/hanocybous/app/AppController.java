package com.hanocybous.app;

import com.hanocybous.backend.ReportType;
import com.hanocybous.backend.controller.IMainController;
import com.hanocybous.backend.controller.MainControllerFactory;
import com.hanocybous.dto.SimpleTableModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class AppController {
    private static IMainController mainEngine;

    public AppController() {
        MainControllerFactory mcFactory = new MainControllerFactory();
        mainEngine = mcFactory.createMainController();
    }

    public SimpleTableModel load(String fileName, String delimiter) {
        return mainEngine.load(fileName, delimiter);
    }

    public SimpleTableModel getByPrefix(String prefix) {
        return mainEngine.getTasksByPrefix(prefix);
    }

    public SimpleTableModel getById(int id) {
        return mainEngine.getTaskById(id);
    }

    public SimpleTableModel getTopLevel() {
        return mainEngine.getTopLevelTasks();
    }

    public int createReportText(String path) {
        return mainEngine.createReport(path, ReportType.TEXT);
    }

    public int createReportMd(String path) {
        return mainEngine.createReport(path, ReportType.MD);
    }

    public int createReportHtml(String path) {
        return mainEngine.createReport(path, ReportType.HTML);
    }

    @Contract("_ -> new")
    public @NotNull SimpleRasterModel translateTableModelToRaster(@NotNull SimpleTableModel tblModel) {
        int inputTblRows = tblModel.getRowCount();
        List<String[]> inputData = tblModel.getData();

        int end = Integer.MIN_VALUE;
        int start = Integer.MAX_VALUE;
        for (String[] ss: inputData) {
            int localStart = Integer.parseInt(ss[3].trim());
            int localEnd = Integer.parseInt(ss[4].trim());
            if(localStart < start) start = localStart;
            if(localEnd > end) end = localEnd;
        }

        int rasterNumRows = inputTblRows + 1;
        int rasterNumCols = end - start + 1 + 1;
        String [][] data = new String[rasterNumRows][rasterNumCols];

        List<String> header = new ArrayList<>();
        data[0][0] = "";
        header.add(data[0][0]);
        for (int j=1; j< rasterNumCols; j++) {
            String s = String.valueOf(start -1 + j);
            data[0][j] = s;
            header.add(s);
        }

        List<String> zeroColumn = new ArrayList<>();
        int currentRow = 1;
        for (String[] ss: inputData) {
            zeroColumn.add(ss[0]);
            data[currentRow][0] = ss[0];
            int localMama = Integer.parseInt(ss[2].trim());
            int localStart = Integer.parseInt(ss[3].trim());
            int localEnd = Integer.parseInt(ss[4].trim());
            for(int j = localStart; j<localEnd+1; j++) {
                if(localMama==0)
                    data[currentRow][j] = "**";
                else
                    data[currentRow][j] = "*";
            }
            currentRow++;
        }

        return new SimpleRasterModel(header, zeroColumn, data);
    }

    public SimpleTableModel getTaskList() {
        return mainEngine.getTaskList();
    }

    public void deleteTask(int selectedId) {
        mainEngine.deleteTask(selectedId);
    }

    public void updateTask(int selectedId, int choice, String newValue) {
        mainEngine.updateTask(selectedId, choice, newValue);
    }

    public void addTask(String taskText, int mamaId, String startDateString, String endDateString, double cost) {
        mainEngine.addTask(taskText, mamaId, startDateString, endDateString, cost);
    }

    public void addTask(String taskText, int mamaId, String startDateString, String endDateString, double cost,
                        int taskId) {
        mainEngine.addTask(taskText, mamaId, startDateString, endDateString, cost, taskId);
    }

    public String[] getTaskData(int mamaId) {
        return mainEngine.getTaskData(mamaId);
    }

    public void updateMamaTask(int mamaId, String mamaStartDateString, String mamaEndDateString, Double mamaCost) {
        mainEngine.updateMamaTask(mamaId, mamaStartDateString, mamaEndDateString, mamaCost);
    }

    public void deleteSubTasks(int selectedId) {
        mainEngine.deleteSubTasks(selectedId);
    }
}
