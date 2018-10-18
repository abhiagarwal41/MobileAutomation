package com.utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogcatUtil {
	   private static final String[] WIN_RUNTIME = { "cmd.exe", "/C" };
	   private static final String[] OS_LINUX_RUNTIME = { "/bin/bash", "-l", "-c" };
	   private static final String filePath = "./logs/response.txt";

	   private LogcatUtil() {
	   }

	   private static <T> T[] concat(T[] first, T[] second) {
	       T[] result = Arrays.copyOf(first, first.length + second.length);
	       System.arraycopy(second, 0, result, first.length, second.length);
	       return result;
	   }

	   public static List<String> runProcess(boolean isWin,String parameter, String... command) throws IOException {
	       System.out.print("command to run: ");
	       for (String s : command) {
	           System.out.print(s);
	       }
	       System.out.print("\n");
	       String[] allCommand = null;
	           if (isWin) {
	               allCommand = concat(WIN_RUNTIME, command);
	           } else {
	               allCommand = concat(OS_LINUX_RUNTIME, command);
	           }
	           ProcessBuilder pb = new ProcessBuilder(allCommand);
	           pb.redirectErrorStream(true);
	           Process p = pb.start();
	           //p.waitFor();
	           BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
	           String _temp = null;
	           List<String> line = new ArrayList<String>();
	           FileWriter writer = new FileWriter(filePath);
	           while ((_temp = in.readLine()) != null) {
	        	   if (Thread.interrupted()) {
//		    		   throw new InterruptedException();
		    		   break;
		    	   }
	        	   if(_temp.contains("com.rohitupreti.testapplication")) {
		              
		               if(_temp.contains(parameter)) {
		            	   System.out.println("temp line: " + _temp);
		            	   writer.write(_temp);
			               line.add(_temp);	   
		               }
	               }
	           }
	           System.out.println("result after command: " + line);
	           writer.flush();
	           writer.close();
	           return line;
	   }
	}