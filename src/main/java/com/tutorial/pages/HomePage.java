package com.tutorial.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//OBJECTS
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDrop;            //ENCAPTULATION CONCEPT
	  
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(name="search")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;
	
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this); // PageFActory design pattern  by selenium provides "PageFactory.initElements()" which automatically to initialize so that the 'WebElement' can be attached to the 'Locators', and it will overcome the "StaleElemenet Exception".

	}
	
	//Using those above WebElements we are performing some ACTIONS
	public void  clickOnMyAccount()
	{
		myAccountDrop.click();
	}
	
	public LoginPage selectLoginOption()
	{
		loginOption.click();
		return new LoginPage(driver);
	}
	
	
	public LoginPage navigateToLoginPage()
	{
		myAccountDrop.click();
		loginOption.click();
		return new LoginPage(driver);
		
	}
	public RegisterPage selectRegisterOption()
	{
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterProductIntoSearchBoxField(String productText)
	{
	    searchBoxField.sendKeys(productText);
	}
	
	public SearchPage clickOnSearchButton()
	{
		searchButton.click();
		return new SearchPage(driver);
	}

}
