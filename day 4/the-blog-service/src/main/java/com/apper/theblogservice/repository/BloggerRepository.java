package com.apper.theblogservice.repository;

import com.apper.theblogservice.model.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, String> {
    boolean existsByEmail(String email);
}
