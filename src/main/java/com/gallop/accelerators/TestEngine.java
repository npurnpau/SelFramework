package com.gallop.accelerators;       

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.gallop.support.ConfiguratorSupport;
import com.gallop.support.ExcelReader;
import com.gallop.support.HtmlReportSupport;
import com.gallop.support.MyListener;
import com.gallop.support.ReportStampSupport;
import com.gallop.utilities.SendMail;


public class TestEngine extends SendMail {
	public static Logger logger = Logger.getLogger(TestEngine.class.getName());

	public static ConfiguratorSupport configProps = new ConfiguratorSupport(
			"config.properties");

	public static ConfiguratorSupport counterProp = new ConfiguratorSupport(
			configProps.getProperty("counterPath"));

	public String currentSuite = "";
	public String method = "";
	//public static String timeStamp = ReportStampSupport.timeStamp().replace(" ", "_").replace(":", "_").replace(".", "_");
	public boolean flag = false;
	public WebDriver webDriver = null;
	public EventFiringWebDriver driver=null;
	public DesiredCapabilities capabilities;
	public  int stepNum = 0;
	public  int PassNum =0;
	public  int FailNum =0;
	public  static int passCounter =0;
	public  static int failCounter =0;
	//public   failCounter =0;
	public String testName = "";
	public String testCaseExecutionTime = "";
	public StringBuffer x=new StringBuffer();
	public String finalTime = "";
	public boolean isSuiteRunning=false;
	public static Map<String, String> testDescription = new LinkedHashMap<String, String>();
	public static Map<String, String> testResults = new LinkedHashMap<String, String>();
	public String url=null;
	static ExcelReader xlsrdr = new ExcelReader(configProps.getProperty("TestData"),configProps.getProperty("sheetName0"));
	public int countcompare = 0;

	public static String browser = null;
	static int len = 0;
	static int i = 0;
	public static ITestContext itc;
	public static String groupNames =null;

	/**
	 * Initializing browser requirements, Test Results file path and Database
	 * requirements from the configuration file
	 * 
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */
	
	@BeforeSuite(alwaysRun=true)
	public void first(ITestContext ctx) throws Throwable{
		ReportStampSupport.calculateSuiteStartTime();
		
	}
	
	
	@Parameters({"browser"})
	@BeforeClass(alwaysRun=true)
	public void first(ITestContext ctx, String browser) throws Throwable{
		itc=ctx;
		
		
		
			if(browser.equalsIgnoreCase("firefox"))
			{
				webDriver = new FirefoxDriver();
				i=i+1;
				

			}
			else if(browser.equalsIgnoreCase("ie"))
			{
				File file = new File("Drivers/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				webDriver= new InternetExplorerDriver();
				i=i+1;
				

			}
			else if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");				
				webDriver=new ChromeDriver();
				i=i+1;

			}

		
		driver = new EventFiringWebDriver(webDriver);
		url = (configProps.getProperty("URL"));
		
		MyListener myListener = new MyListener();
   	driver.register(myListener);
	//	s = new Screen();
		try {
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.manage().window().maximize();
			reportCreater();
			currentSuit = ctx.getCurrentXmlTest().getSuite().getName();
		} catch (Exception e1) {
			System.out.println(e1);
		}
		
	}
	

	

	/**
	 * De-Initializing and closing all the connections
	 * 
	 * @throws Throwable
	 */
	
	//@Parameters({"browser"})
	@AfterSuite(alwaysRun = true)
	public void tearDownFirefox(ITestContext ctx) throws Throwable {
		
		browser="Firefox";
		ReportStampSupport.calculateSuiteExecutionTime();
		
		
			HtmlReportSupport.createHtmlSummaryReport(browser);
		

		driver.quit();
		closeSummaryReport(browser);
		
	}
	
	@AfterSuite(alwaysRun = true)
	public void tearDownChrome(ITestContext ctx) throws Throwable {
		
		browser="Chrome";
		ReportStampSupport.calculateSuiteExecutionTime();
		
		
			HtmlReportSupport.createHtmlSummaryReport(browser);
		

		driver.quit();
		closeSummaryReport(browser);
		
	}

	/**
	 * Write results to Browser specific path
	 * 
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */
	// @Parameters({"browserType"})
	public static String filePath() {
		String strDirectoy = "";
		
				strDirectoy = "HTML";
				
		

		if (strDirectoy != "") {
			new File(configProps.getProperty("screenShotPath") + strDirectoy
					).mkdirs();
		}
	
		File results = new File(configProps.getProperty("screenShotPath") + strDirectoy+"/"+"Screenshots");
		if(!results.exists())
		{
			results.mkdir();
			HtmlReportSupport.copyLogos();
		}

		return configProps.getProperty("screenShotPath") + strDirectoy;

	}

	/**
	 * Browser type prefix for Run ID
	 * 
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */
	public String result_browser() {
		if (groupNames.equals("")) {
			if (configProps.getProperty("browserType").equals("ie")) {
				return "IE";
			} else if (configProps.getProperty("browserType").equals("firefox")) {
				return "Firefox";
			} else {
				return "Chrome";
			}
		} else {
			if (browser.equalsIgnoreCase("ie")) {
				return "IE";

			} else if (browser.equalsIgnoreCase("firefox")) {
				return "Firefox";

			} else {
				return "Chrome";

			}
		}
	}

	/**
	 * Related to Xpath
	 * 
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */
	public String methodName() {
		if (groupNames.equals("")) {
			if (configProps.getProperty("browserType").equals("ie")) {
				return "post";
			} else {
				return "POST";
			}
		} else {
			if (browser.equals("ie")) {
				return "post";
			} else {
				return "POST";
			}
		}
	}
	
	@Parameters({"browser"})
	@BeforeMethod(alwaysRun = true)
	public void reportHeader(Method method, ITestContext ctx, String browser) throws Throwable {
		itc = ctx;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy hh mm ss SSS");
		String formattedDate = sdf.format(date);
		calculateTestCaseStartTime();

		flag = false;

		tc_name = method.getName().toString() + "-" + browser;
		String[] ts_Name = this.getClass().getName().toString().split("\\.");
		packageName = ts_Name[0] + "." + ts_Name[1] + "."+ ts_Name[2];

		
			testHeader(tc_name);
		
		stepNum = 0;
		PassNum = 0;
		FailNum = 0;
		testName = method.getName();
		String[] tmp=testName.split("_");
		String desc = tmp[0]+" "+tmp[1]+" Demo";
		testDescription.put(testName+ "-" + browser, desc);

	}
	
		
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		calculateTestCaseExecutionTime();
		closeDetailedReport(browser);
		
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun=true)
	public void close(String browser){
		driver.close();
		if(FailNum!=0)
		{
			
			testResults.put(tc_name, "FAIL");
			failCounter=failCounter+1;
		}
		else
		{
			testResults.put(tc_name, "PASS");
			passCounter=passCounter+1;
		}
	}
	
	
	public void reportCreater() throws Throwable {
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));

		switch (intReporterType) {
		case 1:

			break;
		case 2:

			htmlCreateReport();
			//HtmlReportSupport.createDetailedReport();

			break;
		default:

			htmlCreateReport();
			break;
		}
	}
	
	public void calculateTestCaseStartTime(){			
		iStartTime = System.currentTimeMillis();
	}

	
	/***
	 * 		This method is supposed to be used in the @AfterMethod to calculate the total test case execution time 
	 * to show in Reports by taking the start time from the calculateTestCaseStartTime method.
	 */
	public void calculateTestCaseExecutionTime(){	
		iEndTime = System.currentTimeMillis();
		iExecutionTime=(iEndTime-iStartTime);
		TimeUnit.MILLISECONDS.toSeconds(iExecutionTime);
		HtmlReportSupport.executionTime.put(tc_name,String.valueOf(TimeUnit.MILLISECONDS.toSeconds(iExecutionTime)));
		//System.out.println(tc_name+";Time :"+String.valueOf(TimeUnit.MILLISECONDS.toSeconds(iExecutionTime)));
	}
	
	
	public void onSuccess(String strStepName, String strStepDes) {


		File file = new File(TestEngine.filePath() + "/" +strTestName+"_Results"
				 + ".html");// "SummaryReport.html"
		Writer writer = null;
	     stepNum = stepNum + 1;
		
		try {
			//testdescrption.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x));
			if (!map.get(packageName + ":" + tc_name).equals("FAIL")) {
				map.put(packageName + ":" + tc_name, "PASS");
				//map.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x.toString()));
			}
			writer = new FileWriter(file, true);
			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'>" + strStepDes + "</td> ");
			writer.write("<td class='Pass' align='center'><img  src='./Screenshots/passed.ico' width='18' height='18'/></td> ");
			PassNum  =PassNum + 1;
			String strPassTime = ReportStampSupport.getTime();
			writer.write("<td><small>" + strPassTime + "</small></td> ");
			writer.write("</tr> ");
			writer.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void onWarning(String strStepName, String strStepDes) {
		Writer writer = null;
		try {
			File file = new File(TestEngine.filePath() + "/" + strTestName+"_Results"
					+ ".html");// "SummaryReport.html"

			writer = new FileWriter(file, true);
			stepNum = stepNum + 1;

			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'>" + strStepDes + "</td> ");
			FailNum = FailNum + 1;

		
			writer.write("<td class='Fail'  align='center'><a  href='"+"./Screenshots"+"/"
					+ strStepDes.replace(" ", "_")
					+ ".jpeg'"+" alt= Screenshot  width= 15 height=15 style='text-decoration:none;'><img src='./Screenshots/warning.ico' width='18' height='18'/></a></td>");

			String strFailTime = ReportStampSupport.getTime();
			writer.write("<td><small>" + strFailTime + "</small></td> ");
			writer.write("</tr> ");
			writer.close();
			
		} catch (Exception e) {

		}

	}


	/*
	 * 
	 * 
	 */
	public void onFailure(String strStepName, String strStepDes) {
		Writer writer = null;
		try {
			File file = new File(TestEngine.filePath() + "/" + strTestName+"_Results"
					 + ".html");// "SummaryReport.html"

			writer = new FileWriter(file, true);
			stepNum = stepNum + 1;

			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'>" + strStepDes + "</td> ");
			FailNum = FailNum + 1;

		
			writer.write("<td class='Fail' align='center'><a  href='"+"./Screenshots"+"/"
					+ strStepDes.replace(" ", "_")
					+ ".jpeg'"+" alt= Screenshot  width= 15 height=15 style='text-decoration:none;'><img  src='./Screenshots/failed.ico' width='18' height='18'/></a></td>");

			String strFailTime = ReportStampSupport.getTime();
			writer.write("<td><small>" + strFailTime + "</small></td> ");
			writer.write("</tr> ");
			writer.close();
			if (!map.get(packageName + ":" + tc_name).equals("PASS")) {
				map.put(packageName + ":" + tc_name+":", "FAIL");
				//map.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x.toString()));
			}
		} catch (Exception e) {

		}

	}
	
	public void closeDetailedReport(String browser) {

		File file = new File(TestEngine.filePath() + "/" + strTestName+"_Results"
				 + ".html");// "SummaryReport.html"
		Writer writer = null;

		try {
			writer = new FileWriter(file, true);
			writer.write("</table>");
			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("</colgroup>");
			writer.write("<tfoot>");
			writer.write("<tr class='heading'> ");
			writer.write("<th colspan='4'>Execution Time In Seconds (Includes Report Creation Time) : "
					+ executionTime.get(tc_name)+ "&nbsp;</th> ");
			writer.write("</tr> ");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Steps Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + PassNum
					+ "</td>");
			writer.write("<td class='fail'>&nbsp;Steps Failed&nbsp;: </td>");
			writer.write("<td class='fail'>" + FailNum
					+ "</td>");
			writer.write("</tr>");
			writer.close();
		} catch (Exception e) {

		}
	}

	public void closeSummaryReport(String browser) {
		File file = new File(TestEngine.filePath() + "/" + "SummaryResults"+browser
				+ ".html");// "SummaryReport.html"
		Writer writer = null;
		try {
			writer = new FileWriter(file, true);

			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' /> ");
			writer.write("</colgroup> ");
			writer.write("<tfoot>");
			writer.write("<tr class='heading'>");
			writer.write("<th colspan='4'>Total Duration  In Seconds (Including Report Creation) : "
					+ ((int)iSuiteExecutionTime) + "</th>");
			writer.write("</tr>");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Tests Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + passCounter
					+ "</td> ");
			writer.write("<td class='fail'>&nbsp;Tests Failed&nbsp;:</td>");
			writer.write("<td class='fail'> " + failCounter
					+ "</td> ");
			writer.write("</tr>");
			writer.write("</tfoot>");
			writer.write("</table> ");

			writer.close();
		} catch (Exception e) {

		}
	}
	
	public void reportStep(String StepDesc) {
		StepDesc = StepDesc.replaceAll(" ", "_");
		File file = new File(TestEngine.filePath() + "/" + strTestName+"_Results"
				 + ".html");// "SummaryReport.html"
		Writer writer = null;

		try {
			writer = new FileWriter(file, true);
			if (BFunctionNo > 0) {
				writer.write("</tbody>");
			}
			writer.write("<tbody>");
			writer.write("<tr class='section'> ");
			writer.write("<td colspan='5' onclick=toggleMenu('" + StepDesc+stepNum+ "')>+ " + StepDesc + "</td>");
			writer.write("</tr> ");
			writer.write("</tbody>");
			writer.write("<tbody id='" + StepDesc+stepNum + "' style='display:table-row-group'>");
			writer.close();
			BFunctionNo = BFunctionNo + 1;
		} catch (Exception e) {

		}
	}


	
}
