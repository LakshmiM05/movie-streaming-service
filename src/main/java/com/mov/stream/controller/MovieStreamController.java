package com.mov.stream.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mov.stream.service.MovieStreamService;

@RestController
public class MovieStreamController {
	
	public static final Logger log = Logger.getLogger(MovieStreamController.class.getName());
    public static final String VIDEO_DIRECTORY = "D:\\Video\\";
    @Autowired
    private MovieStreamService movieStreamService;
    
	
	 @GetMapping("/stream/{videoPath}")
	    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String videoPath) throws FileNotFoundException {
	        File file = new File(VIDEO_DIRECTORY + videoPath);
	        if (file.exists()) {
	            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
	            return ResponseEntity.ok()
	                    .contentType(MediaType.parseMediaType("video/mp4"))
	                    .body(inputStreamResource);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 @GetMapping("/stream/videoById/{videoId}")
	 public ResponseEntity<InputStreamResource> streamVideoById(@PathVariable String videoId) throws FileNotFoundException {
		 System.out.println("videopath::1111  ");
		String videopath = movieStreamService.getStreamPath(Long.valueOf(videoId));
		System.out.println("videopath::1111  "+videopath);
		return streamVideo(videopath);
		 
	 }
	 

}
