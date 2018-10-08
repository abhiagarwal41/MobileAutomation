package com.steps;

import static com.pages.AbstractPage.getTestPage;
import static com.runner.SwitchAppTest.driver;
import static com.runner.SwitchAppTest.log;
import static com.runner.SwitchAppTest.softAssertions;
import static com.runner.SwitchAppTest.test;
import static com.utils.Functions.doLogging;
import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ValidationSteps {
	
	@Then("^Verify that \"(.*?)\" should be visible$")
	public void shouldBeVisible(String elementName) throws Throwable {
		WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
		softAssertions.assertThat(element.isDisplayed()).isEqualTo(true);
		if(element.isDisplayed()==true)
			doLogging("Following element is visible :" + elementName, "PASS", log, test);
		else
			doLogging("Following element is invisible :" + elementName, "FAIL", log, test);
		softAssertions.assertAll();
	}
	
	@Then("^Verify that \"(.*?)\" should be enabled$")
	public void shouldBeEnabled(String elementName) throws Throwable {
		WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
		softAssertions.assertThat(element.isEnabled()).isEqualTo(true);
		if(element.isEnabled()==true)
			doLogging("Following element is enabled :" + elementName, "PASS", log, test);
		else
			doLogging("Following element is disabled :" + elementName, "FAIL", log, test);
		softAssertions.assertAll();
	}
	
	@Then("^Verify that \"(.*?)\" should be invisible$")
	public void shouldBeInVisible(String elementName) throws Throwable {
		WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
		softAssertions.assertThat(element.isDisplayed()).isEqualTo(false);
		if(element.isDisplayed()==false)
			doLogging("Following element is invisible :" + elementName, "PASS", log, test);
		else
			doLogging("Following element is visible :" + elementName, "FAIL", log, test);
		softAssertions.assertAll();
	}
	
	@Then("^Verify that \"(.*?)\" should not be present$")
	public void shouldNotBePresent(String elementName) throws Throwable {
		int size = driver.findElements(getTestPage().getElementLocator(elementName)).size();
		softAssertions.assertThat(size).isEqualTo(0);
		log.info("passcode label present size ="+ size);
		if(size==0)
			doLogging("Following element is not present :" + elementName, "PASS", log, test);
		else
			doLogging("Following element is prsent :" + elementName, "FAIL", log, test);
		softAssertions.assertAll();
	}
	
	@Then("^Verify that alert is visible with text \"([^\"]*)\"$")
	public void verifyThatAlertIsVisibleWithText(String expectedText) throws Throwable {
		 try 
		    { 
		       Alert alert= driver.switchTo().alert(); 
		       String actualText = alert.getText();
		       softAssertions.assertThat(actualText.toLowerCase()).isEqualTo(expectedText.toLowerCase());
		       if(actualText.toLowerCase().equals(expectedText.toLowerCase()))
		    	   doLogging("Alert visible with text:" + actualText, "PASS", log, test);  
		       else
		    	   doLogging("Alert visible with text:" + actualText, "FAIL", log, test);  
		    } 
		    catch (NoAlertPresentException Ex) 
		    { 
		    	doLogging("Alert not visible", "FAIL", log, test);
		    	fail("Alert not visible");	    	
		    }
		 softAssertions.assertAll();
	}
	
	@Then("^I Verify that list \"([^\"]*)\" should have \"([^\"]*)\" rows$")
	public void verifyThatListShouldHaveRows(String elementName, int expectedRowCount) throws Throwable {
		WebElement list = driver.findElement(getTestPage().getElementLocator(elementName));
		List<WebElement> listItems = list.findElements(By.cssSelector("div.icici-list-item"));
		 softAssertions.assertThat(listItems.size()).isEqualTo(expectedRowCount);
	       if(listItems.size() == expectedRowCount)
	    	   doLogging("Actual rows in list:" + listItems.size() + " Expected rows:" + 
	    			   expectedRowCount,"PASS", log, test);  
	       else
	    	   doLogging("Actual rows in list:" + listItems.size() + " Expected rows:" + 
	    			   expectedRowCount,"FAIL", log, test);  
	       softAssertions.assertAll();
	}
	
	@Then("I Verify that \"(.*?)\" contains text \"(.*?)\"$")
	public void verifyItemHasText(String elementName,String text) {
		WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
		 softAssertions.assertThat(element.getText().contains(text));
		 if (element.getText().contains(text)) doLogging(elementName+" has text "+text,"PASS",log,test);
		 else doLogging(elementName+" does not have text "+text,"FAIL",log,test);
		 softAssertions.assertAll();
	}
	
}
