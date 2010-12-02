// $Id: EventExample.java 3 2010-12-02 10:17:23Z diblikp $
// Klasifikace: CHR�?NĚNÉ
package cz.wt.convertor.exceltopdf.others;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * This example shows how to use the event API for reading a file.
 */
public class EventExample {

  public static Map<String, Object> getReportParameters() {
    return new HashMap<String, Object>();
  }

  public static JRDataSource getJRDataSource() {
    return new JRDataSource() {

      private int count = 0;

      public boolean next() throws JRException {
        count++;
        return count < 5;
      }

      public Object getFieldValue(JRField jrf) throws JRException {
        return "test";
      }
    };
  }

  /**
   * Read an excel file and spit out what we find.
   *
   * @param args      Expect one argument that is the file to read.
   * @throws IOException  When there is an error processing the file.
   */
  public static void main(String[] args) throws IOException, JRException {
    JasperReport jr = JasperCompileManager.compileReport("testx.jrxml");
    JasperPrint jp = JasperFillManager.fillReport(jr, getReportParameters(), getJRDataSource());
//    JasperPrintManager.printReport(jp, true);
    JasperExportManager.exportReportToPdf(jp);



//    String fileName = "test.xls";
//    try {
//      if (args.length < 2) {
//
//        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(fileName));
//
//        System.out.println("Data dump:\n");
//
//        for (int k = 0; k < wb.getNumberOfSheets(); k++) {
//          HSSFSheet sheet = wb.getSheetAt(k);
//          int rows = sheet.getPhysicalNumberOfRows();
//          System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
//              + " row(s).");
//          for (int r = 0; r < rows; r++) {
//            HSSFRow row = sheet.getRow(r);
//            if (row == null) {
//              continue;
//            }
//
//            int cells = row.getPhysicalNumberOfCells();
//            System.out.println("\nROW " + row.getRowNum() + " has " + cells
//                + " cell(s).");
//            for (int c = 0; c < cells; c++) {
//              HSSFCell cell = row.getCell(c);
//              String value = null;
//
//              switch (cell.getCellType()) {
//
//                case HSSFCell.CELL_TYPE_FORMULA:
//                  value = "FORMULA value=" + cell.getCellFormula();
//                  break;
//
//                case HSSFCell.CELL_TYPE_NUMERIC:
//                  value = "NUMERIC value=" + cell.getNumericCellValue();
//                  break;
//
//                case HSSFCell.CELL_TYPE_STRING:
//                  value = "STRING value=" + cell.getStringCellValue();
//                  break;
//
//                default:
//              }
//              System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="
//                  + value);
//            }
//          }
//        }
//      }
//      else if (args.length == 2) {
//				if (args[1].toLowerCase().equals("write")) {
//					System.out.println("Write mode");
//					long time = System.currentTimeMillis();
//					HSSFReadWrite.testCreateSampleSheet(fileName);
//
//					System.out.println("" + (System.currentTimeMillis() - time)
//							+ " ms generation time");
//				} else {
//					System.out.println("readwrite test");
//					HSSFWorkbook wb = HSSFReadWrite.readFile(fileName);
//					FileOutputStream stream = new FileOutputStream(args[1]);
//
//					wb.write(stream);
//					stream.close();
//				}
//			} else if (args.length == 3 && args[2].toLowerCase().equals("modify1")) {
//				// delete row 0-24, row 74 - 99 && change cell 3 on row 39 to string "MODIFIED CELL!!"
//
//				HSSFWorkbook wb = HSSFReadWrite.readFile(fileName);
//				FileOutputStream stream = new FileOutputStream(args[1]);
//				HSSFSheet sheet = wb.getSheetAt(0);
//
//				for (int k = 0; k < 25; k++) {
//					HSSFRow row = sheet.getRow(k);
//
//					sheet.removeRow(row);
//				}
//				for (int k = 74; k < 100; k++) {
//					HSSFRow row = sheet.getRow(k);
//
//					sheet.removeRow(row);
//				}
//				HSSFRow row = sheet.getRow(39);
//				HSSFCell cell = row.getCell(3);
//				cell.setCellValue("MODIFIED CELL!!!!!");
//
//				wb.write(stream);
//				stream.close();
//			}
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
  }
}
