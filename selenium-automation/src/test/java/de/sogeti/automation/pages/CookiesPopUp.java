package de.sogeti.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CookiesPopUp {

	private WebDriver driver;

	@FindBy(className = "acceptCookie")
	private WebElement allowAllCookies;

	@FindBy(className = "cookieSettings")
	private WebElement manageCookieSettings;

	@FindBy(className = "declineCookie")
	private WebElement declineOptionalCookies;

	public CookiesPopUp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void acceptAllCookies() {
		allowAllCookies.click();
	}

	public void acceptDeclineCookie() {
		declineOptionalCookies.click();
	}

}
