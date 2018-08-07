package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Functions {
	
	public final static String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	public static String getProperty(String name) throws IOException {
		File src = new File("./src/test/resources/config.properties");
		FileInputStream fis = new FileInputStream(src);
		Properties pro = new Properties();
		pro.load(fis);
		return pro.getProperty(name);
	}

	public static void doLogging(String msg, String status, Logger log, ExtentTest test) {
		log.info(msg);
		test.log(Status.valueOf(status), msg);
	}
/*
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/lms/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
*/

	public static String getDiffDate(int diff,String format) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, diff);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(cal.getTime());
		return date;
	}
	
	public static String getFloatingDate(int diff) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, diff);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String date = sdf.format(cal.getTime());
		// System.out.println("!!!!!!!!!!!!"+date);
		return date;
	}
	
	public static String getRandAlphaNumeric(int RANDOM_STRING_LENGTH) {
     
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber(RANDOM_STRING_LENGTH);
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}
	
	private static int getRandomNumber(int limit) {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(limit);
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}
	
	public static String getRandomFloat() {
		Random randomGenerator = new Random();
		float randomFloat = randomGenerator.nextFloat() / 10;
		return String.format("%.2f", randomFloat);
	}
	
	public static String getRandomFloatFiveDecimals() {
		Random randomGenerator = new Random();
		float randomFloat = randomGenerator.nextFloat() / 10;
		return String.format("%.5f", randomFloat);
	}
	
	public static String getRandomFloatThreeDecimals() {
		Random randomGenerator = new Random();
		float randomFloat = randomGenerator.nextFloat() / 10;
		return String.format("%.3f", randomFloat);
	}
	
	public static String randomAmount(long min,long max) {

		Random random = new Random();
		float val =  (min + (random.nextFloat() * (max - min)));
	        return String.format("%.2f", val);
	}
	
	public static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(new Date());
		return date;
	}

}
