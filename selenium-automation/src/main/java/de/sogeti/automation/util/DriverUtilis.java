package de.sogeti.automation.util;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class DriverUtilis {
	
	public static void switchWindows(WebDriver driver) {
		String mainWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();  
		for (String handle : handles) {
		    if (!handle.equals(mainWindow)) {
		          driver.switchTo().window(handle);
		          break;
		    }
		}
	}

}
