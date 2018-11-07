package de.mankido.resources;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.mankido.domains.Profile;

@RestController
public class LoginResource {
	
	private static final Logger logger = Logger.getLogger(LoginResource.class.getSimpleName());

	@GET
	@RequestMapping("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject login(@PathParam("username") String username, @PathParam("password") String password) {
		logger.info(String.format("login: %s | %s", username, password));
		
		Profile profile = new Profile(username);
		
		return Json.createObjectBuilder().add("user", profile.getUsername()).build();
	}
}
