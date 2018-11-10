package de.mankido.domains;

import java.util.LinkedList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Profile {

	private String id;
	private String username;
	private String profilePicture;
	private String fullName;
	private int followers;
	private LinkedList<Post> posts;

	public Profile() {
		posts = new LinkedList<Post>();
	}

	public Profile(String id, String username, String profile_picture, String full_name, int followers) {
		this.id = id;
		this.username = username;
		this.profilePicture = profile_picture;
		this.fullName = full_name;
		this.followers = followers;
		posts = new LinkedList<Post>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profile_picture) {
		this.profilePicture = profile_picture;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String full_name) {
		this.fullName = full_name;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public void addPost(Post post) {
		posts.add(post);
	}

	public LinkedList<Post> getPosts() {
		return posts;
	}

	@Override
	public String toString() {
		return String.format("Profile [id=%s, username=%s, profile_picture=%s, full_name=%s, followers=%s, posts=%s]",
				id, username, profilePicture, fullName, followers, posts);
	}

	public JsonObject toJson() {
		JsonObjectBuilder jsonObject = Json.createObjectBuilder();
		jsonObject.add("id", id == null ? "0" : id);
		jsonObject.add("username", username == null ? "0" : username);
		jsonObject.add("fullName", fullName == null ? "0" : fullName);
		jsonObject.add("followers", followers);
		return jsonObject.build();
	}

}
