

package com.api.services;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.entities.Astrologer;
import com.api.enums.Status;
import com.api.exceptions.CustomNotFoundException;
import com.api.repository.AstrologerRepo;

@Service
public class AstrologerServiceImpl implements AstrologerService{

	
	@Autowired
	private AstrologerRepo astrologerRepo;

	@Override
	public List<Astrologer> getAllAstrologers() {
		return astrologerRepo.findAll();
	}

	@Override
	public Astrologer getById(Integer astrologerId) {
		Optional<Astrologer> findById = astrologerRepo.findById(astrologerId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
		
	}

	@Override
	public Astrologer addAstrologer(Astrologer astrologer) {

		return astrologerRepo.save(astrologer);

	}

	@Override
	public Astrologer updateAstrologer(Integer astrologerId,Astrologer astrologer) {
	 
		Astrologer existingAstrologer = astrologerRepo.findById(astrologerId)
				.orElseThrow(()-> new CustomNotFoundException("Astrologer not found with ID: "+astrologerId)); 
				
	    // Sirf relevant fields ko update karein
	    existingAstrologer.setName(astrologer.getName());
	    existingAstrologer.setEmail(astrologer.getEmail()); 
	    existingAstrologer.setPassword(astrologer.getPassword()); 
	    existingAstrologer.setExperience(astrologer.getExperience());
	    existingAstrologer.setKnowledge(astrologer.getKnowledge());
	    existingAstrologer.setLanguage(astrologer.getLanguage());
	    existingAstrologer.setContactNumber(astrologer.getContactNumber());
	    existingAstrologer.setAbout(astrologer.getAbout());
	    existingAstrologer.setPhotourl(astrologer.getPhotourl());
	    existingAstrologer.setPrice(astrologer.getPrice());
	    existingAstrologer.setRating(astrologer.getRating());
	    existingAstrologer.setOrders(astrologer.getOrders());
        existingAstrologer.setStatus(astrologer.getStatus());
        


	    return astrologerRepo.save(existingAstrologer);
		
	}

	@Override
	public String deleteById(Integer astrologerId) {
		Optional<Astrologer> existingAstrologer = astrologerRepo.findById(astrologerId);
       if (existingAstrologer.isPresent()) {
	        astrologerRepo.deleteById(astrologerId);
	        return "Astrologer successfully deleted!";
        } else {
	        throw new CustomNotFoundException("Astrologer not found with ID: " + astrologerId);
	    }
	}

	@Override
	public Astrologer getAstrologerByEmail(String email) {
		return astrologerRepo.findByEmail(email)
	               .orElseThrow(() -> new CustomNotFoundException("Astrologer not found with email: " + email));
	}

	@Override
	public Astrologer loginAstrologer(String email, String password) {
		  return astrologerRepo.findByEmail(email)
                  .filter(astrologer -> astrologer.getPassword().equals(password))     //.getPassword().equals(password))
                  .orElse(null);	
	}

	@Override
	public Astrologer changePassword(String email, String oldPassword, String newPassword) {
		Astrologer astrologer  = getAstrologerByEmail(email);
		    if (astrologer.getPassword().equals(oldPassword)) {
		    	astrologer.setPassword(newPassword);
		        return astrologerRepo.save(astrologer); // returns updated astrologer
		    } else {
		        return null; 
		    }
		
	}

	@Override
	public void uploadAstroImage(Integer astrologerId, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		
		
		Astrologer astrologer = astrologerRepo.findById(astrologerId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + astrologerId));

        // Upload folder
        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        // Save file
        String fileName = imageFile.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.write(filePath, imageFile.getBytes());

        // Update image name
        astrologer.setPhotourl(fileName);
        astrologerRepo.save(astrologer);
		
	}

	@Override
	public List<Astrologer> getPendingAstrologers() {
		return astrologerRepo.findByStatus(Status.PENDING);
	}

	@Override
	public Astrologer approveAstrologer(Integer astrologerId) {
		Astrologer astrologer = astrologerRepo.findById(astrologerId)
                .orElseThrow(() -> new RuntimeException("Astrologer not found"));

        astrologer.setStatus(Status.APPROVED); // or Status.REJECTED
        return astrologerRepo.save(astrologer);
	}

	@Override
	public long countUnverifiedAstrologers() {
		
		return astrologerRepo.countByIsVerifiedFalse();
	}

	@Override
	public void verifyAstrologer(Integer astrologerId) {
	 
		Astrologer astro = astrologerRepo.findById(astrologerId)
		        .orElseThrow(() -> new CustomNotFoundException("Astrologer not found"));

		    astro.setVerified(true);
		    astrologerRepo.save(astro);
		
	}

	
	

}

