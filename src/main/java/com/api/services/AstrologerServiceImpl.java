package com.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entities.Astrologer;
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
	public Astrologer getById(Long astrologerId) {
		Optional<Astrologer> findById = astrologerRepo.findById(astrologerId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public String addAstrologer(Astrologer astrologer) {
		
		astrologerRepo.save(astrologer);
		return "AstroLoger successfully insert !";
		
	}

	@Override
	public String updateAstrologer(Astrologer astrologer) {
        
		astrologerRepo.save(astrologer);
		return "Astrologer successfully update !";
	}

	@Override
	public String deleteById(Long astrologerId) {
		if(astrologerRepo.existsById(astrologerId)) {
		   astrologerRepo.deleteById(astrologerId);
		   return "Astrologer successFully delete !";
		}
		return "No record found";
	}

    
}
