package com.utils;

import java.io.IOException;

public class LogRunnable implements Runnable {
	private volatile boolean exit = false;
	String searchKeyword=null;
	public LogRunnable(String searchKeyword) {
		this.searchKeyword=searchKeyword;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
		LogcatUtil.runProcess(true,this.searchKeyword, "adb shell logcat");
		System.out.println("Done");
			  
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
