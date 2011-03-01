package cz.wt.convertor.main.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Utility pro praci s excel souborem
 * @author diblik
 * @version $Revision: 160140 $
 */
public class ExcelUtils {

  public static Workbook getWorkbook(File excelSoubor) {
    try {
      if (excelSoubor.getName().endsWith(".xls")) {
        return getWorkbookXls(new FileInputStream(excelSoubor));
      }
//      if (excelSoubor.getName().endsWith(".xlsx")) {
//        return getWorkbookXlsx(new FileInputStream(excelSoubor));
//      }
    } catch (IOException ex) {
      Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
      //LOGGER.error("Nacitani Workbooku z excel souboru se napodarilo.", ex);
    }
    return null;
  }

  public static HSSFWorkbook getWorkbookXls(File excelSoubor) {
    try {
      if (excelSoubor != null) {
        return new HSSFWorkbook(new FileInputStream(excelSoubor));
      }
    } catch (IOException ex) {
      Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public static HSSFWorkbook getWorkbookXls(InputStream excelInputStream) {
    try {
      if (excelInputStream != null) {
        return new HSSFWorkbook(excelInputStream);
      }
    } catch (IOException ex) {
      Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

//  public static Workbook getWorkbookXlsx(InputStream excelInputStream) {
//    try {
//      if (excelInputStream != null) {
//        return new XSSFWorkbook(excelInputStream);
//      }
//    } catch (IOException ex) {
//      LOGGER.error("Nacitani Workbooku z excel souboru se napodarilo.", ex);
//    }
//    return null;
//  }
  public static List<String> getColumnStringCells(Sheet sheet, int columnIndex) {
    List<String> columnStringCells = new ArrayList<String>();
    List<Cell> columnCells = getColumnCells(sheet, columnIndex);

    for (Cell cell : columnCells) {
      if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
        columnStringCells.add(cell.getStringCellValue());
      }
    }

    return columnStringCells;
  }

  public static List<String> getColumnStringCells(Sheet sheet, String columnHeadName, boolean nevracetColumnHeadNameCell) {
    List<String> columnStringCells = new ArrayList<String>();
    List<Cell> columnCells = getColumnCells(sheet, columnHeadName, nevracetColumnHeadNameCell);

    for (Cell cell : columnCells) {
      if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
        columnStringCells.add(cell.getStringCellValue());
      }
    }

    return columnStringCells;
  }

  public static List<Cell> getColumnCells(Sheet sheet, int columnIndex) {
    List<Cell> columnCells = new ArrayList<Cell>();

    for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
      Row row = rowIt.next();
      Cell cell = row.getCell(columnIndex, Row.RETURN_BLANK_AS_NULL);
      if (cell != null) {
        columnCells.add(cell);
      }
    }

    return columnCells;
  }

  public static List<Cell> getColumnCells(Sheet sheet, String columnHeadName, boolean nevracetColumnHeadNameCell) {
    List<Cell> columnCells = new ArrayList<Cell>();
    boolean firstRow = true;
    int columnIndex = -1;
    for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
      Row row = rowIt.next();

      if (firstRow) {
        for (Iterator<Cell> celIt = row.iterator(); celIt.hasNext();) {
          Cell cell = celIt.next();
          if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING
              && cell.getStringCellValue().equals(columnHeadName)) {
            columnIndex = cell.getColumnIndex();
          }
        }
        firstRow = false;
      }

      if (columnIndex != -1) {
        Cell cell = row.getCell(columnIndex, Row.RETURN_BLANK_AS_NULL);
        if (cell != null) {
          columnCells.add(cell);
        }
      }
    }

    // vynechani nazvu sloupce
    if (nevracetColumnHeadNameCell && !columnCells.isEmpty()) {
      columnCells = columnCells.subList(1, columnCells.size());
    }

    return columnCells;
  }

  public static String getStringFromCell(Cell cell) {
    switch (cell.getCellType()) {
      case Cell.CELL_TYPE_STRING:
        return cell.getRichStringCellValue().getString();
      case Cell.CELL_TYPE_NUMERIC:
        // normalni double vraci ve formatu napr 4.606064020E9
        return String.valueOf((long) cell.getNumericCellValue());
      default:
        return String.valueOf(cell.getStringCellValue());
    }
  }
}
