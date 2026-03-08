package com.astromock.astromock.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatUsers {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    private String name;

    private String role; 	// USER or ASTROLOGER
}
