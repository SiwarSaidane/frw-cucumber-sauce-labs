package com.e2etests.automation.utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.cucumber.java.Before;


public class Setup {
	/**
	   * This method is used to open browser. This method is called before the
	   * invocation of each test method in the given class. In this method we need to
	   * pass browser name which will invoke the respective driver.
	   *
	   * @throws MalformedURLException the malformed URL exception
	   * @Before Methods annotated with @Before will execute before every scenario.
	   */
	
	public static WebDriver driver;
	
@Before		
	public void setWebDriver() {
		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = "Chrome";
		}
		switch (browser) {
		
		case "Chrome" : 
			ChromeOptions chromeOptions = new ChromeOptions();
			driver = new ChromeDriver(chromeOptions);
			
			//update Selenium manager v4.11.0 to ChromeBrowser instead of WebDriverManager (BoniGarcia)
			DesiredCapabilities cp = new DesiredCapabilities();
			cp.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			chromeOptions.merge(cp);
			
			//Or
			//driver = new ChromeDriver();
			
			driver.manage().window().maximize();
	
			break;
			
		case "firefox" :
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability("platform", Platform.WIN10);
			firefoxOptions.setProfile(profile);
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			
			break;
			
		default :
			throw new IllegalArgumentException("Browser \""+browser+"\"is not supported");
			
		}
	}

}
