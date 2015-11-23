package com.gallop.utilities;
  
import com.gallop.accelerators.ActionEngine;
import com.gallop.accelerators.TestEngine;
import com.gallop.support.ConfiguratorSupport;
import com.gallop.support.HtmlReportSupport;
import com.gallop.support.ReportStampSupport;

public class Reporter extends TestEngine {
	public static ConfiguratorSupport configProps = new ConfiguratorSupport(
			"config.properties");
	static String timeStamp = ReportStampSupport.timeStamp().replace(":", "_")
			.replace(".", "_");

	public void reportCreater(String browser) throws Throwable {
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

	
}
