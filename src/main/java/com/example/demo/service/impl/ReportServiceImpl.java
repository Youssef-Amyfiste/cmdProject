package com.example.demo.service.impl;

import com.example.demo.bean.Report;
import com.example.demo.bean.StringResult;
import com.example.demo.service.facade.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    DataSource dataSource;
    @Autowired
    ResourceLoader resourceLoader;
    public String REPORT_RESULT_FOLDER = "C:\\Users\\hp\\AReports";

    public StringResult createReport(Report report) throws SQLException, IOException, JRException {
        try {
            Resource resource = resourceLoader.getResource("Classpath:/reports" + report.getName() + ".jasper");
            InputStream reportStream = resource.getInputStream();
            Map parameters = new HashMap();
            StringResult reportName = new StringResult();
            parameters.put("REPORT_FOLDER", resourceLoader.getResource("Classpath:/reports" + report.getName() + ".jasper").getFile().getAbsolutePath());
            Connection conn = this.dataSource.getConnection();
            byte[] reportBytes = JasperRunManager.runReportToPdf(reportStream, parameters, conn);
            reportName.setName(report.getName() + "_" + System.currentTimeMillis() + ".pdf");
            FileOutputStream fileOutputStream = new FileOutputStream(REPORT_RESULT_FOLDER + reportName.getName());
            fileOutputStream.write(reportBytes);
            return reportName;
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
