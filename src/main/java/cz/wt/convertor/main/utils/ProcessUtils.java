/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.wt.convertor.main.utils;

import cz.wt.convertor.main.jreports.datasource.DataSource;
import cz.wt.convertor.main.poi.TableRowCellProcessor;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author forrest
 */
public class ProcessUtils {

  public static DataSource loadExcelFileToDataSource(File excelFile) {
    if (excelFile != null) {
      TableRowCellProcessor rowCellProcessor = new TableRowCellProcessor();
      DataSource dataSource = rowCellProcessor.processExcelFile(excelFile);
      return dataSource;
    }
    return null;
  }

  public static void printJRDataSource(DataSource dataSource, File jasperTemplate) {
    if (jasperTemplate != null) {
      JasperReport jr;
      try {
        jr = JasperCompileManager.compileReport(jasperTemplate.getPath());
        JasperPrint jp = JasperFillManager.fillReport(jr, dataSource.getMapOfParams(), dataSource);
        JasperPrintManager.printReport(jp, true);
      } catch (JRException ex) {
        Logger.getLogger(ProcessUtils.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static byte[] transformJRDataSourceToPDF(DataSource dataSource, File jasperTemplate) {
    if (jasperTemplate != null) {
      JasperReport jr;
      try {
        jr = JasperCompileManager.compileReport(jasperTemplate.getPath());
        JasperPrint jp = JasperFillManager.fillReport(jr, dataSource.getMapOfParams(), dataSource);
        return JasperExportManager.exportReportToPdf(jp);
      } catch (JRException ex) {
        Logger.getLogger(ProcessUtils.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return null;
  }

  public static File saveFileFromByteArray(File savedFile, byte[] byteArray) {
    if (savedFile != null) {
      try {
        FileUtils.writeByteArrayToFile(savedFile, byteArray);
        return savedFile;
      } catch (IOException ex) {
        Logger.getLogger(ProcessUtils.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return null;
  }
}
