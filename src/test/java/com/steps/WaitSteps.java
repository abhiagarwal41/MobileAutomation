package com.steps;

import static com.pages.AbstractPage.getTestPage;
import static com.pages.AbstractPage.setTestPage;
import static com.runner.SwitchAppTest.log;
import static com.runner.SwitchAppTest.test;
import static com.utils.Functions.doLogging;
import static com.utils.SingletonClass.getInstance;
//import static com.utils.SingletonClass.waitForLoad;
import static java.lang.Thread.sleep;
import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import cucumber.api.java.en.When;

public class WaitSteps{
	
	@When("I Wait for \"(.*?)\" to load$")
	public void waitForPageLoad(String pageName) {
		doLogging("Waiting for page to load : " + pageName, "INFO", log, test);
/*		try {
		waitForLoad(driver);
		}
		catch(TimeoutException e) {
			doLogging("Timeout exception waiting for page to load: " + pageName, "FAIL", log, test);
			fail("Timeout exception waiting for page to load: " + pageName);
		}
*/
		try {
			setTestPage(pageName);
		} catch (Exception e) {
			doLogging(e.getMessage(), "FAIL", log, test);
			fail(e.getMessage());
		}
	}
	
	@When("I Wait for \"(.*?)\" seconds$")
	public void waitForSeconds(long seconds) throws InterruptedException {
		doLogging("Waiting for seconds : " + seconds, "INFO", log, test);
		sleep(seconds*1000);
	}
	
	@When("I Wait until \"(.*?)\" is present$")
	public void waitUntilPresent(String elementName){
		doLogging("Waiting for presence of : " + elementName, "INFO", log, test);
		By locator = getTestPage().getElementLocator(elementName);
		try {
		getInstance().getWebDriverWait().until(presenceOfElementLocated(locator));
		}
		catch(TimeoutException e) {
			doLogging("Timeout exception waiting for presence of : " + elementName, "FAIL", log, test);
			fail("Timeout exception waiting for presence of : " + elementName);
		}
	}
	
	@When("I Wait until \"(.*?)\" is visible$")
	public void waitUntilVisible(String elementName) {
		doLogging("Waiting for visibility of : " + elementName, "INFO", log, test);
		By locator = getTestPage().getElementLocator(elementName);
		try {
		getInstance().getWebDriverWait().until(visibilityOfElementLocated(locator));
		}
		catch(TimeoutException e) {
			doLogging("Timeout exception waiting for visibility of : " + elementName, "FAIL", log, test);
			fail("Timeout exception waiting for visibility of : " + elementName);
		}
	}
	
	@When("I Wait until \"(.*?)\" is clickable$")
	public void waitUntilClickable(String elementName) {
		doLogging("Waiting for clickability of : " + elementName, "INFO", log, test);
		By locator = getTestPage().getElementLocator(elementName);
		try {
		getInstance().getWebDriverWait().until(elementToBeClickable(locator));
		}
		catch(TimeoutException e) {
			doLogging("Timeout exception waiting for clickability of : " + elementName, "FAIL", log, test);
			fail("Timeout exception waiting for clickability of : " + elementName);
		}
	}
	
	@When("I Wait until \"(.*?)\" is invisible$")
	public void waitUntilInVisible(String elementName){
		doLogging("Waiting for invisibility of : " + elementName, "INFO", log, test);
		By locator = getTestPage().getElementLocator(elementName);
		try {
		getInstance().getWebDriverWait().until(invisibilityOfElementLocated(locator));
		}catch(TimeoutException e) {
			doLogging("Timeout exception waiting for invisibility of : " + elementName, "FAIL", log, test);
			fail("Timeout exception waiting for invisibility of : " + elementName);
		}
	}

}
