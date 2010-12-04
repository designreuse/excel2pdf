// $Id: TableRowCellProcessor.java 5 2010-12-02 23:56:08Z diblikp $
// Klasifikace: CHR�?NĚNÉ
package cz.wt.convertor.exceltopdf.jasperreports;

import cz.wt.convertor.exceltopdf.jasperreports.datasource.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * @author diblik
 * @version $Revision: 5 $
 */
public class TableRowCellProcessor {

    private static final DateTimeFormatter DATE_FORMAT_DEFAULT = DateTimeFormat.forPattern("d.M.yyyy");
    private DataSource dataSource = new DataSource();
    private HSSFWorkbook workbook;

    public DataSource process(File inputFile) {
        readInputFileAndProcess(inputFile);

        for (int k = 0; k < workbook.getNumberOfSheets(); k++) {
            HSSFSheet sheet = workbook.getSheetAt(k);
            int rows = sheet.getPhysicalNumberOfRows();
            System.out.println("Sheet " + k + " \"" + workbook.getSheetName(k) + "\" has " + rows + " row(s).");

            processData(sheet);
        }
        dataSource.findParam();
        if (workbook.getSheetAt(1) != null) {
            dataSource.setItemCounter(workbook.getSheetAt(1).getPhysicalNumberOfRows());
        }

        return dataSource;
    }

    private void readInputFileAndProcess(File inputFile) {
        try {
            workbook = new HSSFWorkbook(new FileInputStream(inputFile));
        } catch (IOException ex) {
            Logger.getLogger(TableRowCellProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processData(HSSFSheet sheet) {
        boolean firstRow = true;

        for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {

            Row row = rowIt.next();
//            System.out.println("row " + row.getRowNum());
            if (row == null) {
                continue;
            }

            // zpracuji header
            if (firstRow) {
                headerProcess(row);
                firstRow = false;
                continue;
            }

            Object value = null;

            for (Iterator<Cell> celIt = row.iterator(); celIt.hasNext();) {
                Cell cell = celIt.next();
//                System.out.println("cell " + cell);
                if (cell == null) {
                    continue;
                }

                switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        // je cislo datem?
                        if (DateUtil.isCellDateFormatted(cell)) {
                            value = new LocalDate(cell.getDateCellValue()).toString(DATE_FORMAT_DEFAULT);
                        } else {
                            value = (Double.valueOf(cell.getNumericCellValue())).intValue();
                        }
                        break;

                    case HSSFCell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                }

                dataSource.addData(value, cell.getColumnIndex(), cell.getSheet().getSheetName());
            }
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


            dataSource.createHeader(cell.getStringCellValue(), cell.getColumnIndex(), cell.getSheet().getSheetName());

        }
    }
}
