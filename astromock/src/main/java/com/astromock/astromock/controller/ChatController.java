package com.astromock.astromock.controller;


import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.astromock.astromock.entity.Message;
import com.astromock.astromock.repository.MessageRepository;


@Controller
public class ChatController {

    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(MessageRepository messageRepository,
                          SimpMessagingTemplate messagingTemplate) {
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/privateMessage")
    public void sendPrivateMessage(Message message) {

        message.setTimestamp(LocalDateTime.now());

        String conversationId;

        if (message.getSenderId().compareTo(message.getReceiverId()) < 0) {
            conversationId = message.getSenderId() + "_" + message.getReceiverId();
        } else {
            conversationId = message.getReceiverId() + "_" + message.getSenderId();
        }
        message.setConversationId(conversationId);

        messageRepository.save(message);

        messagingTemplate.convertAndSend(
                "/topic/chat/" + conversationId,
                message
        );
    }
}