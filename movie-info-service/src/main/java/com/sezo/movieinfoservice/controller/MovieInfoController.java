package com.sezo.movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sezo.movieinfoservice.model.Movie;
import com.sezo.movieinfoservice.model.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{id}")
	public Movie getMovie(@PathVariable("id") int id) {

		MovieSummary movieSummary = restTemplate
				.getForObject("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey, MovieSummary.class);
		return new Movie(id, movieSummary.getTitle(), movieSummary.getOverview());

	}

}
