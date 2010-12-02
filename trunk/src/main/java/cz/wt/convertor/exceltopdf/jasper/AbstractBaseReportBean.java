///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package wt.manager.web.controller.jasper;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Map;
//
//import net.sf.jasperreports.engine.JRDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperPrintManager;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//
//public abstract class AbstractBaseReportBean implements Serializable {
//
//  public enum ExportOption {
//
//    PDF, HTML, EXCEL, RTF, PRINT
//  }
//
//  private ExportOption exportOption;
//
//  private final String COMPILE_DIR = "/pages/report/design/";
//
//  public AbstractBaseReportBean() {
//    super();
//    setExportOption(ExportOption.PDF);
//  }
//
//  protected void prepareReport() throws JRException, IOException {
//
//    JasperReport jr = JasperCompileManager.compileReport("reports/example2.jrxml");
//    JasperPrint jp = JasperFillManager.fillReport(jr, getReportParameters(), getJRDataSource());
////    JasperPrintManager.printReport(jp, true);
//    JasperExportManager.exportReportToPdf(jp);
//
//
////    ReportConfigUtil.compileReport(getCompileDir(), getCompileFileName());
////
////    File reportFile = new File(getCompileDir(), getCompileFileName() + ".jasper");
////
////    JasperPrint jasperPrint = ReportConfigUtil.fillReport(reportFile, getReportParameters(), getJRDataSource());
////
////    if (getExportOption().equals(ExportOption.HTML)) {
////      ReportConfigUtil.exportReportAsHtml(jasperPrint, response.getWriter());
////    } else {
////      if (getExportOption().equals(ExportOption.PRINT)) {
////        ReportConfigUtil.exportReportAsPrint(jasperPrint, response.getWriter());
////      } else {
////        if (getExportOption().equals(ExportOption.PRINT)) {
////          ReportConfigUtil.exportReportAsPrint(jasperPrint, response.getWriter());
////        }
////      }
////    }
//  }
//
//  public ExportOption getExportOption() {
//    return exportOption;
//  }
//
//  public void setExportOption(ExportOption exportOption) {
//    this.exportOption = exportOption;
//  }
//
//  protected Map<String, Object> getReportParameters() {
//    return new HashMap<String, Object>();
//  }
//
//  protected String getCompileDir() {
//    return COMPILE_DIR;
//  }
//
//  protected abstract JRDataSource getJRDataSource();
//
//  protected abstract String getCompileFileName();
//}
