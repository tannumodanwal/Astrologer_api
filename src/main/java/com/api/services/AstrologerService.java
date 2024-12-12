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
