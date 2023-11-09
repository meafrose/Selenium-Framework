package com.tutorial.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorial.base.Base;
import com.tutorial.pages.AccountPage;
import com.tutorial.pages.HomePage;
import com.tutorial.pages.LoginPage;
import com.tutorial.utils.Utilities;

public class LoginTest extends Base{
	
	LoginPage loginPage;
	
	public LoginTest()
	{
		super(); //base class will be called
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver=initializeBrowserAndOpenApplication(p.getProperty("browserName"));
		HomePage homepage=new HomePage(driver);
		loginPage = homepage.navigateToLoginPage();
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="validCredentialSupplier")
	public void verifyWithValidLoginCred(String Email, String Password)
	{
		loginPage.enterEmailAddress(Email);
		loginPage.enterPassword(Password);
		AccountPage accountpage=loginPage.clickOnLoginButton();
		
		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationOption());
	}
	
	@DataProvider(name="validCredentialSupplier")
	public Object[][] supplyTestData()
	{
		Object[][]data= Utilities.getTestDataFromExcel("Login");
        return data;
	}

	@Test(priority=2)
	public void verifyWithInValidLoginCred()
	{
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(pData.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=pData.getProperty("emailPasswordNotMatchingWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}
	
	@Test(priority=3)
	public void verifyWithInValidMailAndValidPass()
	{
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(p.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=pData.getProperty("emailPasswordNotMatchingWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}
	
	@Test(priority=4)
	public void verifyWithValidMailAndInvalidPass()
	{
		loginPage.enterEmailAddress(p.getProperty("validEmail"));
		loginPage.enterPassword(p.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=pData.getProperty("emailPasswordNotMatchingWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");

	}
		
	@Test(priority=5)
	public void verifyWithoutMailAndIPass()
	{
		
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=pData.getProperty("emailPasswordNotMatchingWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}
}
