package com.example.springblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    public void addBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public void deleteBlog(Long blogId) {
        if(!blogRepository.existsById(blogId)) {
            throw new IllegalStateException("Blog post not found");
        }
        blogRepository.deleteById(blogId);
    }

    @Transactional
    public void updateBlog(Long blogId, String title, String body, LocalDate date) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(() ->
                new IllegalStateException("Student not found"));

        if (title != null && !blog.getTitle().equals(title)) {
            blog.setTitle(title);
        }

        if(date != null && !blog.getDate().equals(date)) {
            blog.setDate(date);
        }

        if(body != null && !blog.getBody().equals(body)) {
            blog.setBody(body);
        }
    }
}
