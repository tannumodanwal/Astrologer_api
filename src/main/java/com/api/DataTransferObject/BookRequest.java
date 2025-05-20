package com.api.DTO;

import com.api.enums.CommunicationType;

public class BookRequest {
	
	private int userId;
    private int astrologerId;
    private CommunicationType communicationType;
    
    
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAstrologerId() {
		return astrologerId;
	}
	public void setAstrologerId(int astrologerId) {
		this.astrologerId = astrologerId;
	}
	public CommunicationType getCommunicationType() {
		return communicationType;
	}
	public void setCommunicationType(CommunicationType communicationType) {
		this.communicationType = communicationType;
	}
    
    

}
