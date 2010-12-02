// $Id$
// Klasifikace: CHR�?NĚNÉ
package cz.wt.convertor.exceltopdf;

import cz.wt.convertor.exceltopdf.accesor.DataSource;
import cz.wt.convertor.exceltopdf.accesor.TableRowCellProcessor;
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
        DataSource dataSource = rowCellProcessor.process("test.xls");
        System.out.println(dataSource.toString());

        JasperReport jr;
        try {
            jr = JasperCompileManager.compileReport("test1.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(jr, dataSource.getMapOfParams(), dataSource);
            JasperExportManager.exportReportToPdfFile(jp, "export.pdf");
        } catch (JRException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
