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
