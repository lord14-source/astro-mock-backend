package com.astromock.astromock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astromock.astromock.entity.ChatUsers;

public interface UsersRepository extends JpaRepository<ChatUsers, String> {
}
