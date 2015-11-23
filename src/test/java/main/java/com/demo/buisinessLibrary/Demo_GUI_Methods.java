package main.java.com.demo.buisinessLibrary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;



import main.java.com.demo.objectRepository.ClaimPage;
import main.java.com.demo.objectRepository.HomePage;
import main.java.com.demo.objectRepository.Loginpage;
import main.java.com.demo.objectRepository.cartPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.gallop.accelerators.ActionEngine;

public class Demo_GUI_Methods extends ActionEngine {
	public static String dragonscreen_name;
	static Calendar cal;
	static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	static String userName;
	static String URL;
	static Actions builder;
	static WebDriver d;
	static String gift_code;
	


	synchronized public boolean signin_flow(Hashtable<String, String> data) {
		boolean flag = true;
		try {
			
			//Thread.sleep(5000);
			driver.manage().window().maximize();
					
		
			waitForElementPresent(Loginpage.username, "Username Text Box");
			type(Loginpage.username, data.get("USER_NAME"), "Username Field");
			type(Loginpage.password, data.get("PASSWORD"), "Password Field");
			click(Loginpage.signin_button, "Verify Signned in");
			
			
			
			Thread.sleep(3000);
			
		/*	if(driver.findElement(Loginpage.verify_loggedin).getText().equals("Sign in"))
				flag=false;*/

		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}
	
	synchronized public boolean selectClaimType(Hashtable<String, String> data) {
		boolean flag = true;
		try {
			
			Thread.sleep(5000);
			//driver.manage().window().maximize();
		    
			click(ClaimPage.myClaims,"My Claims tab");
			click(ClaimPage.submitClaims,"Submit Claims Button");
			
			if(!waitForElementPresent(ClaimPage.attention, "Attention Page"))
				return false;
			
			click(ClaimPage.proceedWithClaim,"Proceed with claim Button");
			click(ClaimPage.iAgree,"I Agree Button");
			selectByVisibleText(ClaimPage.claimType, "DRUG", "Claim Type List Box");
			click(ClaimPage.claimNext,"Next button");
						
			
		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}
	
	synchronized public boolean submitClaimType(Hashtable<String, String> data) {
		boolean flag = true;
		try {
			
			Thread.sleep(4000);
			//driver.manage().window().maximize();
			
			click(ClaimPage.addNewClaim,"add new claim button");
			type(ClaimPage.DispenseDate, data.get("DISPENSE_DATE"), "Dispense Date");
			selectByVisibleText(ClaimPage.Patient, data.get("PATIENT_NAME"), "Patient Name");
			type(ClaimPage.DIN,data.get("DIN"),"DIN");
			click(ClaimPage.Quantity,"Quanitity");
			Thread.sleep(3000);
			type(ClaimPage.Quantity,data.get("Quanitity"),"Quanitity");
			type(ClaimPage.amount,data.get("AMOUNT"),"Amount");
			click(ClaimPage.update,"Update Button");
			click(ClaimPage.submitClaim,"Submit Claim button");
			if(!waitForElementPresent(ClaimPage.claimSummary, "Claim Summary page"))
				return false;
			
		    String claimRef = getText(ClaimPage.claimReference, "Claim Reference");	
		    
		    if(claimRef.length()>0)
		    	SuccessReport("Claim Reference number", "Claim reference number generated successfully and the reference number is '" + claimRef + "'" );
		    else
		    	warningReport("Claim Reference number", "Claim reference number is not generated");
		    
		    Thread.sleep(3000);
			
		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
		}

		finally{return flag;}
	}
	

	synchronized public boolean verifyMemberClaimCoverage(Hashtable<String, String> data) {
		boolean flag = true;
		try {
			
			Thread.sleep(4000);
			//driver.manage().window().maximize();
			
			click(ClaimPage.myCoverage,"My Coverage Tab");
			click(ClaimPage.viewMyPlanCoverage,"View My Plan Coverage Button");
			click(ClaimPage.benefit,"Coverage Benefit");
			type(ClaimPage.procedureCode,data.get("PROCEDURE_CODE"),"Procedure Code");
			click(ClaimPage.coverageSubmit,"Coverage Submit");
			
			String coverageEligible = getText(ClaimPage.eligible, "Coverage Eligible");
			//String txtTest=data.get("ELIGIBILITY").toString();
			if(coverageEligible.equalsIgnoreCase(data.get("ELIGIBILITY").toString())){
				SuccessReport("Coverage Eligibility", "Member is eligible for coverage for the given procedure code '" + data.get("PROCEDURE_CODE") + "'");
			}
			else{
				failureReport("Coverage Eligibility", "Member is not eligible for coverage for the given procedure code '" + data.get("PROCEDURE_CODE") + "'");
			}
						
		    			
		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
		}

		finally{return flag;}
	}

	synchronized public boolean verifyDependentClaimCoverage(Hashtable<String, String> data) {
		boolean flag = true;
		try {
			
			Thread.sleep(4000);
			//driver.manage().window().maximize();
			
			click(ClaimPage.myCoverage,"My Coverage Tab");
			click(ClaimPage.viewMyPlanCoverage,"View My Plan Coverage Button");
			click(ClaimPage.dependentInformation,"Dependent Information Tab");
			click(ClaimPage.view,"view Dependent Information");			
			click(ClaimPage.benefit,"Coverage Benefit");
			type(ClaimPage.procedureCode,data.get("PROCEDURE_CODE"),"Procedure Code");
			click(ClaimPage.coverageSubmit,"Coverage Submit");
			
			String coverageEligible = getText(ClaimPage.eligible, "Coverage Eligible");
			//String txtTest=data.get("ELIGIBILITY").toString();
			if(coverageEligible.equalsIgnoreCase(data.get("ELIGIBILITY").toString())){
				SuccessReport("Coverage Eligibility", "Dependent is eligible for coverage for the given procedure code '" + data.get("PROCEDURE_CODE") + "'");
			}
			else{
				failureReport("Coverage Eligibility", "Dependent is not eligible for coverage for the given procedure code " + data.get("PROCEDURE_CODE"));
			}
						
		    			
		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
		}

		finally{return flag;}
	}
	
	
	synchronized public boolean dailyDeal(Hashtable<String, String> data) {
		boolean flag = true;
		try {
			
			Thread.sleep(5000);
			click(HomePage.TodaysDeal, "Click on Today's Deal Link");
			
			Thread.sleep(3000);
			verifyText(HomePage.verifyDeal, "Deal of the Day", "Verify Text Deal of the Day");
			
			click(HomePage.learnMore, "Learn More Link");
			Thread.sleep(3000);
			

		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}
	
	synchronized public boolean searchTerm(Hashtable<String, String> data) {
		boolean flag = true;
		try {
			
			Thread.sleep(5000);
			click(HomePage.searchBox, "Click on search Box");
			
			type(HomePage.searchBox, data.get("Search"), "Search a Product in Amazon.com");
			click(HomePage.Go, "Cick on Go button");
			Thread.sleep(3000);
			click(HomePage.product_link, "Open a product from search results");
			Thread.sleep(3000);
			click(HomePage.addToCart, "Add tyhe product to cart");
			

		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}
	
	
	synchronized public boolean verify_cart(Hashtable<String, String> data) {
		boolean flag = true;
		try {
			
			Thread.sleep(5000);
			click(cartPage.CartLink, "Click on cart Link");
			Thread.sleep(3000);
			click(cartPage.checkout, "Proceed to Checkout");
			

		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}
	

	

}
