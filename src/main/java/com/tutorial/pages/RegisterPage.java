package com.tutorial.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement yesNewsletterOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class, 'alert alert-danger alert-dismissible')]")
	private WebElement privacyPlicyWarning;
	
	@FindBy(xpath="//input[@name='firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@name='lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailAddressWarning;
	
	@FindBy(xpath="//input[@name='telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@name='password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
	
	this.driver=driver;
	PageFactory.initElements(driver, this);

}
		
	public void enterFirstName(String firstnameText)
	{
		firstNameField.sendKeys(firstnameText);
	}
	
	public void enterLastName(String lastNameText)
	{
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterTelephoneNumber(String telephoneText)
	{
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPasswordField(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String passwordConfirmText)
	{
		passwordConfirmField.sendKeys(passwordConfirmText);
	}
	
	public void selectPrivacyPolicy()
	{
		privacyPolicyField.click();
	}
	
	public AccountSuccessPage clickOnContinueButton()
	{
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesNewsletterOption()
	{
		yesNewsletterOption.click();
	}
	
	public String retrieveDuplicateEmailAddressWarning()
	{
		String duplicateEmailWarningText=duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	
	public String retrievePrivacyPolicyWarning()
	{
		String privacyPlicyWarningText=privacyPlicyWarning.getText();
		return privacyPlicyWarningText;
	}
	
	public String retrieveFirstNameWarning()
	{
		String firstNameWarningText=firstNameWarning.getText();
		return firstNameWarningText;
	}
	
	public String retrieveLastNameWarning()
	{
		String lastNameWarningText=lastNameWarning.getText();
		return lastNameWarningText;
	}
	
	public String retrieveEmailAddressWarning()
	{
		String emailAddressWarningText=emailAddressWarning.getText();
		return emailAddressWarningText;
	}
	
	public String retrieveTelephoneWarning()
	{
		String telephoneWarningText=telephoneWarning.getText();
		return telephoneWarningText;
	}
	
	public String retrievePasswordWarning()
	{
		String passwordWarningText=passwordWarning.getText();
		return passwordWarningText;
	}
}
