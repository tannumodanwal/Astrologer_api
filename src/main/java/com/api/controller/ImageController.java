package com.api.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:5173")
public class ImageController {
    
	  @GetMapping("/{imageName}")
	 public void getImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		  // Decode image name to handle %20 and other special characters
		    String decodedImageName = URLDecoder.decode(imageName, StandardCharsets.UTF_8);
		    
		    Path path = Paths.get("uploads/" + decodedImageName);
		    
		    if (Files.exists(path)) {
		        response.setContentType(Files.probeContentType(path));
		        Files.copy(path, response.getOutputStream());
		        response.getOutputStream().flush();
		    } else {
		        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		    }
	    }
	
}
