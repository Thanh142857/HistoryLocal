package com.example.historylocal.service;


import com.example.historylocal.dto.PostResponse;
import com.example.historylocal.dto.PostRequest;
import com.example.historylocal.entity.Post;
import com.example.historylocal.repository.postRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PostService {
    private final postRepo postRepository;

    public PostService(postRepo postRepository){
        this.postRepository = postRepository;
    }

    public Page<PostResponse> search(String location, Pageable pageable) {
        return postRepository.findByLocationContainingIgnoreCase(location, pageable).map(post -> new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getLocation()
        ));
    }
    public Post create(PostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setLocation(request.getLocation());
        post.setContent(request.getContent());
        post.setEventDate(request.getEventDate());
        return postRepository.save(post);
    };

    public Post update( Long id,  PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Khng tìm thấy bài viết"));

        post.setTitle(request.getTitle());
        post.setLocation(request.getLocation());
        post.setContent(request.getContent());
        post.setEventDate(request.getEventDate());

        return postRepository.save(post);

    }

    public void deleteById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));

        postRepository.delete(post);
    }
}
