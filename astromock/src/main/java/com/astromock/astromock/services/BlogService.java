package com.astromock.astromock.services;


import com.astromock.astromock.model.Blog;
import com.astromock.astromock.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Blog createBlog(Blog blog) {
        blog.setCreatedAt(LocalDateTime.now());
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public String deleteBlog(Long id) {

        if (!blogRepository.existsById(id)) {
            return "Blog not found with id: " + id;
        }

        blogRepository.deleteById(id);
        return "Blog deleted successfully!";
    }
}