package com.api.exeptionHandling;

public class ErrorResponse {
	private int status;
	private String message; 
	//getters/setters
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}  
}