package com.akshay.microservices.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.microservices.CatalogItem;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@RequestMapping("/{userId}")
	public List <CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		return Collections.singletonList(
					new CatalogItem("KGF", "part 1", 4)
				);
	}

}
