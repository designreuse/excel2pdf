// $Id$
// Klasifikace: CHRÁNĚNÉ
package cz.wt.convertor.exceltopdf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.DateUtil;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * @author diblik
 * @version $Revision$
 */
/**
 * This example shows how to use the event API for reading a file.
 */
public class Test {

  /** dd.MM.yyyy **/
  public static final DateTimeFormatter DATE_FORMAT_DEFAULT = DateTimeFormat.forPattern("d.M.yyyy");

  private Map<String, Object> mapaParametru = new HashMap<String, Object>();

  /**
   * Read an excel file and spit out what we find.
   *
   * @param args      Expect one argument that is the file to read.
   * @throws IOException  When there is an error processing the file.
   */
  public static void main(String[] args) throws IOException {


    String fileName = "test.xls";
    if (args.length < 2) {

      HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(fileName));

      System.out.println("Data dump:\n");

      for (int k = 0; k < wb.getNumberOfSheets(); k++) {
        HSSFSheet sheet = wb.getSheetAt(k);
        int rows = sheet.getPhysicalNumberOfRows();
        System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
            + " row(s).");
        for (int r = 0; r < rows; r++) {
          HSSFRow row = sheet.getRow(r);
          if (row == null) {
            continue;
          }

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


//    // create a new file input stream with the input file specified
//    // at the command line
//    FileInputStream fin = new FileInputStream("test.xls");
//    // create a new org.apache.poi.poifs.filesystem.Filesystem
//    POIFSFileSystem poifs = new POIFSFileSystem(fin);
//    // get the Workbook (excel part) stream in a InputStream
//    InputStream din = poifs.createDocumentInputStream("Workbook");
//    // construct out HSSFRequest object
//    HSSFRequest req = new HSSFRequest();
//    // lazy listen for ALL records with the listener shown above
//    req.addListenerForAllRecords(new Test());
//    // create our event factory
//    HSSFEventFactory factory = new HSSFEventFactory();
//    // process our events based on the document input stream
//    factory.processEvents(req, din);
//    // once all the events are processed close our file input stream
//    fin.close();
//    // and our document input stream (don't want to leak these!)
//    din.close();
    System.out.println("done.");
  }
}
