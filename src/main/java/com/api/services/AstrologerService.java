<<<<<<< HEAD
package com.api.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.api.entities.Astrologer;

@Service
public interface AstrologerService {

	public List<Astrologer> getAllAstrologers();
	
	public Astrologer getById(Long astrologerId);
	
	public String addAstrologer(Astrologer astrologer);
	
	public String updateAstrologer(Astrologer astrologer);
	
	public String deleteById(Long astrologerId);
	
	
	
}
=======
package com.api.services;

import java.io.IOException;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.entities.Astrologer;


@Service
public interface AstrologerService {
	
    public List<Astrologer> getAllAstrologers();	
	public Astrologer getById(Integer astrologerId);	
	public Astrologer addAstrologer(Astrologer astrologer);	
	public Astrologer updateAstrologer(Integer astrologerId,Astrologer astrologer);	
	public String deleteById(Integer astrologerId);
	public Astrologer getAstrologerByEmail(String email);
	public Astrologer loginAstrologer(String email, String password);	
	public Astrologer changePassword(String email, String oldPassword, String newPassword);
	public void uploadAstroImage(Integer astrologerId, MultipartFile imageFile) throws IOException;
	public List<Astrologer> getPendingAstrologers();
	public Astrologer approveAstrologer(Integer astrologerId);
	public long countUnverifiedAstrologers();
	public void verifyAstrologer(Integer astrologerId);
}
>>>>>>> 6565aec (Updated the file with latest changes)
