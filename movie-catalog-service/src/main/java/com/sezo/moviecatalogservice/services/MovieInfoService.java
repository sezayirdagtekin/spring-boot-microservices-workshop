package com.sezo.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sezo.moviecatalogservice.model.Catalog;
import com.sezo.moviecatalogservice.model.Movie;
import com.sezo.moviecatalogservice.model.MovieRating;

@Service
public class MovieInfoService {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallBackCatalogItem")
	public Catalog getCatalogItem(MovieRating r) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + r.getMovieId(), Movie.class);

		return new Catalog(movie.getName(), movie.getDescription(), r.getRating());
	}

	private Catalog getFallBackCatalogItem(MovieRating r) {
		return new Catalog("Movien name not found", "", r.getRating());
	}

}
