package com.example.historylocal.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostResponse {
    private Long id;
    private String title;
    private String location;
    private LocalDateTime createdAt;
    private LocalDate eventDate;

    public PostResponse(Long id, String title, String location, LocalDateTime createdAt, LocalDate eventDate) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.createdAt = createdAt;
        this.eventDate = eventDate;
    }
    public Long getId() { return id; }
    public String getLocation() { return location; }
    public String getTitle() { return title; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDate getEventDate() { return eventDate; }
}
