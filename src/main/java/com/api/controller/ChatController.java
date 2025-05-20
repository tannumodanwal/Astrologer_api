package com.api.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;

import com.api.entities.ChatMessage;
import com.api.repository.ChatMessageRepo;

@Controller
@CrossOrigin(origins = "http://localhost:5173")

public class ChatController {

	 @Autowired
	    private SimpMessagingTemplate messagingTemplate;
	 
	 @Autowired
	    private ChatMessageRepo chatRepo;
	 
	    @MessageMapping("/sendMessage") // /app/sendMessage
	    @SendTo("/topic/messages")
	    public ChatMessage send(ChatMessage message) {
	        message.setTimestamp(LocalDateTime.now());
	        chatRepo.save(message);
	        messagingTemplate.convertAndSend("/topic/messages", message);
	        return message;
	    }


	
}
