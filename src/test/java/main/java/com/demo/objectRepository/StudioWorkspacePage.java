package main.java.com.demo.objectRepository;

import org.openqa.selenium.By;

public class StudioWorkspacePage {
	public static By projectSettingsHeader = By.name("wm-project-settings-header");
	//public static By projectcreateContinueMessage = By.xpath("//div[@dialogid='projectConfigDialog']//button[@title='Save']");
	public static By projectcreateContinueMessage = By.name("wm-project-settings-first-save");
	
			//("//button[contains(text(),'Save')]");
	
	public static By threeColumnWithTopNav = By.xpath("//ul[@class='list-inline web']/li[@name='wm-page-layout-threeColumnWithTopNav']");
	public static By btnCreatePage = By.name("btn-create-page");
	
}
