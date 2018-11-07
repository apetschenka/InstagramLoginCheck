package de.mankido.setup;

import java.util.Properties;

public class Configuration {

	private static Configuration instance = null;
	private static Properties properties;

	private static String dbUrl = null; // "jdbc:postgresql://localhost:5432/poiGenerator";
	private static String dbUser = null; // "postgres";
	private static String dbPassword = null; // "knorke";

	private static String instagramLoginUrl = null;

	private Configuration() {
		properties = System.getProperties();
		
		dbUrl = properties.getProperty("DB_URL");
		dbUser = properties.getProperty("DB_USER");
		dbPassword = properties.getProperty("DB_PASSWORD");

		instagramLoginUrl = properties.getProperty("INSTAGRAM_LOGIN_URL");
	}

	public static Configuration instance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	public static String getDBUrl() {
		return dbUrl;
	}

	public static String getDBUser() {
		return dbUser;
	}

	public static String getDBPassword() {
		return dbPassword;
	}

	public static String getInstagramLoginUrl() {
		return instagramLoginUrl;
	}
	
}
