package com.sezo.movieinfoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sezo.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {
	@RequestMapping("/{id}")
	public Movie getMovie(@PathVariable("id") int id) {

		return new Movie(id, "Matrix");

	}

}
