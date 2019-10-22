package com.akshay.microservices.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.akshay.microservices.models.CatalogItem;
import com.akshay.microservices.models.Movie;
import com.akshay.microservices.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	// get all rated movie IDs
	// For each movie ID, call movie info service and get details
	// Put them all together
	
	@RequestMapping("/{userId}")
	public List <CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4),
				new Rating("2345", 3)
				);
	
		return ratings.stream().map(rating -> { 
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "part 1", rating.getRating());
		})
				.collect(Collectors.toList());
	}

}
