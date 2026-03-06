package com.astromock.astromock.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astromock.astromock.entity.Message;

public interface MessageRepository extends JpaRepository<Message, String> {

 
    
    List<Message> findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestampAsc(
            String sender1, String receiver1,
            String sender2, String receiver2
    );
    
    List<Message> findByConversationIdOrderByTimestampAsc(String conversationId);

}
