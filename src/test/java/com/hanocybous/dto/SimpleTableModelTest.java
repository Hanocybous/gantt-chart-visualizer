package com.hanocybous.dto;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SimpleTableModelTest {

    @Test
    void getColumnCount_returnsCorrectNumberOfColumns() {
        String[] columnNames = {"Column1", "Column2"};
        List<String[]> data = Arrays.asList(new String[][]{{"Data1", "Data2"}});
        SimpleTableModel model = new SimpleTableModel("TestName", "TestPrjName", columnNames, data);

        assertEquals(2, model.getColumnCount());
    }

    @Test
    void getRowCount_returnsCorrectNumberOfRows() {
        String[] columnNames = {"Column1", "Column2"};
        List<String[]> data = Arrays.asList(new String[][]{{"Data1", "Data2"}, {"Data3", "Data4"}});
        SimpleTableModel model = new SimpleTableModel("TestName", "TestPrjName", columnNames, data);

        assertEquals(2, model.getRowCount());
    }

    @Test
    void getColumnName_returnsCorrectColumnName() {
        String[] columnNames = {"Column1", "Column2"};
        List<String[]> data = Arrays.asList(new String[][]{{"Data1", "Data2"}});
        SimpleTableModel model = new SimpleTableModel("TestName", "TestPrjName", columnNames, data);

        assertEquals("Column1", model.getColumnName(0));
        assertEquals("Column2", model.getColumnName(1));
    }

    @Test
    void getValueAt_returnsCorrectValue() {
        String[] columnNames = {"Column1", "Column2"};
        List<String[]> data = Arrays.asList(new String[][]{{"Data1", "Data2"}});
        SimpleTableModel model = new SimpleTableModel("TestName", "TestPrjName", columnNames, data);

        assertEquals("Data1", model.getValueAt(0, 0));
        assertEquals("Data2", model.getValueAt(0, 1));
    }

    @Test
    void setData_updatesDataCorrectly() {
        String[] columnNames = {"Column1", "Column2"};
        List<String[]> initialData = Arrays.asList(new String[][]{{"Data1", "Data2"}});
        SimpleTableModel model = new SimpleTableModel("TestName", "TestPrjName", columnNames, initialData);

        List<String[]> newData = Arrays.asList(new String[][]{{"NewData1", "NewData2"}});
        model.setData(newData);

        assertEquals("NewData1", model.getValueAt(0, 0));
        assertEquals("NewData2", model.getValueAt(0, 1));
    }

    @Test
    void toString_returnsCorrectStringRepresentation() {
        String[] columnNames = {"Column1", "Column2"};
        List<String[]> data = Arrays.asList(new String[][]{{"Data1", "Data2"}});
        SimpleTableModel model = new SimpleTableModel("TestName", "TestPrjName", columnNames, data);

        String expectedString = "SimpleTableModel: name=TestName, prjName=TestPrjName, columnNames=Column1, Column2, data=[Data1, Data2, ], ";
        assertEquals(expectedString, model.toString());
    }
}
