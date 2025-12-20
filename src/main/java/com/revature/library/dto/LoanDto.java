package com.revature.library.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public abstract class LoanDto {
    private LoanDto() {
    }

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

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Own {
        private Long id;
        private BookDto.Own book;
        private PatronDto.Own patron;
        private LocalDateTime loanDate;
        private LocalDateTime dueDate;
        private LocalDateTime returnDate;
    }
}
