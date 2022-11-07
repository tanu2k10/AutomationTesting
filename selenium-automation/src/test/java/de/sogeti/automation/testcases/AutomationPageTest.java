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
		
		/*
		 * This test step cannot cannot be Automated due to captcha before submitting the form.
		 * 
		 * CAPTCHA, short for Completely Automated Public Turing test to tell Computers and Humans Apart, 
		 * is explicitly designed to prevent automation, so do not try! :
		 * 
		 * */
		
	}

}
