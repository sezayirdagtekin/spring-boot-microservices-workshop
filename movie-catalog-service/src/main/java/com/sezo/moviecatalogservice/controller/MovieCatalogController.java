package com.sezo.moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.sezo.moviecatalogservice.model.Catalog;
import com.sezo.moviecatalogservice.model.Movie;
import com.sezo.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	WebClient.Builder webClientBuilder;
	
	
	private static final String MOVIE_SERVICE_URL = "http://localhost:8082/movies/";
	private static final String RATING_SERVICE_URL = "http://localhost:8083/rating/user/";

	@RequestMapping("/{userId}")
	public List<Catalog> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = restTemplate.getForObject(RATING_SERVICE_URL+userId,UserRating.class);

		return userRating.getRatings().stream().map(r -> {
			//Movie movie = restTemplate.getForObject(SERVICE_URL + r.getMovieId(), Movie.class);
			Movie movie =webClientBuilder.build().get().uri(MOVIE_SERVICE_URL+r.getMovieId()).retrieve().bodyToMono(Movie.class).block();
			
			return new Catalog(movie.getName(), "Description", r.getRating());
		}).collect(Collectors.toList());

	}

}
