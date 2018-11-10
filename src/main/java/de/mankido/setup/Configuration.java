package de.mankido.setup;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	/**
	 * [7] app_id, app_ver, data, log_type, seq, session_id, device_id
	 */
	public static final String INSTA_MSG_TMP = "{\"app_id\":\"%s\",\"app_ver\":\"%s\",\"data\":%s,\"log_type\":\"%s\",\"seq\":%s,\"session_id\":\"%s\",\"device_id\":\"%s\"}";

	/**
	 * [3] time, name, extra
	 */
	public static final String INSTA_MSG_DATA_TMP = "[{\"time\":%s,\"name\":\"%s\",\"extra\":%s}]";

	/**
	 * [13] app_id, tos_id, start_time, tos_array, tos_len, tos_seq, tos_cum, log_time, referrer, referrer_domain, url, original_referrer, original_referrer_domain
	 */
	public static final String INSTA_MSG_DATA_EXTRA_TMP = "{\"app_id\":\"%s\",\"tos_id\":\"%s\",\"start_time\":%s,\"tos_array\":%s,\"tos_len\":%s,\"tos_seq\":%s,\"tos_cum\":%s,\"log_time\":%s,\"referrer\":%s,\"referrer_domain\":%s,\"url\":%s,\"original_referrer\":%s,\"original_referrer_domain\":%s}";
	
	private static Configuration instance = null;

	private String dbUrl = null; // "jdbc:postgresql://localhost:5432/poiGenerator";
	private String dbUser = null; // "postgres";
	private String dbPassword = null; // "knorke";

	private String instaAuthUrl = null; // https://graph.instagram.com/logging_client_events
	private String instaAccessToken = null; // 1217981644879628|65a937f07619e8d4dce239c462a447ce
	
	private String instaLoginUrl = null; // https://www.instagram.com/accounts/login/ajax/
	private String instaUser = null; // mankidoTester
	private String instaPassword = null; // mankido123

	private Configuration() {
		Properties properties = System.getProperties();
		
		try {
			properties.load(new FileReader(new File("configuration.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dbUrl = properties.getProperty("DB_URL");
		dbUser = properties.getProperty("DB_USER");
		dbPassword = properties.getProperty("DB_PASSWORD");

		instaAuthUrl = properties.getProperty("INSTAGRAM_AUTHENTIFICATION_URL");
		instaAccessToken = properties.getProperty("INSTAGRAM_ACCESS_TOKEN");
		instaLoginUrl = properties.getProperty("INSTAGRAM_LOGIN_URL");
		instaUser = properties.getProperty("INSTAGRAM_USER");
		instaPassword = properties.getProperty("INSTAGRAM_PASSWORD");
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

	public String getInstaLoginUrl() {
		return instaLoginUrl;
	}

	public String getInstaUser() {
		return instaUser;
	}

	public String getInstaPassword() {
		return instaPassword;
	}

	public String getInstaAuthUrl() {
		return instaAuthUrl;
	}

	public String getInstaAccessToken() {
		return instaAccessToken;
	}	
}
