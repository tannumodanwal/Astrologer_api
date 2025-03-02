package com.api.exeptionHandling;

import java.util.Map;

public class ErrorResponse {

	  private int status;
	  private String message;
	  private Map<String, String> errors; // ✅ Validation errors ke liye field
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
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	public ErrorResponse(int status, String message, Map<String, String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	   
	  
	  
	  
}
