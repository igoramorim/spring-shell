package com.example.shell.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Joke {

//	@JsonProperty("category")
//	private String category;
	
	@JsonProperty("category")
	private List<String> category;
	
	@JsonProperty("icon_url")
	private String iconUrl;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("value")
	private String joke;
	
	public Joke() {
		
	}

//	public String getCategory() {
//		return category;
//	}
//
//	public void setCategory(String category) {
//		this.category = category;
//	}
	
	public String getIconUrl() {
		return iconUrl;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJoke() {
		return joke;
	}

	public void setJoke(String joke) {
		this.joke = joke;
	}

	@Override
	public String toString() {
		return "Joke [category=" + category + ", iconUrl=" + iconUrl + ", id=" + id + ", url=" + url + ", joke=" + joke
				+ "]";
	}

}
