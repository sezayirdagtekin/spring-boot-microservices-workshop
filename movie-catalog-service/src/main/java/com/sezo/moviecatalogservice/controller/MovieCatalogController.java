package com.sezo.moviecatalogservice.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.sezo.moviecatalogservice.model.Catalog;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@RequestMapping("/{id}")
	public List<Catalog> getCatalog(@PathVariable("id") int id) {

		return Collections.singletonList(new Catalog("Transformers", "Transformers description", id));

	}

}
