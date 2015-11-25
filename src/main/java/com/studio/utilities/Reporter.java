package com.studio.utilities;
  

import com.wavemaker.studio.TestEngine;
import com.wavemaker.support.ConfiguratorSupport;
import com.wavemaker.support.ReportStampSupport;

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
