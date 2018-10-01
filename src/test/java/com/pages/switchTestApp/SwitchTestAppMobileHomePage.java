package com.pages.switchTestApp;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.pages.AbstractPage;

public class SwitchTestAppMobileHomePage extends AbstractPage{

	@Override
	public By getElementLocator(String name) {
		// TODO Auto-generated method stub
		switch (name.toUpperCase()) {
		case "ADDACCOUNT": 
			return By.id("com.rohitupreti.testapplication:id/addAccount");
		case "GETACCOUNTS":
			return By.id("com.rohitupreti.testapplication:id/getAccounts");
		case "MESSAGE":
			return By.id("android:id/message");
		case "LISTACCOUNTPROVIDER":
			return By.id("com.rohitupreti.testapplication:id/listAccountProviderButton");
		case "LISTELEMENT":
			return By.id("com.rohitupreti.testapplication:id/listContent");
		case "SELECTEDACCOUNT":
			return By.id("com.rohitupreti.testapplication:id/accountSelected");
		case "CARDDIGITS":
			return By.id("com.rohitupreti.testapplication:id/cardDigitsET");
		case "EXPIRYDATE":
			return By.id("com.rohitupreti.testapplication:id/cardExpiryET");
		case "REQUESTOTP":
			return By.id("com.rohitupreti.testapplication:id/cardDetailsButton");
		case "REGISTERMOBILE":
			return By.id("com.rohitupreti.testapplication:id/regMobile");
		case "SEND":
			return By.id("android:id/button1");
		case "UPIPIN":
			return By.id("com.rohitupreti.testapplication:id/form_item_input");
		default:
			throw new NoSuchElementException(name);
		}
	}

}
