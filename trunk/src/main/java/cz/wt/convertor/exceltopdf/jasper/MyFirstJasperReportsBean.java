///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package wt.manager.web.controller.jasper;
//
//import wt.manager.web.controller.jasper.datasources.MyFirstJasperReportsDataSource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import net.sf.jasperreports.engine.JRDataSource;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.WebApplicationContext;
//import wt.manager.service.UzivatelService;
//import wt.manager.service.entity.Uzivatel;
//import wt.manager.web.controller.jasper.datasources.ZkouskyDataSource;
//
//@Component
//@Scope(WebApplicationContext.SCOPE_SESSION)
//public class MyFirstJasperReportsBean extends AbstractBaseReportBean {
//
//    private final String COMPILE_FILE_NAME = "test";
//    @Autowired
//    private UzivatelService uzivatelService;
//
//    @Override
//    protected String getCompileFileName() {
//        return COMPILE_FILE_NAME;
//    }
//
//    @Override
//    protected Map<String, Object> getReportParameters() {
//        Map<String, Object> reportParameters = new HashMap<String, Object>();
//
//        reportParameters.put("title", "Hello JasperReports");
//
//
//        return reportParameters;
//    }
//
//    @Override
//    protected JRDataSource getJRDataSource() {
//        // return your custom datasource implementation
//        MyFirstJasperReportsDataSource dataSource = new MyFirstJasperReportsDataSource();
//        List<Uzivatel> all = uzivatelService.getAll();
//
//        return new ZkouskyDataSource(all);
//    }
//
//    public String execute() {
//        try {
//            super.prepareReport();
//        } catch (Exception e) {
//            // make your own exception handling
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//}
