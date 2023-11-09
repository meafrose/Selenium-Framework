package com.tutorial.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentReport=new ExtentReports();
		
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("SeleniumTest Automation Result Report");
		spark.config().setDocumentTitle("Automation Reports");
		spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(spark);
		
		Properties configProp=new Properties();
		File configPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorial\\config\\Config.properties");
		
		try {
		FileInputStream fis=new FileInputStream(configPropFile);
		configProp.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	
		
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("BrowserName", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating system", System.getProperty("os.name"));
		extentReport.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		
		return extentReport;
		

	}

}
