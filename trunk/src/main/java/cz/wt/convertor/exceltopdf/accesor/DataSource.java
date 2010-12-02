/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.wt.convertor.exceltopdf.accesor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author forrest
 */
public class DataSource implements JRDataSource {

    private static final String PARAM_NAME = "parametr-jmeno";
    private static final String PARAM_VALUE = "parametr-hodnota";
    private List<DataSourceColumnItem> dataSourceItems = new ArrayList<DataSourceColumnItem>();
    private Map<String, Object> mapOfParams = new HashMap<String, Object>();
    private int itemCounter = 0;
    private int readedCounter = 0;

    public void createHeader(String name, Integer columnIndex, String sheetName) {
        dataSourceItems.add(new DataSourceColumnItem(name, columnIndex, sheetName));
    }

    public void addData(Object data, Integer columnIndex, String sheetName) {
        for (DataSourceColumnItem dataSourceColumnItems : dataSourceItems) {
            if (dataSourceColumnItems.getColumnIndex() == columnIndex && dataSourceColumnItems.getSheetName().equals(sheetName)) {
//                System.out.println("ADD DATA " + columnIndex + ", " + data);
                dataSourceColumnItems.addData(data);
            }
        }
    }

    public void findParam() {
        DataSourceColumnItem dataSourceColumnItemParamName = null;
        DataSourceColumnItem dataSourceColumnItemParamValue = null;

        for (DataSourceColumnItem dataSourceColumnItems : dataSourceItems) {
            if (dataSourceColumnItems.getName().equals(PARAM_NAME)) {
                dataSourceColumnItemParamName = dataSourceColumnItems;
            } else {
                if (dataSourceColumnItems.getName().equals(PARAM_VALUE)) {
                    dataSourceColumnItemParamValue = dataSourceColumnItems;
                }
            }
        }

        if (dataSourceColumnItemParamName != null && dataSourceColumnItemParamValue != null) {
            int index = 0;
            for (Object object : dataSourceColumnItemParamName.getData()) {
                mapOfParams.put((String) object, dataSourceColumnItemParamValue.getData().get(index));
                index++;
            }
            dataSourceItems.remove(dataSourceColumnItemParamName);
            dataSourceItems.remove(dataSourceColumnItemParamValue);
        }

        System.out.println("COUNT " + itemCounter);

    }

    public boolean next() throws JRException {
        readedCounter++;
        return readedCounter <= itemCounter;
    }

    public Object getFieldValue(JRField jrf) throws JRException {

        for (DataSourceColumnItem dataSourceColumnItems : dataSourceItems) {
            if (dataSourceColumnItems.getName().equals(jrf.getName())) {
                if (dataSourceColumnItems.hasNext()) {
                    Object object = dataSourceColumnItems.next();
                    if (!jrf.getValueClass().equals(object.getClass())) {
                        throw new RuntimeException("Nenalezen ocekavanyp. ocekavan: " + jrf.getValueClass() + ", nalezen: " + object.getClass());
                    }
                    return object;
                }
            }
        }
        return null;
    }

    public Map<String, Object> getMapOfParams() {
        return mapOfParams;
    }

    public void setItemCounter(int itemCounter) {
        this.itemCounter = itemCounter;
    }

    @Override
    public String toString() {
        String mapaStr = "";
        for (String key : mapOfParams.keySet()) {
            mapaStr += "(" + key + ", " + mapOfParams.get(key) + ")";
        }
        return ArrayUtils.toString(dataSourceItems) + "\n ------------------------- \n " + mapaStr;

    }
}
