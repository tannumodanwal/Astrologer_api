package com.api.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class ChatMessage {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("senderId")
    private Long senderId;
    @JsonProperty("receiverId")
    private Long receiverId;
    private String message;
    private LocalDateTime timestamp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", message="
				+ message + ", timestamp=" + timestamp + "]";
	}
	public ChatMessage(Long id, Long senderId, Long receiverId, String message, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.message = message;
		this.timestamp = timestamp;
	}
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	
}
