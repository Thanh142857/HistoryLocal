package com.example.historylocal.service;


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
                post -> new searchDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getLocation()
                )
        );
    }

    public Page<searchDTO> search(String location, Pageable pageable, LocalDate eventDate) {

        if (location != null && eventDate != null) {
            return postRepository.findByLocationContainingIgnoreCaseAndEventDate(location, eventDate, pageable)
                    .map(post -> new searchDTO(
                            post.getId(),
                            post.getTitle(),
                            post.getLocation()
                    ));
        }
        if (location !=null) {
            return postRepository.findByLocationContainingIgnoreCase(location, pageable).map(post -> new searchDTO(
                    post.getId(),
                    post.getTitle(),
                    post.getLocation()
            ));
        }
        if (eventDate != null) {
            return postRepository.findByEventDate(eventDate, pageable).map(post -> new searchDTO(
                    post.getId(),
                    post.getTitle(),
                    post.getLocation()
            ));

        }
        return postRepository.findAll( pageable)
                .map(post -> new searchDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getLocation()
                ));
    }
    public PostResponse create(PostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setLocation(request.getLocation());
        post.setContent(request.getContent());
        post.setEventDate(request.getEventDate());

        Post saved = postRepository.save(post);

        return new PostResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getLocation(),
                saved.getCreatedAt(),
                saved.getEventDate()
        );
    }

    public PostResponse update( Long id,  PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy bài viết"));

        post.setTitle(request.getTitle());
        post.setLocation(request.getLocation());
        post.setContent(request.getContent());
        post.setEventDate(request.getEventDate());


        Post updated = postRepository.save(post);

        return new PostResponse(
                updated.getId(),
                updated.getTitle(),
                updated.getLocation(),
                updated.getCreatedAt(),
                updated.getEventDate()
        );

    }

    public void deleteById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));
        postRepository.delete(post);
    }


    public PostResponse getById( Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getLocation(),
                post.getCreatedAt(),
                post.getEventDate()
        );
    }
}
