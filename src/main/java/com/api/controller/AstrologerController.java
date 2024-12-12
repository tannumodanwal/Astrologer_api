package com.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.entities.Astrologer;
import com.api.services.AstrologerService;

@RestController
public class AstrologerController {

	@Autowired
	private AstrologerService astrologerService;
	
	//GetAllAstrologers
	
	@GetMapping("/Astrologers")
	public ResponseEntity<List<Astrologer>> getAllAstrologers(){

		List<Astrologer> allAstrologers = astrologerService.getAllAstrologers();
		return new ResponseEntity<>(allAstrologers,HttpStatus.OK);
		
	}
	
	//GetByID
	
	@GetMapping("/Astrologers/{astrologerId}")
	public ResponseEntity<Astrologer> getById(@PathVariable Long astrologerId){
		
		Astrologer astrologer = astrologerService.getById(astrologerId);
		return new ResponseEntity<>(astrologer,HttpStatus.OK);
	}
    
	//InsertData
	
	@PostMapping("/Astrologers")
	public ResponseEntity<String> addAstrologer(@RequestBody Astrologer astrologer){
		
		String status = astrologerService.addAstrologer(astrologer);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	//updateData
	
	@PutMapping("/Astrologers")
	public ResponseEntity<String> updateAstrologer(@RequestBody Astrologer astrologer){
		
		String status = astrologerService.updateAstrologer(astrologer);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	//DeleteById
	@DeleteMapping("/Astrologers/{astrologerId}")
	public ResponseEntity<String> deleteById(@PathVariable Long astrologerId){
		
		String status = astrologerService.deleteById(astrologerId);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
}
