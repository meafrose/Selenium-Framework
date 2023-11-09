package com.tutorial.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
WebDriver driver;
	
	//OBJECTS
	@FindBy(id="input-email")
	private WebElement emailAddressField;            //ENCAPTULATION CONCEPT
	  
	@FindBy(id="input-password")
	private WebElement passwordfield;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	
	public void enterEmailAddress(String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPassword(String passwordText)
	{
		passwordfield.sendKeys(passwordText);
	}
	
	public AccountPage clickOnLoginButton()
	{
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailPasswordNotMatchingWarningMessageText()
	{
		String warningText=emailPasswordNotMatchingWarning.getText();
		return warningText;
	}
	

}
