package cz.wt.convertor.main.poi;

import cz.wt.convertor.main.jreports.datasource.DataSource;
import cz.wt.convertor.main.utils.ExcelUtils;
import cz.wt.convertor.main.utils.MessagesUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

/**
 * 
 * @author diblik
 * @version $Revision$
 */
public class TableRowCellProcessor {

  private SimpleDateFormat dateformat = new SimpleDateFormat("d.M.yyyy");

  public DataSource processExcelFile(File excelFile) throws DataReadingException {
    DataSource dataSource = null;
    try {

      HSSFWorkbook workbook = ExcelUtils.getWorkbookXls(excelFile);
      dataSource = processWokrbook(workbook);
      
    } catch (DataReadingException dataReadingException) {
      MessagesUtils.showWarning(null, dataReadingException.getMessage());
      throw dataReadingException;
    }
    return dataSource;
  }

  protected DataSource processWokrbook(HSSFWorkbook workbook) throws DataReadingException {
    DataSource dataSource = new DataSource();

    for (int k = 0; k < workbook.getNumberOfSheets(); k++) {
      HSSFSheet sheet = workbook.getSheetAt(k);
      processSheet(sheet, dataSource);
    }

    dataSource.findParam();
    if (workbook.getSheetAt(0) != null) {
      // -1 ptz se pocita i header
      dataSource.setItemCounter(workbook.getSheetAt(0).getPhysicalNumberOfRows()-1);
    }

    return dataSource;
  }

  protected void processSheet(HSSFSheet sheet, DataSource dataSource) throws DataReadingException {
    boolean firstRow = true;

    for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {

      Row row = rowIt.next();

      // prazdny radek preskocim
      if (row == null) {
        continue;
      }

      // zpracuji header
      if (firstRow) {
        processHeaderOfSheet(row, dataSource);
        firstRow = false;
        continue;
      }

      Object value = null;

      for (Iterator<Cell> celIt = row.iterator(); celIt.hasNext();) {
        Cell cell = celIt.next();

        // prazdnou bunku preskocim
        if (cell == null) {
          System.out.println("prazdna");
          continue;
        }

        switch (cell.getCellType()) {
          case HSSFCell.CELL_TYPE_NUMERIC:
            // je cislo datem?
            if (DateUtil.isCellDateFormatted(cell)) {
              value = dateformat.format(cell.getDateCellValue());
            } else {
              value = (Double.valueOf(cell.getNumericCellValue())).intValue();
            }
            break;

          case HSSFCell.CELL_TYPE_STRING:
            value = cell.getStringCellValue();
            break;

          case HSSFCell.CELL_TYPE_BLANK:
            break;

          default:
            throw new DataReadingException("Buòka je špatného typu. " + getInfoOfCell(cell) + ", Type:" + cell.
                getCellType());
        }

        if (value != null) {
          dataSource.addData(value, cell.getColumnIndex(), cell.getSheet().getSheetName());
        }
      }
    }
  }

  protected void processHeaderOfSheet(Row row, DataSource dataSource) throws DataReadingException {
    for (Iterator<Cell> celIt = row.iterator(); celIt.hasNext();) {
      Cell cell = celIt.next();

      if (cell == null) {
        throw new DataReadingException("Mezi sloupci v headeru tabulky nesmí být prázdné místo!." + getInfoOfCell(cell));
      }

      if (cell.getCellType() != HSSFCell.CELL_TYPE_STRING) {
        throw new DataReadingException("Jedna z bunìk v headeru tabulky není textového formátu." + getInfoOfCell(cell));
      }

      dataSource.createHeader(cell.getStringCellValue(), cell.getColumnIndex(), cell.getSheet().getSheetName());
    }
  }

  private String getInfoOfCell(Cell cell) {
    return " Pozice (sloupec: " + cell.getColumnIndex() + ", øádek: " + cell.getRowIndex() + ")" + ", Hodnota: " + ExcelUtils.
        getStringFromCell(cell);
  }
}
