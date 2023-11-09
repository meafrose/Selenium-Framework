package com.tutorial.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorial.utils.ExtentReporter;
import com.tutorial.utils.Utilities;

public class MyListeners implements ITestListener {
	
	String testName;
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		
		extentReport=ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		testName=result.getName();
		extentTest=extentReport.createTest(testName);
		extentTest.log(Status.INFO,testName+" started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		//result.getName();
		extentTest.log(Status.PASS,testName+" got successfully esecuted");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		 
	    //to retrieve the driver in object format
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationSrennshotPath=Utilities.captureSreenshot(driver, testName); 
		
		//attach ss to the extent report
		extentTest.addScreenCaptureFromPath(destinationSrennshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL,testName+" got failed");	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got skipped");
	}


	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush(); //if we don't flush, all the reports and logs will not be added, the report will be created with all the details 
		
		//For Auto-relaunching Extent Report
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport=new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
		
	


