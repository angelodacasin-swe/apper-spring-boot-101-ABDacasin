package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateBloggerResponse {
        private String id;

        @JsonProperty("created_at")
        private LocalDateTime createdAt;

    }