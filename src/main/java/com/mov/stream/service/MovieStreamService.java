package com.mov.stream.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieStreamService {
	@Autowired
	private RestTemplate restTemplate;
	
	//public String catalogServiceUrl="http://MOVIE-CATALOG-SERVICE/movie-info/find-path-by-id";
	public static final String CATALOG_SERVICE = "http://movie-catalog-service/movie-info/find-path-by-id/";
	public String getStreamPath(Long id) {
		System.out.println("Inside getStream");
		ResponseEntity<String> res =restTemplate.getForEntity(CATALOG_SERVICE+id, String.class,id);
		System.out.println("Response::  " +res.getBody());
		return  res.getBody();
		
	}

}
