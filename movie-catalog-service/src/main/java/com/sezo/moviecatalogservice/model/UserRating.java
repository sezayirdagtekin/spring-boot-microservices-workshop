package com.sezo.moviecatalogservice.model;

import java.util.List;

public class UserRating {

	private List<MovieRating> ratings;

	public List<MovieRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<MovieRating> ratings) {
		this.ratings = ratings;
	}

}
