package com.example.historylocal.Controller;


import com.example.historylocal.dto.ApiResponse;
import com.example.historylocal.dto.PostResponse;
import com.example.historylocal.dto.PostRequest;
import com.example.historylocal.dto.SearchDTO;
import com.example.historylocal.entity.Post;
import com.example.historylocal.repository.PostRepository;

import com.example.historylocal.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/posts")

public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;


    @GetMapping
    public Page<SearchDTO> getAll(Pageable pageable) {
        return postService.getAll(pageable);
    }

    @PostMapping
    public ApiResponse<PostResponse> create(@RequestBody @Valid PostRequest request) {
        PostResponse data = postService.create(request);
        return new ApiResponse<>("Tạo mới thành công",data);
    }

    @GetMapping("/search")
    public Page<SearchDTO> search(
            @RequestParam(required = false) String location,
            @RequestParam(required = false)
            @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
            LocalDate eventDate,
            Pageable pageable
    ) {
        return postService.search(location, pageable, eventDate);
    }

    @GetMapping("/{id}")
    public ApiResponse<PostResponse> getById(@PathVariable Long id) {
        PostResponse data = postService.getById(id);
        return new ApiResponse<>("Lấy bài viết thành công",data);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteById(@PathVariable Long id) {
        postService.deleteById(id);
        return new ApiResponse<>("Lấy bài viết thành công", null);
    }

    @PutMapping("/{id}")
    public ApiResponse<PostResponse> update(@PathVariable Long id, @RequestBody @Valid PostRequest request) {
        PostResponse data = postService.update(id, request);
        return new ApiResponse<>("Cập nhật bài viết thành công", data);
    }


}
