package com.astromock.astromock.repository;

import com.astromock.astromock.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}