package com.pages.switchTestApp;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.pages.AbstractPage;

public class SwitchTestAppMobileRegisterPage extends AbstractPage{

	@Override
	public By getElementLocator(String name) {
		// TODO Auto-generated method stub
		switch (name.toUpperCase()) {
		case "MOBILENUMBER": 
			return By.className("android.widget.EditText");
		case "REGISTERMESSAGE":
			return By.id("com.rohitupreti.testapplication:id/listContent");
		case "VERIFY MOBILE":
			return By.className("android.widget.Button");
		case "SEND":
			return By.id("android:id/button1");
		default:
			throw new NoSuchElementException(name);
		}
	}

}
