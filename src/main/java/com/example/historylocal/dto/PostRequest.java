package com.example.historylocal.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class PostRequest {
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;

    @NotBlank(message = "Nội dung không được để trống")
    private String content;

    @NotBlank(message = "Vị trí không được để trống")
    private String location;

    @NotNull(message = "Ngày không được để trống")
    @Past(message = "Phải là ngày trong quá khứ")
    private LocalDate eventDate;


    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public LocalDate getEventDate() { return eventDate; }
    public String getContent() { return content; }
}
