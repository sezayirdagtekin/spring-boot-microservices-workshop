package com.sezo.moviecatalogservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sezo.moviecatalogservice.model.Catalog;
import com.sezo.moviecatalogservice.model.Movie;
import com.sezo.moviecatalogservice.model.MovieRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	RestTemplate restTemplate;

	private static final String SERVICE_URL = "http://localhost:8082/movies/";

	@RequestMapping("/{id}")
	public List<Catalog> getCatalog(@PathVariable("id") String id) {

		List<MovieRating> ratings = Arrays.asList(new MovieRating(4, 80), new MovieRating(3, 90));

		return ratings.stream().map(r -> {
			Movie movie = restTemplate.getForObject(SERVICE_URL + r.getMovieId(), Movie.class);
			return new Catalog(movie.getName(), "Description", r.getRating());
		}).collect(Collectors.toList());

	}

}
