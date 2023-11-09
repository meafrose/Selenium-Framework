package com.tutorial.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorial.base.Base;
import com.tutorial.pages.AccountSuccessPage;
import com.tutorial.pages.HomePage;
import com.tutorial.pages.RegisterPage;
import com.tutorial.utils.Utilities;


public class RegisterTest extends Base {
	
	RegisterPage registerpage;
	AccountSuccessPage accountsuccesspage;
	
	public RegisterTest()
	{
		super(); //base class will be called
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver=initializeBrowserAndOpenApplication(p.getProperty("browserName"));
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		registerpage = homepage.selectRegisterOption();
	}
	
	@AfterMethod
	public void teatDown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void VerifyRegisteringAccountProvidingOnlytheMandatoryfields()
	{
		registerpage.enterFirstName(pData.getProperty("firstname"));
		registerpage.enterLastName(pData.getProperty("lastName"));
		registerpage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephoneNumber(pData.getProperty("telephoneNumber"));
		registerpage.enterPasswordField("meher@123");
		registerpage.enterConfirmPassword("meher@123");
		registerpage.selectPrivacyPolicy();
		accountsuccesspage=registerpage.clickOnContinueButton();
		
		String actualHeading= accountsuccesspage.reteieveAccountSuccessPageHeading();
		Assert.assertEquals(actualHeading,pData.getProperty("accountSuccessful"),"Page is not created successfully");
	}
	
	
	@Test(priority=2)
	public void VerifyRegisteringAccountbyProvidingAllfields()
	{
		
		
		registerpage.enterFirstName(pData.getProperty("firstname"));
		registerpage.enterLastName(pData.getProperty("lastName"));
		registerpage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephoneNumber(pData.getProperty("telephoneNumber"));
		registerpage.enterPasswordField("meher@123");
		registerpage.enterConfirmPassword("meher@123");
		registerpage.selectYesNewsletterOption();
		registerpage.selectPrivacyPolicy();
		accountsuccesspage=registerpage.clickOnContinueButton();
		
		String actualHeading= accountsuccesspage.reteieveAccountSuccessPageHeading();
		Assert.assertEquals(actualHeading,pData.getProperty("accountSuccessful"),"Page is not created successfully");
	}
	
	@Test(priority=3)
	public void VerifyRegisteringAccountbyProvidingExistingEmailaddress()
	{
		registerpage.enterFirstName(pData.getProperty("firstname"));
		registerpage.enterLastName(pData.getProperty("lastName"));
		registerpage.enterEmailAddress(p.getProperty("validEmail"));
		registerpage.enterTelephoneNumber(pData.getProperty("telephoneNumber"));
		registerpage.enterPasswordField("meher@123");
		registerpage.enterConfirmPassword("meher@123");
		registerpage.selectYesNewsletterOption();
		registerpage.selectPrivacyPolicy();
		registerpage.clickOnContinueButton();
		
		String actualWarning=registerpage.retrieveDuplicateEmailAddressWarning();
		Assert.assertEquals(actualWarning,pData.getProperty("existingEmailWarning"),"warning is not there");
	}
	
	@Test(priority=4)
	public void VerifyRegisteringAccountWithNoDetails()
	{
		registerpage.clickOnContinueButton();
		
		
		String actualPrivacyPolicyWarning=registerpage.retrievePrivacyPolicyWarning();
		Assert.assertEquals(actualPrivacyPolicyWarning,pData.getProperty("privacyPolicyWarning"),"Warning is not there");
		
		String actualFirstNameWarning=registerpage.retrieveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning,pData.getProperty("firstNameWarning"),"Warning is not there");
		
		String actualLastNameWarning=registerpage.retrieveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning,pData.getProperty("lastNameWarning"),"Warning is not there");
		
		String actualEmailAddressWarning=registerpage.retrieveEmailAddressWarning();
		Assert.assertEquals(actualEmailAddressWarning,pData.getProperty("emailWarning"),"Warning is not there");
		
		String actualTelephoneWarning=registerpage.retrieveTelephoneWarning();
		Assert.assertEquals(actualTelephoneWarning,pData.getProperty("telephoneWarning"),"Warning is not there");
		
		String actualPasswordWarning=registerpage.retrievePasswordWarning();
		Assert.assertEquals(actualPasswordWarning,pData.getProperty("passwordWarning"),"Warning is not there");
	}
}

