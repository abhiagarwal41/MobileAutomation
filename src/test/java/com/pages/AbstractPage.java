package com.pages;


import org.openqa.selenium.By;

import com.pages.switchTestApp.SwitchTestAppLoginPage;


public abstract class AbstractPage {

	private static AbstractPage testPage;
	
	public static AbstractPage getTestPage(){
		return testPage;
	}
	
	public abstract By getElementLocator(String name);
	
	public static void setTestPage(String pageName) throws Exception{
	  
		switch(pageName.toLowerCase()){
		
		case "switchtestapploginpage" :  
			System.out.println("abstract switch case switchtestapploginpage");
			testPage = new SwitchTestAppLoginPage();
		break;
		
		default:
			throw new Exception("No such page exists: " +pageName);
		}
	}

}
