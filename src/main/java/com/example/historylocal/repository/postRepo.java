package com.example.historylocal.repository;

import com.example.historylocal.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface postRepo extends JpaRepository<Post, Long> {
    Page<Post> findByLocationContainingIgnoreCase(String location, Pageable pageable);
    Page<Post> findByLocationContainingIgnoreCaseAndEventDate(String location, LocalDate eventDate, Pageable pageable);
    Page<Post> findByEventDate(LocalDate eventDate, Pageable pageable);
}
