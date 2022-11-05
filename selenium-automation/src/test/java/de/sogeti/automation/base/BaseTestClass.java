package de.sogeti.automation.base;

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
		setDriver();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(8));
		driver.get("https://www.sogeti.com/");
		

	}
	
	@AfterEach
    public void quit() throws InterruptedException {
		//Thread.sleep(4000);
        driver.quit();
    }
	
}
