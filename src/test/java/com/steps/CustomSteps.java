package com.steps;

import static com.pages.AbstractPage.getTestPage;
import static com.runner.SwitchAppTest.driver;
import static com.runner.SwitchAppTest.log;
import static com.runner.SwitchAppTest.softAssertions;
import static com.runner.SwitchAppTest.test;
import static com.utils.Functions.doLogging;
import static com.utils.Functions.getDiffDate;
import static com.utils.Functions.getProperty;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CustomSteps {

	@When("^I login to lms app$")
	public void iLoginToLmsApp() throws IOException {
		String uname = getProperty("lms_username");
		String pwd = getProperty("lms_password");
		doLogging("Logging to lms..", "INFO", log, test);
		try {
			WebElement username = driver.findElement(getTestPage().getElementLocator("username"));
			WebElement password = driver.findElement(getTestPage().getElementLocator("password"));
			WebElement login_button = driver.findElement(getTestPage().getElementLocator("login_button"));
			username.sendKeys(uname);
			password.sendKeys(pwd);
			login_button.click();
		} catch (Exception e) {
			doLogging("Unable to login: " + e.getMessage(), "FAIL", log, test);
			fail("Unable to login : " + e.getMessage());
		}
	}

	@Then("^Verify data for div \"([^\"]*)\"$")
	public void verifyDataFor(String elementName, DataTable table) throws Throwable {

		doLogging("Matching values for div: " + elementName, "INFO", log, test);

		WebElement div = driver.findElement(getTestPage().getElementLocator(elementName));
		List<Map<String, String>> data = table.asMaps(String.class, String.class);
		List<WebElement> allChildDivs = div.findElements(By.cssSelector("div.icici-label-text-pair"));
		Map<String, String> dataToVerify = data.get(0);

		for (String key : dataToVerify.keySet()) {
			WebElement divToCheck = getRelevantDiv(key, allChildDivs);
			if (divToCheck != null) {
				WebElement span = divToCheck.findElement(By.tagName("span"));
				String actualValue = span.getText();
				String expectedValue = dataToVerify.get(key);
				softAssertions.assertThat(actualValue.trim()).isEqualToIgnoringCase(expectedValue.trim());
				if (actualValue.trim().equalsIgnoreCase(expectedValue.trim()))
					doLogging(key + " Actual Value: " + actualValue + " Expected Value: " + expectedValue, "PASS", log,
							test);
				else
					doLogging(key + " Actual Value: " + actualValue + " Expected Value: " + expectedValue, "FAIL", log,
							test);
			} else {
				doLogging("No label found: " + key, "FAIL", log, test);
				softAssertions.fail("No label found: " + key);
			}
		}

		 softAssertions.assertAll();

	}

	@Then("^Verify data for table \"([^\"]*)\"$")
	public void verifyDataForTable(String elementName, DataTable table) throws Throwable {

		doLogging("Matching values for table: " + elementName, "INFO", log, test);

		WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
		List<List<String>> dataToVerify = table.raw();
		List<WebElement> listItems = element.findElements(By.cssSelector(".mat-list-item"));

		for (int j = 0; j < dataToVerify.size(); j++) {
			
			WebElement actualRow = listItems.get(j);
			List<WebElement> spans = actualRow.findElements(By.tagName("span"));
			List<String> actualData = new ArrayList<String>();
			for (WebElement span : spans)
				actualData.add(span.getText());
			List<String> expectedData = dataToVerify.get(j);
			
			for (int i = 0; i < expectedData.size(); i++) {

				String actualValue = actualData.get(i);
				String expectedValue = expectedData.get(i);
				if(expectedValue.contains("current_date")) {
					expectedValue=expectedValue.replace("current_date", "").replace("+", "").replaceAll(" ", "");
					if(expectedValue.isEmpty())
						expectedValue=getDiffDate(0, "MMM d, yyyy");
					else
						expectedValue=getDiffDate(Integer.parseInt(expectedValue), "MMM d, yyyy");
				}
				softAssertions.assertThat(actualValue.trim()).isEqualToIgnoringCase(expectedValue.trim());
				if (actualValue.trim().equalsIgnoreCase(expectedValue.trim()))
					doLogging(" Actual Value: " + actualValue + " Expected Value: " + expectedValue, "PASS", log, test);
				else
					doLogging(" Actual Value: " + actualValue + " Expected Value: " + expectedValue, "FAIL", log, test);
			}

		}

		 softAssertions.assertAll();

	}

	private static WebElement getRelevantDiv(String key, List<WebElement> allChildDivs) {
		for (WebElement div : allChildDivs) {
			String label = div.findElement(By.tagName("label")).getText();
			if (label.toLowerCase().contains(key.toLowerCase()))
				return div;
		}
		return null;
	}
	
	@Then("^Verify that table \"([^\"]*)\" has \"([^\"]*)\" rows$")
	public void verifyDataForTable(String elementName, int expectedRowCount) throws Throwable {
		
		WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
		List<WebElement> listItems = element.findElements(By.cssSelector(".mat-list-item"));
		softAssertions.assertThat(listItems.size()).isEqualTo(expectedRowCount);
		if(listItems.size()==expectedRowCount)
			doLogging(" Actual rows: " + listItems.size() + " Expected rows: " + expectedRowCount, "PASS", log, test);
		else
			doLogging(" Actual rows: " + listItems.size() + " Expected rows: " + expectedRowCount, "FAIL", log, test);
	}

}
