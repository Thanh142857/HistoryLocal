package com.example.historylocal.Controller;


import com.example.historylocal.dto.PostResponse;
import com.example.historylocal.dto.PostRequest;
import com.example.historylocal.dto.searchDTO;
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
    public Page<searchDTO> getAll(Pageable pageable) {
        return postService.getAll(pageable);
    }

    @PostMapping
    public PostResponse create(@RequestBody @Valid PostRequest request) {
        return postService.create(request);
    }

    @GetMapping("/search")
    public Page<searchDTO> search(@RequestParam String location, Pageable pageable) {
        return postService.search(location, pageable);
    }

    @GetMapping("/{id}")
    public PostResponse getById(@PathVariable Long id) {
        return postService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        postService.deleteById(id);
        return "Đã xóa thành công";
    }

    @PutMapping("/{id}")
    public PostResponse update(@PathVariable Long id, @RequestBody @Valid PostRequest request) {
        return postService.update(id, request);
    }


}
