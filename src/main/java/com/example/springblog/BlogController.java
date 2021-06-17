package com.example.springblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/blog")
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<Blog> getAllStudents() {
        return blogService.getBlogs();
    }

    @PostMapping
    public void postBlog(@RequestBody Blog student) {
        blogService.addBlog(student);
    }

    @DeleteMapping(path="{blogId}")
    public void removeBlog(@PathVariable("blogId") Long blogId) {
        blogService.deleteBlog(blogId);
    }

    @PutMapping(path="{blogId}")
    public void updateBlog(@PathVariable("blogId") Long blogId, @RequestParam(required=false) String title, @RequestParam(required=false) String body, @RequestParam(required=false) @DateTimeFormat(pattern="yyy-MM-dd") LocalDate date) {
        blogService.updateBlog(blogId, title, body, date);
    }
}
