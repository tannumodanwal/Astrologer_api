package com.api.DTO;

import jakarta.validation.constraints.Pattern;

public class AstrologerLoginRequest {

	
	 @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Invalid Gmail address")
		private String email;

	    @Pattern(
		        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
		        message = "Password must contain at least, one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long"
		    )
		private String password;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	    
	    
	
}
