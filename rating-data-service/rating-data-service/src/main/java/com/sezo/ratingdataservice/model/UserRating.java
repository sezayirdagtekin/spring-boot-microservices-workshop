package com.sezo.ratingdataservice.model;

import java.util.List;

public class UserRating {

	private List<MovieRating> ratings;

	public UserRating() {

	}

	public List<MovieRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<MovieRating> ratings) {
		this.ratings = ratings;
	}

}
