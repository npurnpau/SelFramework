package main.java.com.demo.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClaimPage {
	
	//public static By myClaims = By.xpath("//html/body/form/div[3]/div/div[1]/div[5]/div[1]/ul/li[2]/div[2]");
	// Claim Type selection objects
	//public static By myClaims = By.xpath("//div[@id='menuTabs']/ul/li[2]/div[2]");
	public static By myClaims = By.id("MyClaimsTab");
	//public static By submitClaims = By.xpath("//div[@id='MyClaims']/div/a[2]");
	public static By submitClaims = By.id("OnlineClaimSub");
	//public static By attention = By.id("ctl00_Content_TabManageReceipts");
	public static By attention = By.id("ctl00_Content_TbManageReceipts");
	public static By proceedWithClaim = By.id("ctl00_Content_btnProceed");
	public static By iAgree = By.id("ctl00_Content_btnAgree");
	public static By claimType = By.id("ctl00_Content_mddClaimtype");
	public static By claimNext = By.id("ctl00_Content_btnNext");
	
	//Submit Claim objects
	public static By providerName = By.id("ctl00_Content_UcSearchProvider1_txtName");
	public static By proiverAddress = By.id("ctl00_Content_UcSearchProvider1_txtAddress");
	public static By providerCity = By.id("ctl00_Content_UcSearchProvider1_txtCity");
	public static By providerSearch = By.id("ctl00_Content_UcSearchProvider1_btnSearchProv");
	
	//Drug Claim
	public static By addNewClaim = By.id("ctl00_Content_btnNewClaim");
	public static By DispenseDate = By.id("ctl00_Content_mdbServDate");
	public static By Patient = By.id("ctl00_Content_mddPatient");
	public static By DIN = By.id("ctl00_Content_txtDIN");
	public static By Quantity = By.id("ctl00_Content_txtQuantity");
	public static By amount = By.id("ctl00_Content_txtAmount");
	public static By update = By.id("ctl00_Content_btnAddClaim");
	public static By submitClaim = By.id("ctl00_Content_btnWarn");
	public static By claimSummary = By.id("adjudtitle");
	public static By claimReference = By.xpath("//table[@id='ctl00_Content_gvClaims']/tbody/tr[2]/td[3]");
	
	//View My Plan Coverage
	//public static By myCoverage = By.xpath("//div[@id='menuTabs']/ul/li[3]/div[2]");
	public static By myCoverage = By.id("MyCoverageTab");
	//public static By viewMyPlanCoverage = By.xpath("//div[@id='MyCoverage']/div/a[3]");
	public static By viewMyPlanCoverage = By.id("CoverageQuery");
	public static By benefit = By.xpath("//table[@id='ctl00_Content_gvBenefitCoverage']/tbody/tr[2]");
	public static By procedureCode = By.id("ctl00_Content_txtSPCodetxtBox");
	public static By coverageSubmit = By.id("ctl00_Content_btnSubmit");
	public static By eligible = By.id("ctl00_Content_lblDCEligible");
	//public static By dependentInformation = By.xpath("//td[@id='ctl00_Content_mnuMemberDependentn1']/table/tbody/tr/td/a");
	public static By dependentInformation = By.id("ctl00_Content_mnuMemberDependentn1");
	public static By view = By.id("ctl00_Content_gvDependents_ctl02_lbnViewDepBen");
}

	
