package com.sezo.moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.sezo.moviecatalogservice.model.Catalog;
import com.sezo.moviecatalogservice.model.UserRating;
import com.sezo.moviecatalogservice.services.MovieInfoService;
import com.sezo.moviecatalogservice.services.MovieRatingService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	WebClient.Builder webClientBuilder;

	@Autowired
	MovieInfoService movieInfoService;

	@Autowired
	MovieRatingService movieRatingService;

	@RequestMapping("/{userId}")
	public List<Catalog> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = movieRatingService.getUserRating(userId);

		return userRating.getRatings().stream().map(r -> {
			return movieInfoService.getCatalogItem(r);
		}).collect(Collectors.toList());

	}

}
