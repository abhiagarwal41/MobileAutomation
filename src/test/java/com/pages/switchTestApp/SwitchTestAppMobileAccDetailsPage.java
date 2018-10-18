package com.pages.switchTestApp;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.pages.AbstractPage;

public class SwitchTestAppMobileAccDetailsPage extends AbstractPage{

	@Override
	public By getElementLocator(String name) {
		// TODO Auto-generated method stub
		switch (name.toUpperCase()) {
		case "CHECK BALANCE":
			return By.id("com.rohitupreti.testapplication:id/checkBalanceButton");
		case "RESET MPIN":
			return By.id("com.rohitupreti.testapplication:id/resetMpinButton");
		case "VPAET":
			return By.id("com.rohitupreti.testapplication:id/vpaToValidate");
		case "VALIDATE ADDRESS":
			return By.id("com.rohitupreti.testapplication:id/validateAddressButton");
		case "AMOUNT":
			return By.id("com.rohitupreti.testapplication:id/payAmount");
		case "PAY TO VPA":
			return By.id("com.rohitupreti.testapplication:id/payToVpa");
		case "COLLECT FROM VPA":
			return By.id("com.rohitupreti.testapplication:id/collectFromVpa");
		case "CHANGE MPIN":
			return By.id("com.rohitupreti.testapplication:id/changeMpinButton");
		case "TRANSACTION HISTORY":
			return By.id("com.rohitupreti.testapplication:id/transactionHistory");
		case "PENDING TRANSACTIONS":
			return By.id("com.rohitupreti.testapplication:id/pendingTransaction");
		default:
			throw new NoSuchElementException(name);
		}
	}

}
