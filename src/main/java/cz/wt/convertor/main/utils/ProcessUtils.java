/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.wt.convertor.main.utils;

import cz.wt.convertor.main.jreports.datasource.DataSource;
import cz.wt.convertor.main.poi.DataReadingException;
import cz.wt.convertor.main.poi.TableRowCellProcessor;
import java.io.File;
import java.io.IOException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author forrest
 */
public class ProcessUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessUtils.class);

  public static DataSource loadExcelFileToDataSource(File excelFile) throws DataReadingException {
    if (excelFile != null) {
      TableRowCellProcessor rowCellProcessor = new TableRowCellProcessor();
      DataSource dataSource = rowCellProcessor.processExcelFile(excelFile);
      return dataSource;
    }
    return null;
  }

  public static void printJRDataSource(DataSource dataSource, File jasperTemplate) throws JRException {
    if (jasperTemplate != null) {
      JasperReport jr;
      try {
        jr = JasperCompileManager.compileReport(jasperTemplate.getPath());
        JasperPrint jp = JasperFillManager.fillReport(jr, dataSource.getMapOfParams(), dataSource);
        JasperPrintManager.printReport(jp, true);
      } catch (JRException ex) {
        MessagesUtils.showError(null, "Došlo k problémùm pøi pokusu o tisk sestavy.");
        throw new JRException("Došlo k problémùm pøi pokusu o tisk sestavy.", ex);
      }
    }
  }

  public static byte[] transformJRDataSourceToPDF(DataSource dataSource, File jasperTemplate) throws JRException {
    if (jasperTemplate != null) {
      JasperReport jr;
      try {
        jr = JasperCompileManager.compileReport(jasperTemplate.getPath());
        JasperPrint jp = JasperFillManager.fillReport(jr, dataSource.getMapOfParams(), dataSource);
        return JasperExportManager.exportReportToPdf(jp);
      } catch (JRException ex) {
        MessagesUtils.showError(null, "Došlo k problémùm pøi pokusu o transformaci do PDF.");
        throw new JRException("Došlo k problémùm pøi pokusu o transformaci do PDF.", ex);
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
        MessagesUtils.showError(null, "Došlo k problémùm pøi ukládání souboru.");
        LOGGER.error("Došlo k problémùm pøi ukládání souboru.", ex);
      }
    }
    return null;
  }
}
