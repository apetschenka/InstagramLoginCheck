package de.mankido.resources;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.handler.MessageContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.mankido.domains.Profile;
import de.mankido.setup.Configuration;

@RestController
public class LoginResource {

	private static final Logger logger = Logger.getLogger(LoginResource.class.getSimpleName());

	@GET
	@RequestMapping("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject login(@PathParam("username") String username, @PathParam("password") String password) {
		logger.infof("login: %s | %s", username, password);
		HttpEntity entity = null;
		
		try {
			HttpClient httpClient = HttpClients.createDefault();
			String instaAuthUrl = Configuration.instance().getInstaAuthUrl();
			HttpPost httpPost = new HttpPost(instaAuthUrl);
			List<NameValuePair> params = generateAuthParams();
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			Header[] headers = response.getHeaders(MessageContext.HTTP_REQUEST_HEADERS);

			String instaLoginUrl = Configuration.instance().getInstaLoginUrl();
			httpPost = new HttpPost(instaLoginUrl);
			params = generateLoginPost(username, password);
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			httpPost.setHeaders(headers);
			response = httpClient.execute(httpPost);
			
			entity = response.getEntity();
//		    Scanner s = new Scanner(entity.getContent()).useDelimiter("\\A");
//		    logger.info(s.next());
			
		} catch (UnsupportedEncodingException e) {
			logger.info("UnsupportedEncodingException : ", e);
		} catch (ClientProtocolException e) {
			logger.info("ClientProtocolException : ", e);
		} catch (IOException e) {
			logger.info("IOException : ", e);
		}

		if(entity != null) {
			Profile profile = new Profile();
			return profile.toJson();
		}
		else {
			return Json.createObjectBuilder().build();
		}
	}

	private List<NameValuePair> generateAuthParams() throws UnsupportedEncodingException {		
		long dateInMs = new Date().getTime();
		String messageDataExtra = String.format(Configuration.INSTA_MSG_DATA_EXTRA_TMP, "1217981644879628",
				"e4jtzx", dateInMs / 1000, "[1,0]", 1, 6, 17, dateInMs,
				"https://www.instagram.com/accounts/login/?source=auth_switcher", "www.instagram.com",
				"/accounts/login/?source=auth_switcher", "", "");
		
		DecimalFormat decimalFormat = new DecimalFormat("#.000"); 
		String messageData = String.format(Configuration.INSTA_MSG_DATA_TMP, decimalFormat.format(new Double(dateInMs) / 1000),
				"instagram_web_time_spent_bit_array", messageDataExtra);
		
		String message = String.format(Configuration.INSTA_MSG_TMP, "1217981644879628", "1.0.0", messageData,
				"client_event", 51, "166f360cf99-b65d94", "W-Lv3AALAAGKsvLlkMmXrcIlQBMG");

		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("access_token", Configuration.instance().getInstaAccessToken()));
		params.add(new BasicNameValuePair("message", message));
		return params;
	}

	private List<NameValuePair> generateLoginPost(String username, String password) throws UnsupportedEncodingException {
		List<NameValuePair> params = new ArrayList<NameValuePair>(3);
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("queryParams", "{\"source\":\"auth_switcher\"}"));
		return params;
	}
}
