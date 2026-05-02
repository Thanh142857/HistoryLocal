package com.example.historylocal.dto;



public class searchDTO {
    private Long id;
    private String title;
    private String location;

    public searchDTO(Long id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    public Long getId() { return id; }
    public String getLocation() { return location; }
    public String getTitle() { return title; }
}
