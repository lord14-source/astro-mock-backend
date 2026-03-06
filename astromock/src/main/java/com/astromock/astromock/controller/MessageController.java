package com.astromock.astromock.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astromock.astromock.entity.Message;
import com.astromock.astromock.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;

    
    public MessageController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}


	@GetMapping("/{senderId}/{receiverId}")
    public List<Message> getConversation(
            @PathVariable String senderId,
            @PathVariable String receiverId
    ) {
        return messageRepository
                .findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestampAsc(
                        senderId, receiverId,
                        receiverId, senderId
                );
    }
}