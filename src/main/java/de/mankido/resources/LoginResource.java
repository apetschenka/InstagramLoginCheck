package de.mankido.resources;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
		logger.info(String.format("login: %s | %s", username, password));
		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(Configuration.instance().getInstagramLoginUrl());

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("queryParams", "{\"source\":\"auth_switcher\"}"));
		
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			logger.info(String.format("entity: %s", entity));
		} catch (UnsupportedEncodingException e) {
			logger.info("UnsupportedEncodingException : ", e);
		} catch (ClientProtocolException e) {
			logger.info("ClientProtocolException : ", e);
		} catch (IOException e) {
			logger.info("IOException : ", e);
		}
		
		Profile profile = new Profile();
		
		return profile.toJson();
	}
}
