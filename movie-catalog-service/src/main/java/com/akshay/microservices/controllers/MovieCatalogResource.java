package com.akshay.microservices.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.akshay.microservices.models.CatalogItem;
import com.akshay.microservices.models.Movie;
import com.akshay.microservices.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	// get all rated movie IDs

	@RequestMapping("/{userId}")
	public List <CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
	
		return ratings.getUserRating().stream().map(rating -> {
			
			// For each movie ID, call movie info service and get details
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			
			// Put them all together
			return new CatalogItem(movie.getName(), "part 1", rating.getRating());
		})
				.collect(Collectors.toList());
	}

}
