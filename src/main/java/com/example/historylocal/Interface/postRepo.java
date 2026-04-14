package com.example.historylocal.Interface;

import com.example.historylocal.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface postRepo extends JpaRepository<Post, Long> {
    List <Post> findByLocation(String location);
}
