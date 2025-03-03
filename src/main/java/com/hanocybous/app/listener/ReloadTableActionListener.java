package com.hanocybous.app.listener;

import com.hanocybous.app.AppController;
import com.hanocybous.app.SimpleRasterModel;
import com.hanocybous.app.ui.JTableViewer;
import com.hanocybous.dto.SimpleTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReloadTableActionListener implements ActionListener {
    private final AppController appController;
    private final JDesktopPane theDesktop;

    public ReloadTableActionListener(AppController appController, JDesktopPane theDesktop) {
        this.appController = appController;
        this.theDesktop = theDesktop;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JInternalFrame[] frames = theDesktop.getAllFrames();
        for (JInternalFrame frame : frames) {
            frame.dispose();
        }
        SimpleTableModel tblModel = appController.getTaskList();
        SimpleRasterModel rasterModel = appController.translateTableModelToRaster(tblModel);
        showFrameWithTable(tblModel, "All Tasks");
        showFrameWithRaster(rasterModel, "All Tasks");
    }

    private void showFrameWithTable(SimpleTableModel tblModel, String title) {
        JInternalFrame frame = new JInternalFrame(title, true, true, true, true);
        JTableViewer jTableViewer = new JTableViewer(tblModel);
        frame.add(jTableViewer, BorderLayout.CENTER);
        jTableViewer.createAndShowJTable();
        frame.pack();
        theDesktop.add(frame);
        frame.setVisible(true);
    }

    private void showFrameWithRaster(SimpleRasterModel rasterModel, String title) {
        JInternalFrame frame = new JInternalFrame(title, true, true, true, true);
        JTableViewer jTableViewer = new JTableViewer(rasterModel);
        frame.add(jTableViewer, BorderLayout.CENTER);
        jTableViewer.createAndShowJTable();
        frame.pack();
        theDesktop.add(frame);
        frame.setVisible(true);
    }
}
