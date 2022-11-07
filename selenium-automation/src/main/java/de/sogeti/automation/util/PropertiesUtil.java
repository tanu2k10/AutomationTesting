package de.sogeti.automation.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static Properties properties;

	static {
		properties = new Properties();
		InputStream input = null;
		try {
			input = ClassLoader.getSystemClassLoader().getResourceAsStream("application.properties");
			properties.load(input);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

}
