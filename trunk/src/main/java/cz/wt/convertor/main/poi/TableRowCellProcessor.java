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

    public DataSource processExcelFile(File excelFile) {
        HSSFWorkbook workbook = ExcelUtils.getWorkbookXls(excelFile);
        DataSource dataSource = processWokrbook(workbook);
        return dataSource;
    }

    protected DataSource processWokrbook(HSSFWorkbook workbook) {
        DataSource dataSource = new DataSource();

        for (int k = 0; k < workbook.getNumberOfSheets(); k++) {
            HSSFSheet sheet = workbook.getSheetAt(k);
            processSheet(sheet, dataSource);
        }

        dataSource.findParam();
        if (workbook.getSheetAt(1) != null) {
            dataSource.setItemCounter(workbook.getSheetAt(1).getPhysicalNumberOfRows());
        }

        return dataSource;
    }

    protected void processSheet(HSSFSheet sheet, DataSource dataSource) {
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
                        MessagesUtils.showWarning(null, "Bunka je špatného typu. " + cell.getCellType());
                }

                if (value != null) {
                    dataSource.addData(value, cell.getColumnIndex(), cell.getSheet().getSheetName());
                }
            }
        }
    }

    private void processHeaderOfSheet(Row row, DataSource dataSource) {
        for (Iterator<Cell> celIt = row.iterator(); celIt.hasNext();) {
            Cell cell = celIt.next();

            if (cell == null) {
                MessagesUtils.showWarning(null, "Mezi sloupci v headeru tabulky nesmí být prázdné místo!.");
            }

            if (cell.getCellType() != HSSFCell.CELL_TYPE_STRING) {
                MessagesUtils.showWarning(null, "Jedna z bunek v headeru tabulky neni textoveho formátu. " + ExcelUtils.getStringFromCell(cell));
            }

            dataSource.createHeader(cell.getStringCellValue(), cell.getColumnIndex(), cell.getSheet().getSheetName());
        }
    }
}
