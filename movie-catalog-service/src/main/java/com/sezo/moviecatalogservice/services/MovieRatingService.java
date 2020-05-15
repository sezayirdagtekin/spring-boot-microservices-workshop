package com.sezo.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sezo.moviecatalogservice.model.MovieRating;
import com.sezo.moviecatalogservice.model.UserRating;

@Service
public class MovieRatingService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallBacUserRating")
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://movie-rating-service/rating/user/" + userId, UserRating.class);
	}

	private UserRating getFallBacUserRating(String userId) {
		UserRating userRating =new UserRating();
		userRating.setUserid(userId);
		userRating.setRatings(Arrays.asList(new MovieRating(0,0)));
		return userRating;
	}


}
