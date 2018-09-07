package com.pages.switchTestApp;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.pages.AbstractPage;

public class SwitchTestAppCreateVpaPage extends AbstractPage {

	@Override
	public By getElementLocator(String name) {
		// TODO Auto-generated method stub
		switch (name.toUpperCase()) {
		case "VPANAME": 
			return By.id("com.rohitupreti.testapplication:id/vpaEditText");
		case "CHECKAVAILABILITYBUTTON":
			return By.id("com.rohitupreti.testapplication:id/vpaAvailabilityButton");
		default:
			throw new NoSuchElementException(name);
		}
	}

}
