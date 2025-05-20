<<<<<<< HEAD
package com.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="AstrlogerDetails")
public class Astrologer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	public Long id;
	
	@Column(name = "name")
    private String name;

    @Column(name = "experience")
    private String experience;

    @Column(name = "knowledge")
    private String knowledge;

    @Column(name = "language")
    private String language;

    @Column(name = "photourl")
    private String photourl;

    @Column(name = "price")
    private Long price;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "orders")
    private String orders;

    @Column(name = "verified")
    private boolean verified;

	//Getters/Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	//Constructor

	public Astrologer(Long id, String name, String photourl, String knowledge, String language, String experience,
			Long rating, String orders, boolean verified, Long price) {
		super();
		this.id = id;
		this.name = name;
		this.photourl = photourl;
		this.knowledge = knowledge;
		this.language = language;
		this.experience = experience;
		this.rating = rating;
		this.orders = orders;
		this.verified = verified;
		this.price = price;
	}

	//constructor super class
	public Astrologer() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	//toString Method
	
	@Override
	public String toString() {
		return "Astrologer [id=" + id + ", name=" + name + ", photourl=" + photourl + ", knowledge=" + knowledge
				+ ", language=" + language + ", experience=" + experience + ", rating=" + rating + ", orders=" + orders
				+ ", verified=" + verified + ", price=" + price + "]";
	}
	
	
	
	
	
}
=======
package com.api.entities;



import com.api.enums.Status;

//import org.hibernate.annotations.processing.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



@Entity
@Table(name = "Astrologers") 
public class Astrologer {

	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotBlank(message = "Name is required")
//    @NotBlank(message = "name invalid hai")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must only contain letters and spaces")
    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email must be a Gmail address")
    @Column(unique=true)
	private String email;

	@Pattern(
		    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
		    message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long"
		)
    private String password;
    
    @Pattern(regexp = "^\\d+\\s*years$", message = "Experience must be a valid followed by 'years'")
  //  @NotBlank(message = "Experience is required")
    @Column(name = "experience")
    private String experience;

    @NotBlank(message = "Knowledge is required")
   // @Pattern(regexp = "^[a-zA-Z]+(, [a-zA-Z]+)+$", message = "Field must contain letters separated by commas")
    @Column(name = "knowledge")
    private String knowledge;
    
    @NotBlank(message = "language is required")
    @Pattern(regexp = "^[a-zA-Z]+(, [a-zA-Z]+)+$", message = "Field must contain letters separated by commas")
    @Column(name = "language")
    private String language;
    
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits and contain only numbers")
    private String  contactNumber;
    
    @Column(name = "About")
    private String about;

//    @NotBlank(message = "PhotoUrl is required")
//    @URL(message = "Please enter a valid URL for the photo")
//    @Column(name = "photourl")
    private String photourl;

    @Min(value = 0, message = "Price must be at least 0")
    @NotNull(message = "Price is required")
    @Column(name = "price")
    private Long price;

//    @NotNull(message = "Rating field is required")
//    @Min(value = 0, message = "Rating must be at least 0")
//    @Max(value = 5, message = "Rating must be at most 5")
//    @Column(name = "rating")
    private Long rating;

//    @Pattern(regexp = "^\\d+\\s*orders$", message = "Orders must be a valid number followed by 'orders'")
//    @NotBlank(message = "Orders field is required")
//    @Column(name = "orders")
    private String orders;
    
    
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private boolean isVerified = false;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	@Override
	public String toString() {
		return "Astrologer [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", experience=" + experience + ", knowledge=" + knowledge + ", language=" + language
				+ ", contactNumber=" + contactNumber + ", about=" + about + ", photourl=" + photourl + ", price="
				+ price + ", rating=" + rating + ", orders=" + orders + ", status=" + status + ", isVerified="
				+ isVerified + "]";
	}

	public Astrologer(int id,
			@Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must only contain letters and spaces") String name,
			@Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email must be a Gmail address") String email,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long") String password,
			@Pattern(regexp = "^\\d+\\s*years$", message = "Experience must be a valid followed by 'years'") String experience,
			@NotBlank(message = "Knowledge is required") String knowledge, String language,
			@Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits and contain only numbers") String contactNumber,
			String about, String photourl,
			@Min(value = 0, message = "Price must be at least 0") @NotNull(message = "Price is required") Long price,
			Long rating, String orders, Status status, boolean isVerified) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.experience = experience;
		this.knowledge = knowledge;
		this.language = language;
		this.contactNumber = contactNumber;
		this.about = about;
		this.photourl = photourl;
		this.price = price;
		this.rating = rating;
		this.orders = orders;
		this.status = status;
		this.isVerified = isVerified;
	}

	public Astrologer() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
    
	
}
>>>>>>> 6565aec (Updated the file with latest changes)
