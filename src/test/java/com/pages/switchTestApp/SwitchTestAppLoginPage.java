package com.pages.switchTestApp;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.pages.AbstractPage;

public class SwitchTestAppLoginPage extends AbstractPage {

	@Override
	public By getElementLocator(String name) {
		// TODO Auto-generated method stub
		
			
			switch (name.toUpperCase()) {
			case "PASSCODE": 
				return By.id("id/passcodeAppET");
			case "PASSCODELABEL":
				System.out.println("switchcase pass code label");
				return By.xpath("//*[contains(text(),'Enter Passcode')]");
			default:
				throw new NoSuchElementException(name);
			}
	}
	
	
	

}
