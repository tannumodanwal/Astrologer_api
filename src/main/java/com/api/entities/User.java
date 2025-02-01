package com.api.entities;



import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;

@Entity
@Table(name="User_Details")
public class User {
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;


    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than 100")
    private int age;

    @Email
    private String email;

    @NotNull
    private String password;

    @Past(message ="Please enter a valid Date")
    private LocalDate dateOfBirth;
    private LocalTime timeOfBirth;

    @NotNull
    private Long contactNumber;

    private String place;

    private String gender;

    private long astrlogerId;

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

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getAstrlogerId() {
		return astrlogerId;
	}

	public void setAstrlogerId(long astrlogerId) {
		this.astrlogerId = astrlogerId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", password=" + password
				+ ", dateOfBirth=" + dateOfBirth + ", timeOfBirth=" + timeOfBirth + ", contactNumber=" + contactNumber
				+ ", place=" + place + ", gender=" + gender + ", astrlogerId=" + astrlogerId + "]";
	}

	public User(int id, @NotNull String name,
			@Min(value = 18, message = "Age must be at least 18") @Max(value = 100, message = "Age must be less than 100") int age,
			@Email String email, @NotNull String password,
			@Past(message = "Please enter a valid Date") LocalDate dateOfBirth, LocalTime timeOfBirth,
			@NotNull Long contactNumber, String place, String gender, long astrlogerId) {
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

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
    
    
}