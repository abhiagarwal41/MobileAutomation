package com.steps;

import static com.pages.AbstractPage.getTestPage;
import static com.runner.SwitchAppTest.driver;
import static com.runner.SwitchAppTest.log;
import static com.runner.SwitchAppTest.test;
import static com.utils.Functions.doLogging;
import static org.junit.Assert.fail;
import static com.utils.Functions.getDiffDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.utils.Constants;
import com.utils.Functions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidKeyCode;

public class InputSteps {

	@When("I Provide \"(.*?)\" text input as \"(.*?)\"$")
	public void provideTextInput(String elementName, String value) {
		doLogging("Entering value :" + value + " in " + elementName, "INFO", log, test);
		try {
			WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
//			element.clear();
			element.sendKeys(value);
		} catch (NoSuchElementException ne) {
			doLogging("Unable to find element on page : " + elementName, "FAIL", log, test);
			fail("Unable to find element on page : " + elementName);
		}

	}

	@When("I clear contents of \"(.*?)\"$")
	public void clearContents(String elementName) {
		doLogging("Clearing contents of:" + elementName, "INFO", log, test);
		try {
			WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
			element.clear();
		} catch (NoSuchElementException ne) {
			doLogging("Unable to find element on page : " + elementName, "FAIL", log, test);
			fail("Unable to find element on page : " + elementName);
		}

	}

	@When("I uncheck \"(.*?)\"$")
	public void uncheck(String elementName) {
		doLogging("Unchecking:" + elementName, "INFO", log, test);
		try {
			WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
			String classes = element.getAttribute("class");
			if (classes.contains("mat-checkbox-checked"))
				element.click();
		} catch (NoSuchElementException ne) {
			doLogging("Unable to find element on page : " + elementName, "FAIL", log, test);
			fail("Unable to find element on page : " + elementName);
		}

	}

	@When("I check \"(.*?)\"$")
	public void check(String elementName) {
		doLogging("Unchecking:" + elementName, "INFO", log, test);
		try {
			WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
			String classes = element.getAttribute("class");
			if (!classes.contains("mat-checkbox-checked"))
				element.click();
		} catch (NoSuchElementException ne) {
			doLogging("Unable to find element on page : " + elementName, "FAIL", log, test);
			fail("Unable to find element on page : " + elementName);
		}

	}

	@When("I Select option \"(.*?)\" from mat dropdown \"(.*?)\"$")
	public void selectFrom(String value, String elementName) {
		doLogging("Selecting option :" + value + " from " + elementName, "INFO", log, test);
		try {
			WebElement dropdown = driver.findElement(getTestPage().getElementLocator(elementName));
			dropdown.click();
			List<WebElement> options = driver.findElements(By.cssSelector("span.mat-option-text"));
			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase(value)) {
					option.click();
					break;
				}
			}
		} catch (NoSuchElementException ne) {
			doLogging("Unable to find element on page : " + elementName, "FAIL", log, test);
			fail("Unable to find element on page : " + elementName);
		}

	}

	@Then("^I Provide \"([^\"]*)\" random text input as alphanumeric characters$")
	public void provideRandomTextInput(String elementName) {
		String randStr = Functions.getRandAlphaNumeric(10);
		doLogging("Entering value :" +randStr + " in " + elementName, "INFO", log, test);
		try {
			WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
			element.clear();
			element.sendKeys(randStr);
		} catch (NoSuchElementException ne) {
			doLogging("Unable to find element on page : " + elementName, "FAIL", log, test);
			fail("Unable to find element on page : " + elementName);
		}

	}
	
	@When("^I select date \"([^\"]*)\" days from today$")
	public void iSelectDateDaysFromToday(int dateDiff) throws Throwable {
		WebElement calendar = driver.findElement(By.cssSelector(".mat-calendar"));
		String date = getDiffDate(dateDiff,"d MMMM yyyy");
		doLogging("Selecting date :" + date, "INFO", log, test);
		String xpath = "//table[@class='mat-calendar-table']/tbody//td[@aria-label='" + date + "']";
		try {
			WebElement dateToSelect = calendar.findElement(By.xpath(xpath));
			dateToSelect.click();
		} catch (NoSuchElementException ne) {
			if (dateDiff > 0) {
				while (true) {
					WebElement nextMonth = calendar.findElement(By.cssSelector("button.mat-calendar-next-button"));
					nextMonth.click();
					try {
						WebElement dateToSelect = calendar.findElement(By.xpath(xpath));
						dateToSelect.click();
						break;
					} catch (NoSuchElementException e) {
						log.info("Searching date in next month");
					}
				}
			} else {
				while (true) {
					WebElement previousMonth = calendar
							.findElement(By.cssSelector("button.mat-calendar-previous-button"));
					previousMonth.click();
					try {
						WebElement dateToSelect = calendar.findElement(By.xpath(xpath));
						dateToSelect.click();
						break;
					} catch (NoSuchElementException e) {
						log.info("Searching date in previous month");
					}
				}
			}
		}
	}

	@When("I press back button$")
	public void pressBackButton() {
		doLogging("pressing back button", "INFO", log, test);
		try {
			driver.pressKeyCode(AndroidKeyCode.BACK);
		} catch (Exception ne) {
			doLogging("Unable to press back button", "FAIL", log, test);
			fail("Unable to press back button");
		}

	}
}
