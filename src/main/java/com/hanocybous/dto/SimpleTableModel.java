package com.hanocybous.dto;

import javax.swing.table.AbstractTableModel;
import java.io.Serial;
import java.util.List;

public class SimpleTableModel extends AbstractTableModel {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String[] columnNames;
    private List<String[]> data;
    private final String name;
    private final String prjName;

    public SimpleTableModel(String name, String prjName, String[] pColumnNames, List<String[]> pData) {
        this.name = name;
        this.prjName = prjName;
        this.columnNames = pColumnNames;
        this.data = pData;
        this.fireTableDataChanged();
    }


    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data.get(row)[col];
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public String getName() {
        return this.name;
    }

    public String getPrjName() {
        return this.prjName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SimpleTableModel: name=")
                .append(this.name)
                .append(", prjName=")
                .append(this.prjName)
                .append(", columnNames=");
        for (String s : this.columnNames) {
            sb.append(s).append(", ");
        }
        sb.append("data=");
        for (String[] s : this.data) {
            sb.append("[");
            for (String s2 : s) {
                sb.append(s2)
                        .append(", ");
            }
            sb.append("], ");
        }
        return sb.toString();
    }
}
