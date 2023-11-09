package com.tutorial.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorial.base.Base;
import com.tutorial.pages.HomePage;
import com.tutorial.pages.SearchPage;

public class SearchTest extends Base{
	
	SearchPage searchpage;
	
	public SearchTest()
	{
		super(); //base class will be called
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver=initializeBrowserAndOpenApplication(p.getProperty("browserName"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySeachWithValidProduct()
	{
		HomePage homepage=new HomePage(driver);
		homepage.enterProductIntoSearchBoxField(pData.getProperty("validProduct"));
		searchpage=homepage.clickOnSearchButton();
		
		Assert.assertTrue(searchpage.displayStatusOfHPValidProduct(),"HP is not displayed");
	}
	
	@Test(priority=2)
	public void verifySeachWithINValidProduct()
	{
		HomePage homepage=new HomePage(driver);
		homepage.enterProductIntoSearchBoxField(pData.getProperty("invalidProduct"));
		searchpage=homepage.clickOnSearchButton();
		
		String actualSeachMessage=searchpage.reteieveNoProductMessageText();
		Assert.assertEquals(actualSeachMessage,pData.getProperty("NoProductInSearch"));
	}
	
	@Test(priority=3)
	public void verifySeachWithNoProduct()
	{
		HomePage homepage=new HomePage(driver);
		searchpage=homepage.clickOnSearchButton();
		
		String actualSeachMessage=searchpage.reteieveNoProductMessageText();
		Assert.assertEquals(actualSeachMessage,pData.getProperty("NoProductInSearch"));
	}
	
	
}
