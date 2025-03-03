package com.hanocybous.app.gui;

import com.hanocybous.app.AppController;
import com.hanocybous.backend.ReportType;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportActionListener implements ActionListener {
    private final AppController appController;
    private final ReportType reportType;

    public ReportActionListener(AppController appController, ReportType reportType) {
        this.appController = appController;
        this.reportType = reportType;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select a target file to save");
        int destiny = jfc.showSaveDialog(null);
        if (destiny == JFileChooser.APPROVE_OPTION) {
            String path = jfc.getSelectedFile().getPath();
            switch (reportType) {
                case TEXT -> appController.createReportText(path);
                case MD -> appController.createReportMd(path);
                case HTML -> appController.createReportHtml(path);
            }
        }
    }
}
