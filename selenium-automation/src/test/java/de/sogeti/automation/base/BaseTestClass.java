package de.sogeti.automation.base;

import static de.sogeti.automation.util.PropertiesUtil.properties;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestClass {

	public WebDriver driver;

	@BeforeAll
	public static void setDriver() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void setUp() {
		int implicitlyWait = Integer.valueOf(properties.getProperty("implicitWait"));
		int pageLoadTimeout = Integer.valueOf(properties.getProperty("pageLoadTimeout"));
		String url = properties.getProperty("url");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
		driver.get(url);

	}

	@AfterEach
	public void quit() throws InterruptedException {
		// Thread.sleep(4000);
		driver.quit();
	}

}
