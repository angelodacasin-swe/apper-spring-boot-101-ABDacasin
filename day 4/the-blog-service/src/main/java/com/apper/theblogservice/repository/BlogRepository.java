package com.apper.theblogservice.repository;

import com.apper.theblogservice.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {
    List<Blog> findAllByBloggerId(String bloggerId);
}
