package com.api.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.api.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Users") 
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "(Please enter your name (only letters and spaces).")
    private String name;

    @Min(value = 18, message = "Please the age field, Age must be greater than 18")
    @Max(value = 100, message = "Age must be less than 100")
    private int age;

    @NotBlank(message="Duplicate Entry")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email must be a Gmail address")
    @Column(unique=true)
	private String email;

	
	@Pattern(
		    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
		    message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long"
		)
    private String password;
	
	@Column(name = "image_name")
	private String imageName;


	

	@NotNull(message = "field Birth Date must be valid")
    private LocalDate dateOfBirth;
    
	@NotNull(message = "Birth time must be provided")
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime timeOfBirth;

	@Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits and contain only numbers")
    private String contactNumber;

	
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Place must contain only letters and spaces")
    private String place;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Field Gender is required")
    private Gender gender;


    private int astrlogerId;

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
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public User(int id,
			@Pattern(regexp = "^[a-zA-Z ]+$", message = "(Please enter your name (only letters and spaces).") String name,
			@Min(value = 18, message = "Please the age field, Age must be greater than 18") @Max(value = 100, message = "Age must be less than 100") int age,
			@NotBlank(message = "Duplicate Entry") @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email must be a Gmail address") String email,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long") String password,
			String imageName, @NotNull(message = "field Birth Date must be valid") LocalDate dateOfBirth,
			@NotNull(message = "Birth time must be provided") LocalTime timeOfBirth,
			@Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits and contain only numbers") String contactNumber,
			@Pattern(regexp = "^[A-Za-z ]+$", message = "Place must contain only letters and spaces") String place,
			@NotNull(message = "Field Gender is required") Gender gender, int astrlogerId) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.password = password;
		this.imageName = imageName;
		this.dateOfBirth = dateOfBirth;
		this.timeOfBirth = timeOfBirth;
		this.contactNumber = contactNumber;
		this.place = place;
		this.gender = gender;
		this.astrlogerId = astrlogerId;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalTime getTimeOfBirth() {
		return timeOfBirth;
	}

	public void setTimeOfBirth(LocalTime timeOfBirth) {
		this.timeOfBirth = timeOfBirth;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAstrlogerId() {
		return astrlogerId;
	}

	public void setAstrlogerId(int astrlogerId) {
		this.astrlogerId = astrlogerId;
	}

	
	public User(int id,
			@NotBlank(message = "field Name must be valid") @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must only contain letters and spaces") String name,
			@NotNull(message = "field Age must be valid") @NotNull(message = "Not convert int to string") @Min(value = 18, message = "Age must be at least 18") @Max(value = 100, message = "Age must be less than 100") int age,
			@NotBlank(message = "field Email must be valid") @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email must be a Gmail address") String email,
			@NotBlank(message = "field Password must be valid") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long") String password,
			@NotNull(message = "field Birth Date must be valid") LocalDate dateOfBirth,
			@NotNull(message = "Birth time must be provided") LocalTime timeOfBirth,
			@Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits and contain only numbers") @NotNull(message = "Field Contact number is required") String contactNumber,
			@NotBlank(message = "field Place must be valid") @Pattern(regexp = "^[A-Za-z ]+$", message = "Place must contain only letters and spaces") String place,
			@NotNull(message = "Field Gender is required") Gender gender,
			@NotNull(message = "field AstrlogerID must be valid") int astrlogerId) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.timeOfBirth = timeOfBirth;
		this.contactNumber = contactNumber;
		this.place = place;
		this.gender = gender;
		this.astrlogerId = astrlogerId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", password=" + password
				+ ", dateOfBirth=" + dateOfBirth + ", timeOfBirth=" + timeOfBirth + ", contactNumber=" + contactNumber
				+ ", place=" + place + ", gender=" + gender + ", astrlogerId=" + astrlogerId + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
