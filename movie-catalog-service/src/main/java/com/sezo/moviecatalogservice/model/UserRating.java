package com.sezo.moviecatalogservice.model;

import java.util.List;

public class UserRating {

	private String userid;
	private List<MovieRating> ratings;

	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<MovieRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<MovieRating> ratings) {
		this.ratings = ratings;
	}

}
