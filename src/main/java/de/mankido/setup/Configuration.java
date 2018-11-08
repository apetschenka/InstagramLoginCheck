package de.mankido.setup;

import java.util.Properties;

public class Configuration {

	private static Configuration instance = null;
	private static Properties properties;

	private static String dbUrl = null; // "jdbc:postgresql://localhost:5432/poiGenerator";
	private static String dbUser = null; // "postgres";
	private static String dbPassword = null; // "knorke";

	private static String instagramLoginUrl = null;
	private static String instagramUser = null;
	private static String instagramPassword = null;
	private static String instagramQuery = null;

	private Configuration() {
		properties = System.getProperties();
		
		dbUrl = properties.getProperty("DB_URL");
		dbUser = properties.getProperty("DB_USER");
		dbPassword = properties.getProperty("DB_PASSWORD");

		instagramLoginUrl = properties.getProperty("INSTAGRAM_LOGIN_URL");
		instagramUser = properties.getProperty("INSTAGRAM_USER");
		instagramPassword = properties.getProperty("INSTAGRAM_PASSWORD");
	}

	public static Configuration instance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	public String getDBUrl() {
		return dbUrl;
	}

	public String getDBUser() {
		return dbUser;
	}

	public String getDBPassword() {
		return dbPassword;
	}

	public String getInstagramLoginUrl() {
		return instagramLoginUrl;
	}

	public String getInstagramUser() {
		return instagramUser;
	}

	public String getInstagramPassword() {
		return instagramPassword;
	}

	public String getInstagramQuery() {
		return instagramQuery;
	}
	
}
