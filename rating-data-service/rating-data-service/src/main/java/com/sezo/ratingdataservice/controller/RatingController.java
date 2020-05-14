package com.sezo.ratingdataservice.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sezo.ratingdataservice.model.MovieRating;
import com.sezo.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@RequestMapping("/{movieId}")
	MovieRating getRating(@PathVariable("movieId") int movieId) {
		return new MovieRating(movieId, 90);

	}

	@RequestMapping("/user/{userId}")
	public UserRating getUserRating(@PathVariable("userId") int userId) {
		List<MovieRating> ratings = Arrays.asList(new MovieRating(3, 80), new MovieRating(549, 90));
		UserRating ur= new UserRating();
		ur.setRatings(ratings);
		return ur;
	}
}
