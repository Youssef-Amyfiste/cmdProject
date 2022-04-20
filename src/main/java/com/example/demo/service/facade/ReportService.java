package com.example.demo.service.facade;

import com.example.demo.bean.Report;
import com.example.demo.bean.StringResult;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;

public interface ReportService {
    public StringResult createReport(Report report) throws SQLException, IOException, JRException;
}
