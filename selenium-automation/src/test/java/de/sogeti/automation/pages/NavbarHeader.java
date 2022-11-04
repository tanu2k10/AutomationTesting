package de.sogeti.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavbarHeader {

	WebDriver driver;

	@FindBy(xpath = "//*[@id=\"main-menu\"]/ul/li[3]/div[1]/span")
	WebElement serviceLink;

	@FindBy(linkText = "Automation")
	WebElement automationLink;

	@FindBy(css = "#main-menu > ul > li.has-children")
	List<WebElement> mainMenuList;

	public NavbarHeader(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickMainMenuAndSelectSubMenuLink(String mainMenuHeading, String subMenuLinkText) {
		for (int i = 0; i < mainMenuList.size(); i++) {
			WebElement mainMenuElement = mainMenuList.get(i);
			String mainMenuText = mainMenuElement.findElement(By.tagName("span")).getText();
			if (mainMenuHeading.equals(mainMenuText)) {
				mainMenuElement.click();
				WebElement subMenuElement = mainMenuElement.findElement(By.linkText(subMenuLinkText));
				subMenuElement.click();
				return;
			}
		}

		throw new RuntimeException(String.format("Unable to select the mainMenu:%s and subMenu:%s combination",
				mainMenuHeading, subMenuLinkText));

	}

	public String getSelectedMainMenuName() {
		WebElement selectedWebElement = driver.findElement(By.className("selected"));
		return selectedWebElement.findElement(By.tagName("span")).getText();
	}

	public String getSelectedSubMenuName() {
		WebElement selectedWebElement = driver.findElement(By.className("selected"));
		selectedWebElement.click();
		return selectedWebElement.findElement(By.className("selected")).findElement(By.tagName("a")).getText();
	}

	

}
