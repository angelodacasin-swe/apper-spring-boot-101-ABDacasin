package com.apper.theblogservice.service;

import com.apper.theblogservice.exception.BlogNotFoundException;
import com.apper.theblogservice.exception.BloggerNotFoundException;
import com.apper.theblogservice.exception.EmailAlreadyRegisteredException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BlogRepository;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BloggerService {
    private final BloggerRepository bloggerRepository;
    private final BlogRepository blogRepository;

    public BloggerService(BloggerRepository bloggerRepository, BlogRepository blogRepository) {
        this.bloggerRepository = bloggerRepository;
        this.blogRepository = blogRepository;
    }


    // REGISTER USER
    public Blogger registerBlogger(String email, String name, String password)
            throws EmailAlreadyRegisteredException {
        if (bloggerRepository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException("Email is already registered.");
        }

        if (password.length() < 5) {
            throw new IllegalArgumentException("Password must be at least 5 characters.");
        }

        Blogger blogger = new Blogger();
        blogger.setId(UUID.randomUUID().toString());
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);
        blogger.setCreatedAt(LocalDateTime.now());

        return bloggerRepository.save(blogger);
    }

    // GET BLOGGER
    public Blogger getBlogger(String id) throws BloggerNotFoundException {
        return bloggerRepository.findById(id)
                .orElseThrow(() -> new BloggerNotFoundException("Blogger" + id + " not found."));
    }

    // GET ALL BLOGGER
    public List<Blogger> getAllBloggers() {
        return bloggerRepository.findAll();
    }

    // CREATE BLOG
    public Blog createBlog(String title, String body, String bloggerId) throws BloggerNotFoundException {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(bloggerId);

        if (bloggerResult.isEmpty()) {
            throw new BloggerNotFoundException("No Registered Blogger with " + bloggerId);
        }

        Blog blog = new Blog();
        blog.setId(UUID.randomUUID().toString());
        blog.setTitle(title);
        blog.setBody(body);
        blog.setBloggerId(bloggerId);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setLastUpdate(blog.getCreatedAt());

        return blogRepository.save(blog);

    }

    // UPDATE BLOG
    public Blog updateBlog(String blogId, String title, String body)
            throws BlogNotFoundException {

        Blog blog = getBlog(blogId);

        blog.setTitle(title);
        blog.setBody(body);
        blog.setLastUpdate(LocalDateTime.now());

        return blogRepository.save(blog);
    }


      // GET BLOG
        public Blog getBlog(String bloggerId) throws BlogNotFoundException {
        return blogRepository.findById(bloggerId)
                .orElseThrow(() -> new BlogNotFoundException(" Blog " + bloggerId + " not found "));
    }


    // GET ALL BLOGS
        public List<Blog> getAllBlog() { return blogRepository.findAll();
    }


    // GET ALL BLOGS BY BLOGGER
        public List<Blog> getAllBlogsByBloggerId(String bloggerId)
                throws BloggerNotFoundException {
        getBlogger(bloggerId);
        return blogRepository.findAllByBloggerId(bloggerId);
    }
}
