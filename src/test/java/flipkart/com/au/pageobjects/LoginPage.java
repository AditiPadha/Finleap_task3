package flipkart.com.au.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import flipkart.com.au.utils.Keywords;

public class LoginPage {

	Keywords key = new Keywords();

	public By loginAndSignUpButton=By.xpath("//a[text()='Login & Signup']");
	public By usernameInputField = By.xpath("//label/span[contains(text(),'Enter Email/Mobile number')]/../..//input");
	public By passwordField = By.xpath("//label/span[contains(text(),'Enter Password')]/../..//input");
	public By loginButton = By.xpath("//span[contains(text(),'Login')]/../../button");
	public By loginErrorMessage=By.xpath("//span[text()='Please enter valid Email ID/Mobile number' or text()='Your username or password is incorrect']");
	public By myAccountOption=By.xpath("//div[text()='My Account']");
	public By continueButton=By.xpath("//span[contains(text(),'CONTINUE')]/../../button");
	public By loginWithPassword=By.xpath("//button[text()='Login with Password']");
	public By loginChangeOption=By.xpath("//span[text()='Change?']");
	
	//if Login Window does not show up then clicking on this will Activate the same
	public void clickLoginAndSignUpButton(WebDriver driver) {
		key.findElementClick(driver, loginAndSignUpButton);
	}
	
	//Scenario2: Might come up as an login option with only user name input 
	public void clickContinueButton(WebDriver driver) {
		key.findElementClick(driver, continueButton);
	}
	
	//Scenario2: Will come up as option after click continue button
		public void clickLoginWithPasswordButton(WebDriver driver) {
			key.findElementClick(driver, loginWithPassword);
		}
	
	public void enterUserName(WebDriver driver, String userName) {
		key.findElementSendKey(driver, usernameInputField, userName);
	}

	public void enterPassword(WebDriver driver, String passwordData) {
		key.findElementClear(driver, passwordField);
		key.findElementSendKey(driver, passwordField, passwordData);
	}

	public void clickLoginButton(WebDriver driver) {
		key.findElementClick(driver, loginButton);
		
	}
	
	public void clearUserName(WebDriver driver) {
		key.findElementClear(driver, usernameInputField);
	}
	
	public void openURL(WebDriver driver, String URL) throws Exception {
		key.getUrl(driver, URL);
	}

	public boolean loginButtonIsPresent(WebDriver driver) throws Exception {
		return key.isElementPresent(driver, loginButton);
		
	}
	
	public boolean isErrorMessagePresent(WebDriver driver) throws Exception {
		return key.isElementPresent(driver, loginErrorMessage);
	}
	
	//We get My Account Option only after successful log in
	public boolean verifyLogin(WebDriver driver) throws Exception {
		return key.isElementPresent(driver, myAccountOption);
	}
	
	public boolean verifyContinueButtonOption(WebDriver driver) throws Exception {
		return key.isElementPresent(driver, continueButton);
	}
	
	public boolean verifyChangeButtonOption(WebDriver driver) throws Exception {
		return key.isElementPresent(driver, loginChangeOption);
	}
	
	public void clickChangeButtonOption(WebDriver driver) throws Exception {
		key.findElementClick(driver, loginChangeOption);
	}
}
