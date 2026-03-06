package com.astromock.astromock.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astromock.astromock.entity.Message;
import com.astromock.astromock.repository.MessageRepository;

@RestController
@RequestMapping("/api/chat")
//@CrossOrigin("*")
public class ChatRestController {

    private final MessageRepository messageRepository;

    public ChatRestController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/history")
    public List<Message> getHistory(
            @RequestParam String user1,
            @RequestParam String user2
    ) {
    	System.out.println("User "+user1);
        
        String conversationId;

        if (user1.compareTo(user2) < 0) {
            conversationId = user1 + "_" + user2;
        } else {
            conversationId = user2 + "_" + user1;
        }
       

        return messageRepository
                .findByConversationIdOrderByTimestampAsc(conversationId);
    }
}