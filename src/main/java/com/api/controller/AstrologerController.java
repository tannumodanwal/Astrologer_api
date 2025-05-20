package com.api.controller;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.entities.Astrologer;
import com.api.exceptions.CustomNotFoundException;
import com.api.services.AstrologerService;

import jakarta.servlet.http.HttpServletResponse;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/astrologers")

public class AstrlogerController {

	
	@Autowired
	private AstrologerService astrologerService;
	
	
	//GetAllAstrologers
	
	@GetMapping
	public ResponseEntity<List<Astrologer>> getAllAstrologers(){

		List<Astrologer> allAstrologers = astrologerService.getAllAstrologers();
		return new ResponseEntity<>(allAstrologers,HttpStatus.OK);
		
	}
	
	//GetByID
	
	@GetMapping("/{astrologerId}")
	public ResponseEntity<Astrologer> getById(@PathVariable Integer astrologerId){

		Astrologer astrologer = astrologerService.getById(astrologerId);
		  if (astrologer == null) {
	            throw new CustomNotFoundException("Astrologer not found with ID: " + astrologerId);
	        }
		return new ResponseEntity<>(astrologer,HttpStatus.OK);
	}
    
	//InsertData
	
	@PostMapping
	public ResponseEntity<Astrologer> addAstrologer(@Validated @RequestBody Astrologer astrologer){
		
		Astrologer status = astrologerService.addAstrologer(astrologer);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	//updateData
	
	@PutMapping("/{astrologerId}")
	public ResponseEntity<Astrologer> updateAstrologer(@PathVariable Integer astrologerId, @Validated @RequestBody Astrologer astrologer){
		
		Astrologer updatedAstrologer = astrologerService.updateAstrologer(astrologerId, astrologer);
        return new ResponseEntity<>(updatedAstrologer, HttpStatus.OK);
	}
	
	
	//DeleteById
	@DeleteMapping("/{astrologerId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer astrologerId){
		
		String status = astrologerService.deleteById(astrologerId);
		
		if (status == null) {
            throw new CustomNotFoundException("Astrologer not found with ID: " + astrologerId);
        }
		return new ResponseEntity<>(status,HttpStatus.OK);
	}

	
	  @GetMapping("/image/{imageName}")
	    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
	        Path path = Paths.get("uploads/" + imageName);
	        if (Files.exists(path)) {
	            response.setContentType(Files.probeContentType(path));
	            Files.copy(path, response.getOutputStream());
	            response.getOutputStream().flush();
	        } else {
	            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        }
	    }

	    @PostMapping("/{astrologerId}/upload-image")
	    public ResponseEntity<String> uploadUserImage(
	            @PathVariable Integer astrologerId,
	            @RequestParam("image") MultipartFile imageFile) throws IOException {

	    	astrologerService.uploadAstroImage(astrologerId, imageFile);

	        return ResponseEntity.ok("Image uploaded and user updated successfully for user ID: " + astrologerId);
	    }
	    
	    @GetMapping("/admin/pending-count")
	    public ResponseEntity<Long> getPendingVerificationCount() {
	        long count = astrologerService.countUnverifiedAstrologers();
	        return ResponseEntity.ok(count);
	    }

	    @PutMapping("/admin/verify/{id}")
	    public ResponseEntity<String> verifyAstrologer(@PathVariable Integer astrologerId) {
	        astrologerService.verifyAstrologer(astrologerId);
	        return ResponseEntity.ok("Astrologer verified successfully!");
	    }

	
	
}
