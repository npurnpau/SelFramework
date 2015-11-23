package main.java.com.demo.buisinessLibrary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import org.openqa.selenium.By;

import main.java.com.demo.objectRepository.HomePage;

public class Demo_Buisiness_Methods extends Demo_GUI_Methods {
	static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	static Calendar cal;
	// static String userName;
	private static int count = 1;
	


	// -------------------sujit methods --------------------




	synchronized public boolean ExistingUserSignin(
			Hashtable<String, String> data) throws Throwable {

		boolean flag = true;
		try {
			userName = data.get("USER_NAME");
			
			if (signin_flow(data)) {
				SuccessReport("Existing user signin verification ",
						"User successfully signed in");
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


	synchronized public boolean ExistingUserSignin_fail(
			Hashtable<String, String> data) throws Throwable {

		boolean flag = true;
		try {
			userName = data.get("USER_NAME");
		
			driver.findElement(By.name("abc")).click();
			Thread.sleep(3000);

		} catch (Exception e) {

			flag = false;
			failureReport("Existing user signin verification ",
					"User not able to signin");
		}
		return flag;

	}
	
	synchronized public boolean selectClaim(
			Hashtable<String, String> data) throws Throwable {

		boolean flag = true;
		try {
			userName = data.get("USER_NAME");
			
			if (selectClaimType(data)) {
				SuccessReport("Select Claim Type",
						"Successfully selected claim type");
			} else {
				failureReport("Existing user signin verification ",
						"User not able to select claim type");
			}
			Thread.sleep(3000);

		} catch (Exception e) {

			flag = false;
			failureReport("Existing user signin verification ",
					"User not able to signin");
		}
		return flag;

	}
	
	synchronized public boolean submitClaim(
			Hashtable<String, String> data) throws Throwable {

		boolean flag = true;
		try {
			userName = data.get("USER_NAME");
			
			if (submitClaimType(data)) {
				SuccessReport("Submit Claim Type",
						"Successfully submitted selected claim type");
			} else {
				failureReport("Submit Claim Type ",
						"User not able to submit claim type");
			}
			Thread.sleep(3000);

		} catch (Exception e) {

			flag = false;
			failureReport("Existing user signin verification ",
					"User not able to signin");
		}
		return flag;

	}
	
	synchronized public boolean verifyClaimCoverage(
			Hashtable<String, String> data) throws Throwable {

		boolean flag = true;
		try {
			userName = data.get("USER_NAME");
			
			if (verifyMemberClaimCoverage(data)) {
				SuccessReport("Member coverage eligibility verification",
						"Successfully verified the coverage eligibility of member");
			} else {
				failureReport("Member coverage eligibility verification",
						"Failed to verifiy the coverage eligibility of member");
			}
			Thread.sleep(3000);

		} catch (Exception e) {

			flag = false;
			failureReport("Existing user signin verification ",
					"User not able to signin");
		}
		return flag;

	}
	
	synchronized public boolean verifyDependentCoverage(
			Hashtable<String, String> data) throws Throwable {

		boolean flag = true;
		try {
			userName = data.get("USER_NAME");
			
			if (verifyDependentClaimCoverage(data)) {
				SuccessReport("Dependent coverage eligibility verification",
						"Successfully verified the coverage eligibility of Dependent");
			} else {
				failureReport("Dependent coverage eligibility verification",
						"Failed to verifiy the coverage eligibility of member");
			}
			Thread.sleep(3000);

		} catch (Exception e) {

			flag = false;
			failureReport("Existing user signin verification ",
					"User not able to signin");
		}
		return flag;

	}
	
	
	
	synchronized public boolean verifyDealOfTheDay(Hashtable<String, String> data) throws Throwable {

		boolean flag = true;
		try {
			
			driver.findElement(HomePage.Logo).click();
			if (dailyDeal(data)) {
				SuccessReport("Existing user signin verification ",
						"User successfully signed in");
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
	
	synchronized public boolean search(Hashtable<String, String> data) throws Throwable {

		boolean flag = true;
		try {
			
			driver.findElement(HomePage.Logo).click();
			if (searchTerm(data)) {
				SuccessReport("Existing user signin verification ",
						"User successfully signed in");
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
