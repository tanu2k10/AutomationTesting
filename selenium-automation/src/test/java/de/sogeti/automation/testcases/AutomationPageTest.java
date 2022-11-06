package de.sogeti.automation.testcases;

import org.junit.jupiter.api.Test;

import de.sogeti.automation.base.BaseTestClass;
import de.sogeti.automation.pages.AutomationPage;
import de.sogeti.automation.pages.CookiesPopUp;
import de.sogeti.automation.pages.NavbarHeader;

public class AutomationPageTest extends BaseTestClass {
	
	@Test
	void test_automationPage_contactUsForm_Submission() {
		String mainMenu = "Services";
		String subMenu = "Automation";
		CookiesPopUp cookiesPopUp = new CookiesPopUp(driver);
		cookiesPopUp.acceptAllCookies();

		NavbarHeader navbarHeader = new NavbarHeader(driver);
		navbarHeader.clickMainMenuAndSelectSubMenuLink(mainMenu, subMenu);
		AutomationPage automationPage = new AutomationPage(driver);
		automationPage.fillContantUsFormWithRandomData();
		
		
	}

}
