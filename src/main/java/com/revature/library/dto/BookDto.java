package com.revature.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public abstract class BookDto {
    private BookDto() {
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Creation {
        @NotBlank(message = "Title is required")
        private String title;

        @NotBlank(message = "Author is required")
        private String author;

        @Pattern(regexp = "^[0-9-]+$", message = "Invalid ISBN format")
        private String isbn;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Own {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Boolean available;
        private LocalDateTime createdAt;
    }
}
