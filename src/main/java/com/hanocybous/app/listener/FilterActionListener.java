package com.hanocybous.app.gui;

import com.hanocybous.app.AppController;
import com.hanocybous.dto.SimpleTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterActionListener implements ActionListener {
    private final AppController appController;
    private final JDesktopPane theDesktop;
    private final String title;
    private final FilterType filterType;

    public FilterActionListener(AppController appController, JDesktopPane theDesktop, String title, FilterType filterType) {
        this.appController = appController;
        this.theDesktop = theDesktop;
        this.title = title;
        this.filterType = filterType;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        SimpleTableModel tblModel = switch (filterType) {
            case TOP_LEVEL -> appController.getTopLevel();
            case BY_ID -> {
                int selectedId = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID:"));
                yield appController.getById(selectedId);
            }
            case BY_PREFIX -> {
                String prefix = JOptionPane.showInputDialog("Enter the prefix:");
                yield appController.getByPrefix(prefix);
            }
        };
        showFrameWithTable(tblModel, title);
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
}
