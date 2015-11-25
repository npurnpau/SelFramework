package com.wavemakerstudio.teststeps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import org.openqa.selenium.By;



public class Business_Logic_Methods extends UtilMethods {
	static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	static Calendar cal;

	synchronized public boolean verifySampleDB(
			Hashtable<String, String> data) throws Throwable {

		boolean flag = true;
		
		try {
			if (signin_flow(data)) {
				if(createWebApp()){
					if(importSampleDb()){
					SuccessReport("Existing user signin verification ",
						"User successfully signed in");
			 		}
				}
			} else {
				failureReport("Existing user signin verification ",
						"User not able to signin");
			}
			Thread.sleep(3000);

		} catch (Exception e) {

			flag = false;
			failureReport("Existing user signin verification ",
					"User not able to signin");
		}
		return flag;

	}
	
	
	
}
