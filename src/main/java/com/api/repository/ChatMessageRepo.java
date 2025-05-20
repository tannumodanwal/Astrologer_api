package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entities.ChatMessage;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long>{
	
	List<ChatMessage> findBySenderIdAndReceiverId(Long senderId, Long receiverId);

}
