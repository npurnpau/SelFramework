package com.wavemakerstudio.teststeps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.wavemaker.studio.ActionEngine;
import com.wavemakerstudio.locaters.ImportDatabaseWizardPage;
import com.wavemakerstudio.locaters.Loginpage;
import com.wavemakerstudio.locaters.ProjectsListPage;
import com.wavemakerstudio.locaters.StudioWorkspacePage;
import com.wavemakerstudio.locaters.WmStudioHeaderPage;

public class UtilMethods extends ActionEngine {
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
			waitForVisibilityOfElement(StudioWorkspacePage.projectcreateContinueMessage,"continue message");
			click(StudioWorkspacePage.projectcreateContinueMessage, "default project settings");
			waitForVisibilityOfElement(StudioWorkspacePage.threeColumnWithTopNav,"Three column layout ");
			waitForElementPresent(StudioWorkspacePage.threeColumnWithTopNav, "Three column layout ");
			click(StudioWorkspacePage.threeColumnWithTopNav, "Three column layout ");
			click(StudioWorkspacePage.btnCreatePage, "button on Main Page");
		
		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}
	
	synchronized public boolean importSampleDb() {
		boolean flag = true;
		try {
			 waitForElementPresent(WmStudioHeaderPage.projectNameinHeader, "Project name on header");
			waitForElementPresent(WmStudioHeaderPage.page_content1, "Header Import button");
			click(WmStudioHeaderPage.headerImport, "Clicked on header Import");
			click(WmStudioHeaderPage.ImportDB, "Clicked on import db button");
			click(ImportDatabaseWizardPage.sampleDBButton, "Sample Db button");
			waitForVisibilityOfElement(ImportDatabaseWizardPage.entitiesCheck, "Tables Checkbox");
			click(ImportDatabaseWizardPage.incrementStepButton, "Incremented step 2");
			waitForVisibilityOfElement(ImportDatabaseWizardPage.variablesCheck, "Variables Checkbox");
			click(ImportDatabaseWizardPage.incrementStepButton, "Incremented step 3");
			click(ImportDatabaseWizardPage.usedbwidgetsButton, "Clicked on use db widgets.");
			
		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
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

}
