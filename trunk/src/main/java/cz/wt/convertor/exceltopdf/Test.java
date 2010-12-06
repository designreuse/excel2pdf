// $Id$
// Klasifikace: CHR�?NĚNÉ
package cz.wt.convertor.exceltopdf;

import cz.wt.convertor.exceltopdf.jasperreports.datasource.DataSource;
import cz.wt.convertor.exceltopdf.jasperreports.TableRowCellProcessor;
import cz.wt.convertor.exceltopdf.bean.FileChooser;
import cz.wt.convertor.exceltopdf.utils.ProcessUtils;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 * 
 * @author diblik
 * @version $Revision$
 */
/**
 * This example shows how to use the event API for reading a file.
 */
public class Test {

  /**
   * Read an excel file and spit out what we find.
   *
   * @param args      Expect one argument that is the file to read.
   * @throws IOException  When there is an error processing the file.
   */
  public static void main(String[] args) throws IOException {

    TableRowCellProcessor rowCellProcessor = new TableRowCellProcessor();
    DataSource dataSource = rowCellProcessor.process(new File("test.xls"));
    System.out.println(dataSource.toString());

    JasperReport jr;
    try {
      jr = JasperCompileManager.compileReport("test.jrxml");
      JasperPrint jp = JasperFillManager.fillReport(jr, dataSource.getMapOfParams(), dataSource);
      byte[] exportReportToPdf = JasperExportManager.exportReportToPdf(jp);
      ProcessUtils.transformJRDataSourceToPDF(dataSource, new File("test.jrxml"));

//            FileChooser fileChooser = new FileChooser();
      //            File ulozenySoubor = fileChooser.ulozSoubor(this, "nezname");
      //    if (ulozenySoubor != null && getValue() != null) {
      //      try {
      //        FileUtils.writeByteArrayToFile(ulozenySoubor, getValue());
      //        OKMessageFactory.showInformationOk(this, "Soubor " + ulozenySoubor.getName() + " byl uložen na pevný disk.");
      //      } catch (IOException iOException) {
      //        OKMessageFactory.showWarningOk(this,
      //            DsCertifikatMessageCodes.DATOVA_SCHRANKA_CERTIFIKAT_SOUBOR_NEPODARILO_ULOZIT.getMessage());
      //      }
      //    }

    } catch (JRException ex) {
      Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
