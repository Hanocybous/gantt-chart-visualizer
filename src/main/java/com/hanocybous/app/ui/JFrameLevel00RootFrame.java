package com.hanocybous.app.gui;

import com.hanocybous.app.AppController;
import com.hanocybous.dto.SimpleTableModel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class JFrameLevel00RootFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final AppController appController;
    private final JDesktopPane theDesktop;

    public JFrameLevel00RootFrame() {
        super("Gantt Manager with Java Swing");
        appController = new AppController();
        theDesktop = new JDesktopPane();
        this.add(theDesktop);
        this.setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menubar = new JMenuBar();
        menubar.add(createFileMenu());
        menubar.add(createFiltersMenu());
        menubar.add(createReportMenu());
        menubar.add(createSettingsMenu());
        menubar.add(createHelpMenu());
        return menubar;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem miLoadTsv = new JMenuItem("Load TSV");
        JMenuItem miLoadCsv = new JMenuItem("Load CSV");
        JMenuItem miExit = new JMenuItem("Exit");

        miLoadTsv.addActionListener(new LoadFileActionListener(appController, theDesktop, "\t"));
        miLoadCsv.addActionListener(new LoadFileActionListener(appController, theDesktop, ","));
        miExit.addActionListener(e -> {
            int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Warning", JOptionPane.OK_CANCEL_OPTION);
            if (dialogButton == JOptionPane.OK_OPTION) {
                System.exit(NORMAL);
            }
        });

        fileMenu.add(miLoadTsv);
        fileMenu.add(miLoadCsv);
        fileMenu.add(miExit);
        return fileMenu;
    }

    private JMenu createFiltersMenu() {
        JMenu filtersMenu = new JMenu("Filters");
        JMenuItem miTopLevel = new JMenuItem("Top Level Tasks");
        JMenuItem miFilterId = new JMenuItem("Filter by Id");
        JMenuItem miFilterPrefix = new JMenuItem("Filter by Prefix");

        miTopLevel.addActionListener(new FilterActionListener(appController, theDesktop, "Top Level Tasks", FilterType.TOP_LEVEL));
        miFilterId.addActionListener(new FilterActionListener(appController, theDesktop, "Filter by Id", FilterType.BY_ID));
        miFilterPrefix.addActionListener(new FilterActionListener(appController, theDesktop, "Filter by Prefix", FilterType.BY_PREFIX));

        filtersMenu.add(miTopLevel);
        filtersMenu.add(miFilterId);
        filtersMenu.add(miFilterPrefix);
        return filtersMenu;
    }

    private JMenu createReportMenu() {
        JMenu reportMenu = new JMenu("Report");
        JMenuItem miReportTxt = new JMenuItem("Report TXT");
        JMenuItem miReportMd = new JMenuItem("Report Markdown");
        JMenuItem miReportHtml = new JMenuItem("Report HTML");

        miReportTxt.addActionListener(new ReportActionListener(appController, ReportType.TEXT));
        miReportMd.addActionListener(new ReportActionListener(appController, ReportType.MD));
        miReportHtml.addActionListener(new ReportActionListener(appController, ReportType.HTML));

        reportMenu.add(miReportTxt);
        reportMenu.add(miReportMd);
        reportMenu.add(miReportHtml);
        return reportMenu;
    }

    private JMenu createSettingsMenu() {
        JMenu settingsMenu = new JMenu("Settings");
        JMenuItem miReloadTable = new JMenuItem("Reload Table");
        JMenu miTaskSettings = new JMenu("Task Settings");
        JMenuItem miAddTask = new JMenuItem("Add Task");
        JMenuItem miDeleteTask = new JMenuItem("Delete Task");
        JMenuItem miUpdateTask = new JMenuItem("Update Task");

        miReloadTable.addActionListener(new ReloadTableActionListener(appController, theDesktop));
        miAddTask.addActionListener(new TaskSettingsActionListener(appController, theDesktop, TaskAction.ADD));
        miDeleteTask.addActionListener(new TaskSettingsActionListener(appController, theDesktop, TaskAction.DELETE));
        miUpdateTask.addActionListener(new TaskSettingsActionListener(appController, theDesktop, TaskAction.UPDATE));

        miTaskSettings.add(miAddTask);
        miTaskSettings.add(miDeleteTask);
        miTaskSettings.add(miUpdateTask);
        settingsMenu.add(miReloadTable);
        settingsMenu.add(miTaskSettings);
        return settingsMenu;
    }

    private JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu("Help");
        JMenuItem miHelp = new JMenuItem("Help");
        JMenuItem miAbout = new JMenuItem("About");

        helpMenu.add(miHelp);
        helpMenu.add(miAbout);
        return helpMenu;
    }
}
