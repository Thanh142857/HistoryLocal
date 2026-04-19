package com.example.historylocal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;


    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Nội dung không được để trống")
    private String content;

    @NotBlank(message = "Vị trí không được để trống")
    private String location;

    @Past(message = "Phải là ngày trong quá khứ")
    private LocalDate eventDate;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Post(Long id, String title, String content, String location, LocalDate eventDate, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.location = location;
        this.eventDate = eventDate;
        this.createdAt = createdAt;



    }
}
