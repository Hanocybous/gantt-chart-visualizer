package com.hanocybous.app.listener;

import com.hanocybous.app.AppController;
import com.hanocybous.app.model.TaskAction;
import com.hanocybous.app.ui.JTableViewer;
import com.hanocybous.dto.SimpleTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskSettingsActionListener implements ActionListener {
    private final AppController appController;
    private final JDesktopPane theDesktop;
    private final TaskAction taskAction;

    public TaskSettingsActionListener(AppController appController, JDesktopPane theDesktop, TaskAction taskAction) {
        this.appController = appController;
        this.theDesktop = theDesktop;
        this.taskAction = taskAction;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (taskAction) {
            case ADD -> addTask();
            case DELETE -> deleteTask();
            case UPDATE -> updateTask();
        }
    }

    private void addTask() {
        JDialog dialog = new JDialog((Frame) null, "Add Task", true);
        JPanel p = new JPanel(new GridLayout(6, 2));
        JTextField[] textFields = new JTextField[6];
        String[] labels = {"Task Text", "Mama Id", "Start Date", "End Date", "Cost", "Task Id"};
        for (int i = 0; i < labels.length; i++) {
            p.add(new JLabel(labels[i]));
            textFields[i] = new JTextField(10);
            p.add(textFields[i]);
        }
        JButton okayButton = new JButton(new AbstractAction("OK") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskText = textFields[0].getText();
                int mamaId = Integer.parseInt(textFields[1].getText());
                String startDateString = textFields[2].getText();
                String endDateString = textFields[3].getText();
                double cost = Double.parseDouble(textFields[4].getText());
                int taskId = Integer.parseInt(textFields[5].getText());
                appController.addTask(taskText, mamaId, startDateString, endDateString, cost, taskId);
                reloadTable();
                dialog.dispose();
            }
        });
        p.add(okayButton);
        dialog.add(p);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void deleteTask() {
        JDialog dialog = new JDialog((Frame) null, "Delete Task", true);
        JPanel p = new JPanel();
        p.add(new JLabel("Enter the id of the task to delete"));
        JTextField textField = new JTextField(10);
        p.add(textField);
        JButton okayButton = new JButton(new AbstractAction("OK") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedId = Integer.parseInt(textField.getText());
                appController.deleteTask(selectedId);
                if (appController.getTaskData(selectedId).length == 0) {
                    appController.deleteSubTasks(selectedId);
                }
                reloadTable();
                dialog.dispose();
            }
        });
        p.add(okayButton);
        dialog.add(p);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void updateTask() {
        JDialog dialog = new JDialog((Frame) null, "Update Task", true);
        JPanel p = new JPanel();
        JTextField textField = new JTextField(10);
        p.add(textField);
        JButton okayButton = new JButton(new AbstractAction("OK") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedId = Integer.parseInt(textField.getText());
                String[] options = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost"};
                int choice = JOptionPane.showOptionDialog(null, "What do you want to update?", "Update Task", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                String newValue = JOptionPane.showInputDialog("Enter the new value");
                appController.updateTask(selectedId, choice, newValue);
                reloadTable();
                dialog.dispose();
            }
        });
        p.add(okayButton);
        dialog.add(p);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void reloadTable() {
        JInternalFrame[] frames = theDesktop.getAllFrames();
        for (JInternalFrame frame : frames) {
            frame.dispose();
        }
        SimpleTableModel taskList = appController.getTaskList();
        if (taskList != null) {
            taskList.fireTableDataChanged();
            showFrameWithTable(taskList, "Task List");
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
}
