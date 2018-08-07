package com.steps;

import static com.pages.AbstractPage.getTestPage;
import static com.runner.SwitchAppTest.driver;
import static com.runner.SwitchAppTest.log;
import static com.runner.SwitchAppTest.test;
import static com.utils.Functions.doLogging;
import static org.junit.Assert.fail;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.When;

public class ClickSteps {
	
	@When("^I click on \"(.*?)\"$")
	public void iClickOn(String elementName) {
		doLogging("Clicking on element : " + elementName, "INFO", log, test);
		try {
		WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
		element.click();
		}
		catch(NoSuchElementException ne){
			doLogging("Unable to find element on page : " + elementName, "FAIL", log, test);
			fail("Unable to find element on page : " + elementName);
		}
	}

	@When("^I accept alert")
	public void closeAlert(){
		doLogging("Accepting alert", "INFO", log, test);
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
		catch(NoSuchElementException ne){
			doLogging("Unable to find alert on page : " , "FAIL", log, test);
			fail("Unable to find alert on page : ");
		}
	}
}
