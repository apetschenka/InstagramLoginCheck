package de.mankido.domains;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import de.mankido.helper.Util;

public class Post {

	private String text;
	
	private Date postDate;

	public Post(String text, Date postDate) {
		this.text = text;
		this.postDate = postDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return String.format("Post [text=%s, postDate=%s]", text, postDate);
	}
	
	public JsonObject toJson() {		
		JsonObjectBuilder jsonObject = Json.createObjectBuilder();
		jsonObject.add("text", text);
		jsonObject.add("postDate", Util.formatter.format(postDate));
		return jsonObject.build();
	}
	
}
