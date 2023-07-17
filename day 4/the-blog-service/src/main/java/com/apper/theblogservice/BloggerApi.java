package com.apper.theblogservice;

import com.apper.theblogservice.exception.*;
import com.apper.theblogservice.payload.*;
import org.springframework.web.bind.annotation.*;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.service.BloggerService;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

@RestController
public class BloggerApi {

    private final BloggerService bloggerService;

    public BloggerApi(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    // REGISTER BLOGGER
    @PostMapping(path="/blogger")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse registerBlogger(@RequestBody @Valid CreateBloggerRequest request)
            throws EmailAlreadyRegisteredException {

        Blogger registeredBlogger = bloggerService.registerBlogger
                (request.getEmail(),
                request.getName(),
                request.getPassword());

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(registeredBlogger.getId());
        response.setCreatedAt(registeredBlogger.getCreatedAt());

        return response;
    }

    // GET BLOGGER
    @GetMapping("/blogger/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BloggerResponse getBlogger(@PathVariable String id)
            throws BloggerNotFoundException {
        Blogger blogger = bloggerService.getBlogger(id);

        BloggerResponse bloggerResponse = new BloggerResponse();
        bloggerResponse.setId(blogger.getId());
        bloggerResponse.setName(blogger.getName());
        bloggerResponse.setEmail(blogger.getEmail());
        bloggerResponse.setCreatedAt(blogger.getCreatedAt());

        return bloggerResponse;
    }

    // GET ALL BLOGGER/S
    @GetMapping("/blogger")
    @ResponseStatus(HttpStatus.OK)
    public List<BloggerResponse> getAllBloggers() {
        List<BloggerResponse> bloggerResponseList = bloggerService.getAllBloggers().stream()
                .map(blogger -> {
                    BloggerResponse bloggerResponse = new BloggerResponse();
                    bloggerResponse.setId(blogger.getId());
                    bloggerResponse.setName(blogger.getName());
                    bloggerResponse.setEmail(blogger.getEmail());
                    bloggerResponse.setCreatedAt(blogger.getCreatedAt());
                    return bloggerResponse;
                })
                .collect(Collectors.toList());
        return bloggerResponseList;
    }

    // CREATE BLOG
    @PostMapping("/blog")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request)
            throws BloggerNotFoundException {

        Blog createdBlog = bloggerService.createBlog(request.getTitle(), request.getBody(), request.getBloggerId());

        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(createdBlog.getId());
        response.setBloggerId(createdBlog.getBloggerId());
        response.setCreatedAt(createdBlog.getCreatedAt());
        response.setLastUpdated(createdBlog.getLastUpdate());

        return response;
    }

    // GET BLOG
    @PutMapping("/blog/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CreateBlogResponse updateBlog(@PathVariable String blogId, @RequestBody @Valid UpdateBlogRequest request)
            throws BlogNotFoundException {

        Blog updatedBlog = bloggerService.updateBlog(blogId, request.getTitle(), request.getBody());

        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(updatedBlog.getId());
        response.setBloggerId(updatedBlog.getBloggerId());
        response.setCreatedAt(updatedBlog.getCreatedAt());
        response.setLastUpdated(updatedBlog.getLastUpdate());

        return response;
    }

    // GET BLOG
    @GetMapping("/blog/{blogId}")
    @ResponseStatus(HttpStatus.OK)
    public BlogResponse getBlog(@PathVariable String blogId)
            throws BlogNotFoundException {
        Blog blog = bloggerService.getBlog(blogId);

        BlogResponse blogResponse = new BlogResponse();
        blogResponse.setBloggerId(blog.getBloggerId());
        blogResponse.setTitle(blog.getTitle());
        blogResponse.setBody(blog.getBody());
        blogResponse.setCreatedAt(blog.getCreatedAt());
        blogResponse.setLastUpdated(blog.getLastUpdate());

        return blogResponse;
    }

    // GET ALL BLOGS
    @GetMapping("/blog")
    @ResponseStatus(HttpStatus.OK)
    public List<BlogResponse> getAllBlogs() {
        List<Blog> blogs = bloggerService.getAllBlog();
        List<BlogResponse> blogResponseList = blogs.stream()
                .map(blog -> {
                    BlogResponse blogResponse = new BlogResponse();
                    blogResponse.setBloggerId(blog.getBloggerId());
                    blogResponse.setTitle(blog.getTitle());
                    blogResponse.setBody(blog.getBody());
                    blogResponse.setCreatedAt(blog.getCreatedAt());
                    blogResponse.setLastUpdated(blog.getLastUpdate());

                    return blogResponse;

                })

                .collect(Collectors.toList());

        return blogResponseList;
    }

    // GET ALL BLOGS BY BLOGGER
    @GetMapping("/blog/blogger/{bloggerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BlogResponse> getAllBlogsByBloggerId(@PathVariable String bloggerId)
            throws BloggerNotFoundException {
        List<BlogResponse> blogResponseList = bloggerService.getAllBlogsByBloggerId(bloggerId).stream()
                .map(blog -> {
                    BlogResponse blogResponse = new BlogResponse();
                    blogResponse.setBloggerId(blog.getBloggerId());
                    blogResponse.setTitle(blog.getTitle());
                    blogResponse.setBody(blog.getBody());
                    blogResponse.setCreatedAt(blog.getCreatedAt());
                    blogResponse.setLastUpdated(blog.getLastUpdate());

                    return blogResponse;

                })

                .collect(Collectors.toList());

        return blogResponseList;
    }
}
