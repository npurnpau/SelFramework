package main.java.com.demo.objectRepository;

import org.openqa.selenium.By;

public class HomePage {
	
	public static By Logo = By.xpath("//a[@class='nav-logo-link']");
	public static By TodaysDeal = By.xpath("//div[@id='nav-cross-shop-links']/a[2]");
	public static By verifyDeal = By.xpath("//div[@class='sdl-maincontent']/div[1]");
	public static By learnMore = By.xpath("//div[@id='dealActionButton']/img");
	public static By searchBox = By.id("twotabsearchtextbox");
	public static By Go= By.xpath("//input[@class='nav-submit-input']");
	public static By product_link= By.xpath("(//div[@id='atfResults']//img)[1]");
	public static By addToCart= By.id("add-to-cart-button");
}
