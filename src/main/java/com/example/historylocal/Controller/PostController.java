package com.example.historylocal.Controller;


import com.example.historylocal.Interface.postRepo;
import com.example.historylocal.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")

public class PostController {

    @Autowired
    private postRepo postRepository;

    @GetMapping
    public List<Post> getAll() {
        return postRepository.findAll();
    };
    @PostMapping
    public Post create(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/search")
    public List<Post> search(@RequestParam String location) {
        return postRepository.findByLocation(location);
    }

}
