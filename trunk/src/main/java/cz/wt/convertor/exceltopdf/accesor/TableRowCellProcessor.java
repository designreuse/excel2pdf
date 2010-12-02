// $Id$
// Klasifikace: CHRÁNĚNÉ
package cz.wt.convertor.exceltopdf.accesor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.record.formula.functions.Column;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.LocalDate;

/**
 * 
 * @author diblik
 * @version $Revision$
 */
public class TableRowCellProcessor {

  private HSSFWorkbook workbook;

  private Map<String, List<Object>> mapaDat = new HashMap<String, List<Object>>();

  public void readInputFile(File inputFile) {
    try {
      workbook = new HSSFWorkbook(new FileInputStream(inputFile));
    } catch (IOException ex) {
      Logger.getLogger(TableRowCellProcessor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void headerProcess(Row row) {
    for (Iterator<Cell> celIt = row.iterator(); celIt.hasNext();) {
      Cell cell = celIt.next();
      if (cell == null) {
        continue;
      }

      if (cell.getCellType() != HSSFCell.CELL_TYPE_STRING) {
        new RuntimeException("musi byt string");
      }

      mapaDat.put(cell.getStringCellValue(), new ArrayList<Object>());
    }
  }

  public void process() {
    for (int k = 0; k < workbook.getNumberOfSheets(); k++) {
      HSSFSheet sheet = workbook.getSheetAt(k);
      int rows = sheet.getPhysicalNumberOfRows();

      System.out.println("Sheet " + k + " \"" + workbook.getSheetName(k) + "\" has " + rows + " row(s).");
      processData(sheet);
    }

  }

  private void processData(HSSFSheet sheet) {
    boolean firstRow = false;



    for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
      Row row = rowIt.next();
      if (row == null) {
        continue;
      }

      // zpracuji header
      if (firstRow) {
        headerProcess(row);
        continue;
      }


      for (Iterator<Cell> celIt = row.iterator(); celIt.hasNext();) {
        Cell cell = celIt.next();
        if (cell == null) {
          continue;
        }

        switch (cell.getCellType()) {
          case HSSFCell.CELL_TYPE_NUMERIC:
            // je cislo datem?
            if (DateUtil.isCellDateFormatted(cell)) {
              value = "DATE value=" + new LocalDate(cell.getDateCellValue()).toString(DATE_FORMAT_DEFAULT);
            } else {
              value = "NUMERIC value=" + cell.getNumericCellValue();
            }
            break;

          case HSSFCell.CELL_TYPE_STRING:
            value = "STRING value=" + cell.getStringCellValue();
            break;

          default:
        }
      }

    }




    for (int r = 0; r < rows; r++) {
      HSSFRow row = sheet.getRow(r);


      int cells = row.getPhysicalNumberOfCells();
      System.out.println("\nROW " + row.getRowNum() + " has " + cells
          + " cell(s).");
      for (int c = 0; c < cells; c++) {
        HSSFCell cell = row.getCell(c);
        String value = null;
        if (cell != null) {
          switch (cell.getCellType()) {

            case HSSFCell.CELL_TYPE_FORMULA:
              value = "FORMULA value=" + cell.getCellFormula();
              break;

            case HSSFCell.CELL_TYPE_NUMERIC:
              if (DateUtil.isCellDateFormatted(cell)) {
                value = "DATE value=" + new LocalDate(cell.getDateCellValue()).toString(DATE_FORMAT_DEFAULT);
              } else {
                value = "NUMERIC value=" + cell.getNumericCellValue();
              }
              break;

            case HSSFCell.CELL_TYPE_STRING:
              value = "STRING value=" + cell.getStringCellValue();
              break;

            default:
          }
          System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="
              + value);
        }
      }
    }
  }
}

}
