package com.revature.library.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public abstract class LoanDto {

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Creation {
        @PositiveOrZero
        @NotNull
        private Long bookId;

        @PositiveOrZero
        @NotNull
        private Long patronId;
    }
}
