package de.mankido.domains;

import java.util.LinkedList;

public class Profile {

	private String username;
	
	private LinkedList<Post> posts;

	public Profile(String username) {
		this.username = username;
		posts = new LinkedList<Post>();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public void addPost(Post post) {
		posts.add(post);
	}
	
	public LinkedList<Post> getPosts() {
		return posts;
	}
	
}
