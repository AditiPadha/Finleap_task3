/*
 * This class will contains one function per keyword. Every function should 
 * have proper comments explaining what that function do.
 */
package flipkart.com.au.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keywords {

	public void getUrl(WebDriver driver, String URL) {// ====open url =====>
		driver.get(URL);
	}

	public String getAttributeValue(WebDriver driver, By locator, String attributeKey) {// ===Return
																						// attribute
																						// value==>
		return driver.findElement(locator).getAttribute(attributeKey);

	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		for (int i = 0; i < dirContents.length; i++) {
			System.out.println(downloadPath);
			System.out.println(dirContents[i].getName());
			if (dirContents[i].getName().equals(fileName)) {

				// File has been found, it can now be deleted:
				dirContents[i].delete();

				return true;
			}
		}
		return false;
	}

	public void findElementClick(WebDriver driver, By locator) {// ==Find
																// element and
																// click=====>
		waitElementUntilClickable(driver, locator);
		driver.findElement(locator).click();
	}
	
	public void findElementClick(WebDriver driver, By locator, int timeout) {// ==Find
																// element and
																// click=====>
		waitElementUntilClickable(driver, locator, timeout);
		driver.findElement(locator).click();
	}


	public void findElementWaitAndClick(WebDriver driver, By locator) {// ==Find
		// element and
		// click=====>
		waitElementUntilDisplayed(driver, locator);
		driver.findElement(locator).click();
	}

	public void findElementJustClick(WebDriver driver, By locator) {// ==Find
																	// element
																	// and
																	// click=====>
		driver.findElement(locator).click();
	}

	public void clickByjs(WebDriver driver, By locator) {
		WebElement ele = driver.findElement(locator);
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].click();", ele);
	}

	public void findElementClear(WebDriver driver, By locator) {// ==Find
																// element and
																// Clear====>
		waitElementUntilDisplayed(driver, locator);
		driver.findElement(locator).clear();
	}

	public void findElementSendKey(WebDriver driver, By locator, String SendkeyData) {
		// ==Find element and send keys====>
		waitElementUntilPresent(driver, locator);
		scrollIntoView(driver, locator);
		waitElementUntilDisplayed(driver, locator);
		driver.findElement(locator).sendKeys(SendkeyData);
	}

	public void waitElementUntilPresent(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public void findElementSendKey(WebDriver driver, By locator, Keys keys) {
		waitElementUntilDisplayed(driver, locator);
		driver.findElement(locator).sendKeys(keys);
	}

	public WebElement findEelement(WebDriver driver, By locator) {// ===Find
																	// element
																	// and
																	// return
																	// element=====>
		waitElementUntilDisplayed(driver, locator);
		return driver.findElement(locator);
	}

	public boolean isElementPresent(WebDriver driver, By locator) {// ===Find
																	// element
																	// and
																	// return
																	// True/False=====>
		try {
			waitElementUntilDisplayed(driver, locator);
			return true;
		} catch (Exception e) {
			// System.out.println(locator + " Not found");
			return false;
		}

	}

	public boolean isElementPresent(WebDriver driver, By locator, int timmer) {// ===Find
																				// element
																				// and
																				// return
																				// True/False=====>
		try {
			waitElementUntilDisplayed(driver, locator, timmer);
			return true;
		} catch (Exception e) {
			System.out.println("Information only [May not be reason of failure]-->" + locator + " Not found");
			// System.out.println(e);
			return false;
		}

	}

	public String getText(WebDriver driver, By locator) {// ==Return String as
															// data==>
		waitElementUntilDisplayed(driver, locator);
		return driver.findElement(locator).getText();
	}

	public int findElementsCount(WebDriver driver, By locator) {// ==Find
																// elements
																// counts and
																// returns
																// Integer
																// value==>
		List<WebElement> listOfElement = driver.findElements(locator);
		return listOfElement.size();
	}

	public void waitElementUntilDisplayed(WebDriver driver, By locator) {// ===Wait
																			// for
																			// element
																			// until
																			// displayed==>
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitElementUntilDisplayed(WebDriver driver, By locator, int timer) {// ===Wait
																					// for
																					// element
																					// until
																					// displayed==>
		WebDriverWait wait = new WebDriverWait(driver, timer);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitElementUntilClickable(WebDriver driver, By locator) {// ===Wait
																// displayed==>
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitElementUntilClickable(WebDriver driver, By locator, int timeout) {// ===Wait
															// displayed==>
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public void waitUntillInvisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitUntillInvisible(WebDriver driver, By locator, int timmer) {
		WebDriverWait wait = new WebDriverWait(driver, timmer);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitUntillElementCount(WebDriver driver, By locator, int number) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.numberOfElementsToBe(locator, number));
	}

	// ================Switch to Frames via String/Integer/default======>
	public void switchToFrame(WebDriver driver, String frame) {
		driver.switchTo().frame(frame);
	}

	public void switchToFrame(WebDriver driver, int frame) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	public void switchToDefault(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	// ================================================================>

	// ===================Scroll
	// functions=============================================>
	public void scrollDownScreen(WebDriver driver) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,500)", "");
	}

	public void scrollUpScreen(WebDriver driver) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,-500)", "");
	}

	public void scrollToElementLocation(WebDriver driver, By locator) {
		String location = driver.findElement(locator).getLocation().toString();
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollTo" + location + "", "");
	}

	public void scrollIntoView(WebDriver driver, By locator) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));

	}

	public void scrollToElementLocation(WebDriver driver, WebElement element) {
		String location = element.getLocation().toString();
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollTo" + location + "", "");
	}
	
	public void scrollIntoView(WebDriver driver, WebElement element) {
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", element);

	}
	
	public void falseFailure(WebDriver driver) {
		// this is only for failing condition
		driver.findElement(By.xpath(".//input[just an error]"));
	}

	public void falseFailure(WebDriver driver, String testStep) {
		// this is only for failing condition
		System.out.println("This is failed by false failure in testStep-->" + testStep);
		driver.findElement(By.xpath(".//input[just an error]"));
	}

	public String getAttributeByJse(WebDriver driver, By locator, String attributeName) {

		JavascriptExecutor je = (JavascriptExecutor) driver;
		String attribute = je
				.executeScript("return arguments[0].getAttribute('" + attributeName + "')", driver.findElement(locator))
				.toString();
		return attribute;
	}

	public String getAttributeByJse(WebDriver driver, WebElement element, String attributeName) {

		JavascriptExecutor je = (JavascriptExecutor) driver;
		String attribute = je.executeScript("return arguments[0].getAttribute('" + attributeName + "')", element)
				.toString();
		return attribute;

	}

	public void setAttributeByJse(WebDriver driver, By locator, String AttributeName, String AttributeValue) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].setAttribute('" + AttributeName + "','" + AttributeValue + "')",
				driver.findElement(locator));

	}

	public String getElementPropertyByJse(WebDriver driver, By locator, String propertyName) {

		JavascriptExecutor je = (JavascriptExecutor) driver;
		String attribute = je.executeScript("return arguments[0]." + propertyName, driver.findElement(locator))
				.toString();
		return attribute;

	}

	public String getElementPropertyByJse(WebDriver driver, WebElement element, String propertyName) {

		JavascriptExecutor je = (JavascriptExecutor) driver;
		String attribute = je.executeScript("return arguments[0]." + propertyName, element).toString();
		return attribute;

	}

	public void waitElementsUntilDisplayed(WebDriver driver, By locator, int count) {// ===Wait
																						// for
																						// elements
																						// until
																						// displayed==>
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.numberOfElementsToBe(locator, count));
	}

	public boolean isElementsPresent(WebDriver driver, By locator, int count) {// ===Find
																				// elements
																				// and
																				// return
																				// True/False=====>
		try {
			waitElementsUntilDisplayed(driver, locator, count);
			return true;
		} catch (Exception e) {
			// System.out.println(locator + " Not found");
			return false;
		}
	}

	public void uploadFileByRobotAfterClickBrowseButton(WebDriver driver, String filePath) throws AWTException {
		StringSelection ss = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		// imitate mouse events like ENTER, CTRL+C, CTRL+V
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public String getFutureDate(int i) {
		LocalDateTime ldt = LocalDateTime.now().plusDays(i);
		DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH);
		return formmat1.format(ldt);
	}

	public String getCurrentDate() {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH);
		return formmat1.format(ldt);
	}

	public void switchToNewWindow(WebDriver driver) {
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

	}

	public void waitAndSwitchToNewWindow(WebDriver driver, int numberOfWindowsToBe) {
		(new WebDriverWait(driver, Constants.TIMEOUT))
				.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindowsToBe));
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

	}

	public void selectBoxSelectOption(WebDriver driver, By onlineUpdateInputBox, String onlineUpdate) {
		waitElementUntilDisplayed(driver, onlineUpdateInputBox);
		new Select(findEelement(driver, onlineUpdateInputBox)).selectByVisibleText(onlineUpdate);
	}

	public void selectBoxSelectOptionByValue(WebDriver driver, By onlineUpdateInputBox, String onlineUpdate) {
		waitElementUntilDisplayed(driver, onlineUpdateInputBox);
		new Select(findEelement(driver, onlineUpdateInputBox)).selectByValue(onlineUpdate);
	}
	
	public void checkBoxOn(WebDriver driver, By locator) {// ==Find
																// element and
																// click if unchecked=====>
		waitElementUntilClickable(driver, locator);
		if(!driver.findElement(locator).isSelected()) {
			driver.findElement(locator).click();
		}
	}

	public void checkBoxOff(WebDriver driver, By locator) {// ==Find
																// element and
																// click if checked=====>
		waitElementUntilClickable(driver, locator);
		if(driver.findElement(locator).isSelected()) {
			driver.findElement(locator).click();
		}
	}

	
	public void closeTab(WebDriver driver) {
		driver.close();

	}

	public String generateRandomNumbers(int length) {
		String AB = "0123456789";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();

	}
	
	public WebElement getRowContainingCellText(WebDriver driver, String tableId, String cellText) {
		List<WebElement> elems = driver.findElements(By.xpath(
				String.format("//*[contains(@id,'%s')]/tbody/tr[descendant::*[contains(.,'%s')]]", tableId, cellText)));

		if (elems.size() > 0)
			return elems.get(0);

		return null;
	}
}
