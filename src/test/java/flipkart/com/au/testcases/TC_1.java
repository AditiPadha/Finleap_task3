package flipkart.com.au.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import flipkart.com.au.pageobjects.LoginPage;
import flipkart.com.au.utils.Constants;
import flipkart.com.au.utils.OpenBrowserInstance;
import flipkart.com.au.utils.PageLoading;

public class TC_1 extends OpenBrowserInstance {
	protected PageLoading loading = new PageLoading();
	LoginPage loginPage = new LoginPage();

	String counterName;

	String username, password;

	public void setup() {
		driver = getBrowserInstance();
	}

	@Test
	public void StartTest() {


		try {
			setup();
			executeTest();
			tearDown();

		} catch (Exception e) {
			tearDown();
			System.out.println( " Failed->" + e);
		}
	}

	public void executeTest() {

		try {
			loginPage.openURL(driver, Constants.URL);
			loading.waitUntilPageLoad(driver);

			/*Handling Possible Scenario 1:Below Scenario refers to the condition when login 
			window might not show up on its own*/
			if (!loginPage.loginButtonIsPresent(driver) && 
					!loginPage.verifyContinueButtonOption(driver)) {
				
				loginPage.clickLoginAndSignUpButton(driver);
			}
			
			/*Handling Possible Scenario 2:
			Below Scenario refers to the condition when login window might show up with option to provide 
			user name only, then click continue, then select  password input option and after that 
			the conventional login Window will show up but User name can then be changed only after 
			clicking 'Change' and repeating the same steps*/
			
			if ( loginPage.verifyContinueButtonOption(driver)) {
				loginPage.enterUserName(driver, Constants.invalidUsername);
				loginPage.clickContinueButton(driver);
				loginPage.clickLoginWithPasswordButton(driver);
				
				loginPage.enterPassword(driver, Constants.invalidPassword);

				loginPage.clickLoginButton(driver);

				
				if (loginPage.isErrorMessagePresent(driver)) {
					System.out.println("Error Message Found For Invalid Credentials");
				} else {
					tearDown();
					Assert.fail("No Error Message Was Found , login using :Scenario 2");
				}

				loginPage.clickChangeButtonOption(driver);
				loginPage.enterUserName(driver, Constants.username);
				loginPage.clickContinueButton(driver);
				loginPage.clickLoginWithPasswordButton(driver);
				loginPage.enterPassword(driver, Constants.password);
				
			}
			
			/*Handling Possible Scenario 3(Primary and Conventional Scenario):*/
			else {
				
				//Verifying Error Message with Invalid credentials
				loginPage.enterUserName(driver, Constants.invalidUsername);
				loginPage.enterPassword(driver, Constants.invalidPassword);

				loginPage.clickLoginButton(driver);
				
				if (loginPage.isErrorMessagePresent(driver)) {
					System.out.println("Error Message Found For Invalid Credentials");
				} else {
					tearDown();
					Assert.fail("No Error Message Was Found, login using :Scenario 3");
				}
				
				loginPage.clearUserName(driver);
				loginPage.enterUserName(driver, Constants.username);
				loginPage.enterPassword(driver, Constants.password);
			}


			loginPage.clickLoginButton(driver);

			if(loginPage.verifyLogin(driver)) {
				
				System.out.println("Login Successful ");
			}
			else {
				tearDown();
				Assert.fail("Login Failed");
			}
			
		} catch (Exception e) {
			Assert.fail("Login Failed due to following error:"+e.getMessage());
		}

	}

	public void tearDown() {
		if (driver != null) {
			driver.close();
			driver.quit();
			driver = null;
		}
	}

}