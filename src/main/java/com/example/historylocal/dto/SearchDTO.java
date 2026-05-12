package com.example.historylocal.dto;



public class SearchDTO {
    private Long id;
    private String title;
    private String location;

    public SearchDTO(Long id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    public Long getId() { return id; }
    public String getLocation() { return location; }
    public String getTitle() { return title; }
}
