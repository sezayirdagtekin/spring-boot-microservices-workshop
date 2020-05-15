package com.sezo.moviecatalogservice.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

	@RequestMapping("/{userId}")
	@HystrixCommand(fallbackMethod="getFallBackCatalog")
	public List<Catalog> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = restTemplate.getForObject("http://movie-rating-service/rating/user/" + userId,
				UserRating.class);

		return userRating.getRatings().stream().map(r -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + r.getMovieId(), Movie.class);

			return new Catalog(movie.getName(), movie.getDescription(), r.getRating());
		}).collect(Collectors.toList());

	}

	public List<Catalog> getFallBackCatalog(@PathVariable("userId") String userId) {
		return Arrays.asList(new Catalog("No movie", "", 0));

	}
}
