package de.sogeti.automation.testcases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import de.sogeti.automation.base.BaseTestClass;
import de.sogeti.automation.pages.CookiesPopUp;
import de.sogeti.automation.pages.NavbarHeader;
import de.sogeti.automation.util.DriverUtilis;

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

	@ParameterizedTest
	@CsvSource({ 
		"https://www.sogeti.be/		, 		BELGIUM" ,
		"https://www.sogeti.fi/		, 		FINLAND",
		"https://www.fr.sogeti.com/ , 		FRANCE",
		"https://www.sogeti.de/		, 		GERMANY",
		"https://www.sogeti.ie/		, 		IRELAND",
		"https://www.sogeti.lu/		, 		LUXEMBOURG",
		"https://www.sogeti.nl/		, 		NETHERLANDS",
		"https://www.sogeti.no/		, 		NORWAY",
		"https://www.sogeti.es/		, 		SPAIN",
		"https://www.sogeti.se/		, 		SWEDEN",
		"https://www.uk.sogeti.com/	, 		UK",
		"https://www.us.sogeti.com/	, 		USA"	
	})
	void test_all_country_specific_links_working(String pageLink, String pageName) {
		CookiesPopUp cookiesPopUp = new CookiesPopUp(driver);
		cookiesPopUp.acceptAllCookies();

		NavbarHeader navbarHeader = new NavbarHeader(driver);
		navbarHeader.gotoInternationalPage(pageName);

		DriverUtilis.switchWindows(driver);

		Assertions.assertEquals(pageLink, driver.getCurrentUrl());

	}
}