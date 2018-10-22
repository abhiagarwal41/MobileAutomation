package com.steps;

import static com.pages.AbstractPage.getTestPage;
import static com.runner.SwitchAppTest.driver;
import static com.runner.SwitchAppTest.log;
import static com.runner.SwitchAppTest.test;
import static com.utils.Functions.doLogging;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import cucumber.api.java.en.When;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ClickSteps {

	@When("^I click on \"(.*?)\"$")
	public void iClickOn(String elementName) {
		doLogging("Clicking on element : " + elementName, "INFO", log, test);
		try {
			WebElement element = driver.findElement(getTestPage().getElementLocator(elementName));
			element.click();
		} catch (NoSuchElementException ne) {
			doLogging("Unable to find element on page : " + elementName, "FAIL", log, test);
			fail("Unable to find element on page : " + elementName);
		}
	}

	@When("^I accept alert")
	public void closeAlert() {
		doLogging("Accepting alert", "INFO", log, test);
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoSuchElementException ne) {
			doLogging("Unable to find alert on page : ", "FAIL", log, test);
			fail("Unable to find alert on page : ");
		}
	}

	@When("^I click on list element which has text \"(.*?)\"$")
	public void iClickOnListElementContainsText(String text) {
		doLogging("Clicking on list element which has text: " + text, "INFO", log, test);
		try {
			boolean elementFound = false;
			List<WebElement> list = driver.findElements(getTestPage().getElementLocator("LISTELEMENT"));
			doLogging("List size : " + list.size(), "INFO", log, test);
			while (!elementFound) {
				for (WebElement element : list) {
//					WebElement name =element.findElement(By.id("com.rohitupreti.testapplication:id/tv1"));
					if (element.getText().contains(text)) {
						element.click();
						elementFound = true;
						System.out.println("Executed...");
						break;
					}
				}
				swipeUp();
			}
		} catch (NoSuchElementException ne) {
			doLogging("Unable to find element on page : " + text, "FAIL", log, test);
			fail("Unable to find element on page : " + text);
		}
	}

	@When("^I click on element at (\\d+) index of \"(.*?)\" list$")
	public void iClickOnNthListElement(int n, String elementName) {
		doLogging("Clicking on " + n + "list element", "INFO", log, test);
		try {
			List<WebElement> list = driver.findElements(getTestPage().getElementLocator(elementName));
			doLogging("List size : " + list.size(), "INFO", log, test);
			if (list.size() > n) {
				list.get(n).click();
			} else {
				doLogging("List size is less than " + n, "FAIL", log, test);
			}
		} catch (NoSuchElementException ne) {
			doLogging("Unable to find element on page : " + elementName, "FAIL", log, test);
			fail("Unable to find element on page : " + elementName);
		}
	}

	private void swipeUp() {
		// TODO Auto-generated method stub
		Dimension size = driver.manage().window().getSize();
		int starty = (int) (size.height * 0.80);
		int endy = (int) (size.height * 0.20);
		int startx = size.width / 2;
		System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);
		TouchAction ta = new TouchAction(driver);
//		ta.press(startx, starty).waitAction(Duration.ofSeconds(3)).moveTo(startx, endy).release().perform();
		ta.press(new PointOption().withCoordinates(startx, starty)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(3))).moveTo(new PointOption().withCoordinates(startx, endy)).release().perform();
		try {
			TimeUnit.MILLISECONDS.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
