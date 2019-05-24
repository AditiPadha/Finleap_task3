package flipkart.com.au.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class OpenBrowserInstance {
	public WebDriver driver;
	String browserName = Constants.browser;

	public WebDriver getBrowserInstance() {

		if (browserName == "chrome") {
			return initChrome();
		}

		return driver;
	}

	// Open Chrome Browser
	public WebDriver initChrome() {

		System.setProperty("webdriver.chrome.driver", "Lib/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		options.addArguments("incognito");
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.addArguments("--js-flags=--expose-gc");
		options.addArguments("--enable-precise-memory-info");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");
		options.addArguments("test-type=browser");
		options.addArguments("disable-infobars");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		return driver;
	}

	//@AfterClass
	public void closeDriver() {
		
		if (driver != null) {
			driver.close();
			driver.quit();
			driver = null;
		}
		
	}

}
