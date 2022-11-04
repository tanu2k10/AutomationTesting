package de.sogeti.automation.testcases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.sogeti.automation.base.BaseTestClass;
import de.sogeti.automation.pages.CookiesPopUp;
import de.sogeti.automation.pages.NavbarHeader;

public class NavbarHeaderTest extends BaseTestClass {

	@Test
	@DisplayName("Test Case 1: Test service and automation link selected")
	void test_servive_automation_link_selected() {
		String mainMenu = "Services";
		String subMenu = "Automation";
		CookiesPopUp cookiesPopUp = new CookiesPopUp(driver);
		cookiesPopUp.acceptAllCookies();

		NavbarHeader navbarHeader = new NavbarHeader(driver);
		navbarHeader.clickMainMenuAndSelectSubMenuLink(mainMenu, subMenu);

		Assertions.assertEquals(mainMenu, navbarHeader.getSelectedMainMenuName());
		Assertions.assertEquals(subMenu, navbarHeader.getSelectedSubMenuName());
		Assertions.assertTrue(driver.getCurrentUrl().contains("services/automation/"));

	}
}