package com.hanocybous.app;

import java.io.Serial;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * @author pvassil
 *
 */
public class SimpleRasterModel extends AbstractTableModel {

    @Serial
    private static final long serialVersionUID = 1L;
    private List<String> header;
    private List<String> zeroColumn;
    private final String[][] data;

    public SimpleRasterModel(List<String> header, List<String> zeroColumn, String[][] data) {
        super();
        this.header = header;
        this.zeroColumn = zeroColumn;
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getValueAt(int arg0, int arg1) {
        return data[arg0][arg1];
    }
}
