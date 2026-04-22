package com.example.historylocal.dto;

public class PostResponse {
    private Long id;
    private String title;
    private String location;

    public PostResponse(Long id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }
    public Long getId() { return id; }
    public String getLocation() { return location; }
    public String getTitle() { return title; }
}
