package com.hanocybous.app.listener;

import com.hanocybous.app.AppController;
import com.hanocybous.app.SimpleRasterModel;
import com.hanocybous.app.ui.JTableViewer;
import com.hanocybous.dto.SimpleTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadFileActionListener implements ActionListener {
    private final AppController appController;
    private final JDesktopPane theDesktop;
    private final String delimiter;

    public LoadFileActionListener(AppController appController, JDesktopPane theDesktop, String delimiter) {
        this.appController = appController;
        this.theDesktop = theDesktop;
        this.delimiter = delimiter;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select a file");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Files", delimiter.equals("\t") ? "tsv" : "csv");
        jfc.addChoosableFileFilter(filter);
        jfc.setAcceptAllFileFilterUsed(true);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String pathString = jfc.getSelectedFile().getPath();
            SimpleTableModel tblModel = appController.load(pathString, delimiter);
            SimpleRasterModel raster = appController.translateTableModelToRaster(tblModel);
            showFrameWithTable(tblModel, pathString);
            showFrameWithRaster(raster, pathString);
        }
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
