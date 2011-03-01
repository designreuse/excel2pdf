/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.wt.convertor.main.jreports.datasource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author forrest
 */
public class DataSourceColumnItem implements Iterator<Object> {

    private String name = null;
    private Integer columnIndex = null;
    private List<Object> data = new ArrayList<Object>();
    private int readedCounter = 0;
    private String sheetName = null;

    public DataSourceColumnItem(String name, Integer columnIndex, String sheetName) {
//        System.out.println("ADD " + name + ", " + columnIndex + ", sheetNumber " + sheetName);
        this.sheetName = sheetName;
        this.name = name;
        this.columnIndex = columnIndex;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public void addData(Object data) {
        this.data.add(data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    @Override
    public boolean hasNext() {
        return readedCounter < data.size();
    }

    @Override
    public Object next() {
        Object object = data.get(readedCounter);
        readedCounter++;
        return object;
    }

    @Override
    public void remove() {
        data.remove(data.get(readedCounter));
    }

    @Override
    public String toString() {
        String dataStr = "";
        for (Object object : data) {
            dataStr += "   " + object + "\n";
        }
        dataStr += "\n ";
        return " name= " + name + "\n columnIndex= " + columnIndex + "\n sheetName= " + sheetName + "\n data: \n" + dataStr;
    }
}
