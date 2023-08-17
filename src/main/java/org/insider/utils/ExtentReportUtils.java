package org.insider.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ExtentReportUtils {
    private static final String REPORT_NAME = "AllTests.html";
    public static ExtentReports extentReports;

    public static ExtentReports initialiseExtentReport(){
        ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter(REPORT_NAME);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter_all);
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        return extentReports;
    }

    public static void generateExtentReport(){
        extentReports.flush();
        try {
            Desktop.getDesktop().browse(new File("AllTests.html").toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
