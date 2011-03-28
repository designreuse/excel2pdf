/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.wt.convertor.main.jreports.datasource;

import cz.wt.convertor.main.utils.MessagesUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author forrest
 */
public class DataSource implements JRDataSource {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataSource.class);

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
    LOGGER.trace("addData: " + data + ", column index: " + columnIndex + ", sheet name: " + sheetName);
    for (DataSourceColumnItem dataSourceColumnItems : dataSourceItems) {
      if (dataSourceColumnItems.getColumnIndex() == columnIndex
          && dataSourceColumnItems.getSheetName().equals(sheetName)) {
        LOGGER.debug("added: " + data + ", column index: " + columnIndex + ", sheet name: " + sheetName);
        dataSourceColumnItems.addData(data);
      }
    }
  }

  public void findParam() {
    LOGGER.trace("find params");
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
        Object value = null;
        if (dataSourceColumnItemParamValue.getData().size() > index) {
          value = dataSourceColumnItemParamValue.getData().get(index);
        }
        LOGGER.debug("param name: " + object + ", value: " + value);
        mapOfParams.put((String) object, value);
        index++;
      }
      dataSourceItems.remove(dataSourceColumnItemParamName);
      dataSourceItems.remove(dataSourceColumnItemParamValue);
    } else {
      LOGGER.warn("Params not found.");
    }
  }

  @Override
  public boolean next() throws JRException {
    readedCounter++;
    return readedCounter <= itemCounter;
  }

  @Override
  public Object getFieldValue(JRField jrf) throws JRException {

    LOGGER.trace("finding " + jrf.getName() + " (" + jrf.getValueClassName() + ")");
    for (DataSourceColumnItem dataSourceColumnItems : dataSourceItems) {

      if (dataSourceColumnItems.getName().equals(jrf.getName())) {

        LOGGER.trace("found dataSourceColumnItems");
        if (dataSourceColumnItems.hasNext()) {
          Object object = dataSourceColumnItems.next();

          if (!jrf.getValueClass().equals(object.getClass())) {
            LOGGER.error("Nenalezen ocekavany. ocekavan: " + jrf.getValueClass() + ", nalezen: " + object.getClass());
            MessagesUtils.showError(null, "Nenalezen ocekavany. ocekavan: " + jrf.getValueClass() + ", nalezen: " + object.
                getClass());
          }

          LOGGER.debug("Has next: " + object);
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

    String dataSourceItemsStr = "";
    for (DataSourceColumnItem columnItem : dataSourceItems) {
      dataSourceItemsStr += "(" + columnItem + ")";
    }

    return dataSourceItemsStr + "\n ------------------------- \n " + mapaStr;

  }
}
