package com.sezo.ratingdataservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sezo.ratingdataservice.model.MovieRating;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@RequestMapping("/{movieId}")
	MovieRating getRating(@PathVariable("movieId") int movieId) {
		return new MovieRating(movieId, 90);

	}

}
