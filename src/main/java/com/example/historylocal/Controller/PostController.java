package com.example.historylocal.Controller;


import com.example.historylocal.repository.postRepo;
import com.example.historylocal.entity.Post;

import com.example.historylocal.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")

public class PostController {

    @Autowired
    private postRepo postRepository;
    @Autowired
    private PostService postService;

    @GetMapping
    public Page<Post> getAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @PostMapping
    public Post create(@RequestBody @Valid Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/search")
    public Page<Post> search( @RequestParam String location, Pageable pageable) {
        return postService.search(location, pageable);
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        postRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post postRequest) {
       Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            return null;
        }

        if (postRequest.getTitle()!= null) {
            post.setTitle(postRequest.getTitle());
        }
        if (postRequest.getContent()!= null) {
            post.setContent(postRequest.getContent());
        }
        if (postRequest.getLocation()!= null) {
            post.setLocation(postRequest.getLocation());
        }
        if (postRequest.getEventDate()!= null) {
            post.setEventDate(postRequest.getEventDate());
        }
        return postRepository.save(post);

    }
}
