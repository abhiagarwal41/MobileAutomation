package com.runner;

import static com.utils.Constants.application;
import static com.utils.Constants.logPropertiesPath;
import static com.utils.SingletonClass.getInstance;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.assertj.core.api.SoftAssertions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;



@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features = "src/test/resources/features/switch", glue = "com.steps", snippets = SnippetType.CAMELCASE, tags = {
		"@test2" }, monochrome = true)
public class SwitchAppTest {

	public static SoftAssertions softAssertions;
//	public static WebDriver driver;
	public static AndroidDriver driver;
	public static Logger log = Logger.getLogger(SwitchAppTest.class);
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeClass
	public static void beforeClass() throws Exception {

		PropertyConfigurator.configure(logPropertiesPath);
		intializeExtentReporting();
		log.info("Initializing logs for Upi Switch test Application UI tests...");
		application = "switchTestApp";
		File screenshotDir = new File("./FailedTestsScreenshots/switch");
		if (!screenshotDir.exists()) {
			screenshotDir.mkdirs();
		}
		FileUtils.cleanDirectory(screenshotDir);	
		
	}

	@AfterClass
	public static void afterClass() {
		getInstance().stopApplication();
	}

	public static void intializeExtentReporting() {
		File reportsDir= new File("./reports/");
		if(!reportsDir.exists()) {
			reportsDir.mkdirs();
		}
		File reportFile = new File(reportsDir.getAbsolutePath()+"upi_switch_app_report.html");
		htmlReporter = new ExtentHtmlReporter(reportFile);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));

		htmlReporter.config().setDocumentTitle("UPI Switch App test report");
		htmlReporter.config().setReportName("UPI Switch App test report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	
}
