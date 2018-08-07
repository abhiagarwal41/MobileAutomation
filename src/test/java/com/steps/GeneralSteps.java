package com.steps;

import static com.runner.SwitchAppTest.driver;
import static com.runner.SwitchAppTest.extent;
import static com.runner.SwitchAppTest.log;
import static com.runner.SwitchAppTest.softAssertions;
import static com.runner.SwitchAppTest.test;
import static com.utils.Functions.doLogging;
//import static com.utils.Functions.getScreenshot;
import static com.utils.SingletonClass.getInstance;
import static org.junit.Assert.fail;

import org.assertj.core.api.SoftAssertions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class GeneralSteps {
	
	
	@Given("^I start application$")
	public void iStartApp() {
		try {
//		String url = getProperty(application);
		driver = getInstance().getDriver();
//		driver.get(url);
		doLogging("Opened app on device ", "INFO", log, test);
		}
		catch(Exception e) {
			doLogging("Unable to initialize driver" + e.getMessage(), "FAIL", log, test);
			log.debug("exception caught "+e.getMessage());
			fail("Unable to initialize driver" + e.getMessage());
		}
	}
	
	@Given("^I refresh page$")
	public void iRefreshPAge() {
		try {
		doLogging("Refreshing page..", "INFO", log, test);
		driver = getInstance().getDriver();
		driver.navigate().refresh();
		}
		catch(Exception e) {
			doLogging("Unable to refresh page" + e.getMessage(), "FAIL", log, test);
			fail("Unable to refresh page" + e.getMessage());
		}
	}
	
	@Before
	public void before(Scenario scenario) {
		log.info("Starting test scenario : " + scenario.getName());
		test = extent.createTest(scenario.getName());  
		softAssertions = new SoftAssertions();
	}
	
	@After
	public void after(Scenario scenario) throws Exception {
//		if(scenario.isFailed()) {
//			String screenshotPath = getScreenshot(driver, scenario.getName());
//			test.addScreenCaptureFromPath(screenshotPath);
//		}
		extent.flush();
	}
	

}
