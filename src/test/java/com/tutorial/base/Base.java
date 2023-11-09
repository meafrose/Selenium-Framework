package com.tutorial.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
//import java.util.Collections;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorial.utils.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties p;
	public Properties pData;
	
	public Base()
	{
		
		pData=new Properties();
		File dataProp=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorial\\testdata\\testdata.properties");
		
		try {
		FileInputStream f2=new FileInputStream(dataProp);
		pData.load(f2);
	}catch(Throwable e) {
		e.printStackTrace();
		}
		
		p=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorial\\config\\Config.properties");
		
		try {
		FileInputStream f=new FileInputStream(propFile);		
		p.load(f);;
	}catch(Throwable e) {
		e.printStackTrace();
	}
	}
	
	public WebDriver initializeBrowserAndOpenApplication(String browserName)
	{
		if(browserName.equals("chrome"))
		{
			
			ChromeOptions option=new ChromeOptions();
			//option.setExperimentalOption("excludeSwitchs",Collections.singletonList("enable-automation"));
			option.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(option);
		}
		else if(browserName.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browserName.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		
		//ChromeOptions option=new ChromeOptions();
		//option.setExperimentalOption("excludeSwitchs",Collections.singletonList("enable-automation"));
		//option.addArguments("--remote-allow-origins=*");
		
		//driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(p.getProperty("url"));
		
		return driver;
	}

}
