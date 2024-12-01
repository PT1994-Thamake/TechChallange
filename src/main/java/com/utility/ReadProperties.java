package com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	private static Properties properties;
	private static String filePath = "E:\\2.Workspace\\TechChallange\\data\\Tendable.properties";

	static {
		properties = new Properties();
		try (FileInputStream fis = new FileInputStream(filePath)) {
			properties.load(fis);  // Load properties file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static String getLocator(String page, String element) {
		return properties.getProperty(page + "." + element);
	}

	public static String getTestData(String key) {
		return properties.getProperty(key);
	}
}
