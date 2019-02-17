package com.example.shell.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.shell.model.Joke;
import com.example.shell.model.JokeApi;

@ShellComponent
public class MyCommands {
	
	private static final String BASE_API_URL = "https://api.chucknorris.io/jokes/random";
	private static final String CATEGORIES_API_URL = "https://api.chucknorris.io/jokes/categories";
	private static final String MY_API_URL = "http://localhost:8090/jokes";
	
	@Autowired
	private RestTemplate restClient;
	
//	@ShellMethod("Add two integers together.")
//    public int add(int a, int b) {
//        return a + b;
//    }
	
//	@ShellMethod("Testing flags")
//	public String flag(@ShellOption({"-C", "--command"}) String command) {
//			return "Digitou flag: " + command;
//	}
	
//	@ShellMethod("Terminate the system.")
//	public String shutdown(@ShellOption(arity=1, defaultValue="false") boolean force) {
//		return "You said " + force;	
//	}
	
	@ShellMethod("Consume Chuck Norris API.")
	public String joke(@ShellOption({"-C", "--category"}) String category) {
		try {
			return getJoke(category);
		} catch (HttpClientErrorException e) {
			return handleError(e);
		}
	}
	
	@ShellMethod("Show joke categories from API Chuck Norris.")
	public String categories() {
		try {
			return getCategories();
		} catch (HttpClientErrorException e) {
			return handleError(e);
		}
	}
	
	@ShellMethod("Show all Jokes from my personal database")
	public String allJokes() {
		try {
			return getAllJokesApi();
		} catch (HttpClientErrorException e) {
			return handleError(e);
		}
	}
	
	@ShellMethod("Show a Joke by ID from my personal database")
	public String jokeById(@ShellOption({"-I", "--id"}) Integer id) {
		try {
			return getJokeByIdApi(id);
		} catch (HttpClientErrorException e) {
			return handleError(e);
		}
	}
	
	@ShellMethod("Get a Joke from Chuck Norris API and save in a local database.")
	public String saveJoke(@ShellOption({"-C", "--category"}) String category) {
		try {
			return saveJokeApi(category);
		} catch (HttpClientErrorException e) {
			return handleError(e);
		}
	}
	
	private String saveJokeApi(String category) {
		Joke joke = getJokeToSave(category);
		
		JokeApi jokeToSave = new JokeApi();
		jokeToSave.setCategory(joke.getCategory() != null ? joke.getCategory().get(0) : null);
		jokeToSave.setIconUrl(joke.getIconUrl());
		jokeToSave.setIdJoke(joke.getId());
		jokeToSave.setJoke(joke.getJoke());
		jokeToSave.setUrl(joke.getUrl());
		
		return restClient.postForObject(MY_API_URL, jokeToSave, String.class);
	}
	
	private Joke getJokeToSave(String category) {
		final String url = BASE_API_URL + "?category={category}";
		return restClient.getForObject(url, Joke.class, category);
	}
	
	private String getJokeByIdApi(Integer id) {
		final String url = MY_API_URL + "/{id}";
		return restClient.getForObject(url, String.class, id);
	}
	
	private String getAllJokesApi() {
		return restClient.getForObject(MY_API_URL, String.class);
	}
	
	private String getJoke(String category) {
		final String url = BASE_API_URL + "?category={category}";
		return restClient.getForObject(url, Joke.class, category).toString();
	}
	
	private String getCategories() {
		return restClient.getForObject(CATEGORIES_API_URL, String.class);
	}
	
	private String handleError(HttpClientErrorException e) {
		if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			return "Não encontrado";
		}
		return "Erro desconhecido";
	}

}
