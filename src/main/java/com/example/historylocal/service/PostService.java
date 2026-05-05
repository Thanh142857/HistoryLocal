package com.example.historylocal.service;


import com.example.historylocal.dto.PostMapper;
import com.example.historylocal.dto.PostResponse;
import com.example.historylocal.dto.PostRequest;
import com.example.historylocal.dto.searchDTO;
import com.example.historylocal.entity.Post;
import com.example.historylocal.repository.postRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostService {
    private final postRepo postRepository;

    public PostService(postRepo postRepository){
        this.postRepository = postRepository;
    }
    public Page<searchDTO> getAll(Pageable pageable) {
        return postRepository.findAll(pageable).map(
                PostMapper ::toSearchDTO
        );
    }

    public Page<searchDTO> search(String location, Pageable pageable, LocalDate eventDate) {

        if (location != null && eventDate != null) {
            return postRepository.findByLocationContainingIgnoreCaseAndEventDate(location, eventDate, pageable)
                    .map(PostMapper ::toSearchDTO);
        }
        if (location !=null) {
            return postRepository.findByLocationContainingIgnoreCase(location, pageable).map(PostMapper ::toSearchDTO);
        }
        if (eventDate != null) {
            return postRepository.findByEventDate(eventDate, pageable).map(PostMapper ::toSearchDTO);

        }
        return postRepository.findAll( pageable)
                .map(PostMapper ::toSearchDTO);
    }
    public PostResponse create(PostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setLocation(request.getLocation());
        post.setContent(request.getContent());
        post.setEventDate(request.getEventDate());

        Post saved = postRepository.save(post);

        return PostMapper.toPostResponse(saved);
    }

    public PostResponse update( Long id,  PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy bài viết"));

        post.setTitle(request.getTitle());
        post.setLocation(request.getLocation());
        post.setContent(request.getContent());
        post.setEventDate(request.getEventDate());


        Post updated = postRepository.save(post);

        return PostMapper.toPostResponse(updated);

    }

    public void deleteById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));
        postRepository.delete(post);
    }


    public PostResponse getById( Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));
        return PostMapper.toPostResponse(post);
    }
}
