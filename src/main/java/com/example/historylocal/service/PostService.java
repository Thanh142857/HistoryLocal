package com.example.historylocal.service;


import com.example.historylocal.dto.PostReponse;
import com.example.historylocal.repository.postRepo;
import com.example.historylocal.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final postRepo postRepository;

    public PostService(postRepo postRepository){
        this.postRepository = postRepository;
    }

    public Page<PostReponse> search(String location, Pageable pageable) {
        return postRepository.findByLocationContainingIgnoreCase(location, pageable).map(post -> new PostReponse(
                post.getId(),
                post.getTitle(),
                post.getLocation()
        ));
    }
}
