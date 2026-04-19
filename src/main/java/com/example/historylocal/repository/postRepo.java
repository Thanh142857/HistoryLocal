package com.example.historylocal.repository;

import com.example.historylocal.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface postRepo extends JpaRepository<Post, Long> {
    Page<Post> findByLocationContainingIgnoreCase(String location, Pageable pageable);
}
