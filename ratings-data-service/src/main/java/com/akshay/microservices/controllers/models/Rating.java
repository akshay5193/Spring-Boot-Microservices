package com.akshay.microservices.controllers.models;

public class Rating {
	
	public String movieId;
	public int rating;
	
	
	// Constructor
	public Rating(String movieId, int rating) {
		this.movieId = movieId;
		this.rating = rating;
	}
	
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

}
