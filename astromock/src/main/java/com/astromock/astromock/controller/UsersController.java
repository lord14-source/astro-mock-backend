package com.astromock.astromock.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astromock.astromock.entity.ChatUsers;
import com.astromock.astromock.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
    private  UsersRepository usersRepository;
    

    public UsersController(UsersRepository usersRepository) {
    	
		this.usersRepository = usersRepository;
	}

	@PostMapping
    public ChatUsers createUser(@RequestBody ChatUsers user) {
        return usersRepository.save(user);
    }

    @GetMapping
    public List<ChatUsers> getAllUsers() {
        return usersRepository.findAll();
    }
}