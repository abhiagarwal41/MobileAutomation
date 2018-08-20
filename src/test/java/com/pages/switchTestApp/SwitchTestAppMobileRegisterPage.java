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
		case "VERIFY MOBILE":
			return By.className("android.widget.Button");
		default:
			throw new NoSuchElementException(name);
		}
	}

}
