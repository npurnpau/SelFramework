package main.java.com.demo.buisinessLibrary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.UUID;

import main.java.com.demo.objectRepository.ClaimPage;
import main.java.com.demo.objectRepository.HomePage;
import main.java.com.demo.objectRepository.ImportDatabaseWizardPage;
import main.java.com.demo.objectRepository.Loginpage;
import main.java.com.demo.objectRepository.ProjectsListPage;
import main.java.com.demo.objectRepository.StudioWorkspacePage;
import main.java.com.demo.objectRepository.WmStudioHeaderPage;
import main.java.com.demo.objectRepository.cartPage;

import org.apache.commons.lang3.RandomStringUtils;
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
			System.out.println(data.get("USER_NAME"));
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
	
	synchronized public boolean createWebApp() {
		boolean flag = true;
		try {
			
			waitForElementPresent(ProjectsListPage.createApplication, "Create Application Button");
			click(ProjectsListPage.createApplication, "Verify create application Button");
			click(ProjectsListPage.selectPlatformasWeb, "Selected as web application");
			
			type(ProjectsListPage.applicationName, "Auto_"+RandomStringUtils.randomAlphabetic(10), "Project Name Entered");
			//selectByValue(ProjectsListPage.projectshellSelectbox, "Default Project", "Project shell Selected as Default");
			click(ProjectsListPage.createProjectButton, "Project create button has clicked");
			
			waitForVisibilityOfElement(StudioWorkspacePage.projectSettingsHeader,"Project created successfully and project settings header has been displayed");
			waitForVisibilityOfElement(StudioWorkspacePage.projectcreateContinueMessage,"Project created successfully and project settings header has been displayed");
			click(StudioWorkspacePage.projectcreateContinueMessage, "Continuing with default project settings.");
			click(StudioWorkspacePage.threeColumnWithTopNav, "Three column layout selected by default");
			click(StudioWorkspacePage.btnCreatePage, "Main Page created");
			
		/*	if(driver.findElement(Loginpage.verify_loggedin).getText().equals("Sign in"))
				flag=false;*/

		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}
	
	synchronized public boolean importSampleDb() {
		boolean flag = true;
		try {
			 
			waitForElementPresent(WmStudioHeaderPage.headerImport, "Header Import button");
			click(WmStudioHeaderPage.headerImport, "Clicked on header Import");
			click(WmStudioHeaderPage.ImportDB, "Clicked on import db button");
			waitForElementPresent(ImportDatabaseWizardPage.sampleDBButton, "Sample Db button");
			click(ImportDatabaseWizardPage.sampleDBButton, "Clicked on sample db button");
			click(ImportDatabaseWizardPage.incrementStepButton, "Incremented step 2");
			clickAndWaitForElementPresent(ImportDatabaseWizardPage.incrementStepButton,ImportDatabaseWizardPage.usedbwidgetsButton, "Incremented step 3");
			click(ImportDatabaseWizardPage.usedbwidgetsButton, "Clicked on use db widgets.");
			
			type(ProjectsListPage.applicationName, "Auto_"+RandomStringUtils.randomAlphabetic(10), "Project Name Entered");
			selectByValue(ProjectsListPage.projectshellSelectbox, "Default Project", "Project shell Selected as Default");
			click(ProjectsListPage.createProjectButton, "Project create button has clicked");
			waitForVisibilityOfElement(StudioWorkspacePage.projectSettingsHeader,"Project created successfully and project settings header has been displayed");
			
		/*	if(driver.findElement(Loginpage.verify_loggedin).getText().equals("Sign in"))
				flag=false;*/

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
