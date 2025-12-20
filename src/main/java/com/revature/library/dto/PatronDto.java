package com.revature.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public abstract class PatronDto {
    private PatronDto() {
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Creation {
        @NotBlank
        private String name;

        @Email(regexp = "^.*@.*$")
        private String email;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Own {
        private Long id;
        private String name;
        private String email;
        private LocalDate memberSince;
    }
}
