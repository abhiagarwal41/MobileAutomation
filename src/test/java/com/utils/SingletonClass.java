package com.utils;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.runner.SwitchAppTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class SingletonClass {

	private static final SingletonClass INSTANCE = new SingletonClass();
	private boolean isRunning = false;
	private AndroidDriver driver;
	private WebDriverWait webDriverWait;

	public AndroidDriver getDriver() {
		return driver;
	}

	private SingletonClass() {
		// TODO Auto-generated constructor stub
	}

	
	public void startApplication() {		
	
		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setCapability("deviceName", "emulator-5554");
		caps.setCapability("deviceName", "FRTBD80322861561");
		
//		caps.setCapability("udid", "ENUL6303030010"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "8.1.0");
	    caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
		caps.setCapability("appPackage", "com.rohitupreti.testapplication");
		caps.setCapability("appActivity", ".Activities.UserLogin.LoginActivity");
		caps.setCapability("appWaitActivity", ".Activities.UserLogin.LoginActivity");
		caps.setCapability("noReset", "true");
		
		//Instantiate Appium Driver
		try {
				 driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
				 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		} catch (MalformedURLException e) {
			SwitchAppTest.log.error(e.getMessage());
		}
		
//		driver = new ChromeDriver();
	//	driver.manage().window().maximize();
		webDriverWait = new WebDriverWait(driver, 60);

	}

	public static synchronized SingletonClass getInstance() {
		if (!INSTANCE.isRunning) {
			INSTANCE.startApplication();
			INSTANCE.isRunning = true;
		}

		return INSTANCE;
	}

	public void stopApplication() {

		driver.close();
		driver.quit();
		INSTANCE.isRunning = false;
	}

	public WebDriverWait getWebDriverWait() {
		if (driver == null) {
			throw new RuntimeException(
					"'webDriverWait' has not been initialized yet. Please initialize webDriverWait by calling 'open' method");
		}
		return webDriverWait;
	}

	/*
	public static void waitForLoad(WebDriver driver) {
		
		 ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
		      @Override
		      public Boolean apply(WebDriver driver) {
		        try {
		          return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
		        }
		        catch (Exception e) {
		          // no jQuery present
		          return true;
		        }
		      }
		    };

		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		
		
		getInstance().getWebDriverWait().until(pageLoadCondition);
		getInstance().getWebDriverWait().until(jQueryLoad);

	}
	*/
}
