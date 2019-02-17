package com.example.shell.model;

public class JokeApi {
	
	private String category;
	
	private String iconUrl;
	
	private String idJoke;
	
	private String url;

	private String joke;
	
	public JokeApi() {
		
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getIdJoke() {
		return idJoke;
	}

	public void setIdJoke(String idJoke) {
		this.idJoke = idJoke;
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
		return "JokeApi [category=" + category + ", iconUrl=" + iconUrl + ", idJoke=" + idJoke + ", url=" + url + ", joke="
				+ joke + "]";
	}

}
