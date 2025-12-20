package com.revature.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    @ToString.Exclude
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patron_id", nullable = false)
    @ToString.Exclude
    private Patron patron;

    @Column(nullable = false)
    private LocalDateTime loanDate = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dueDate = LocalDateTime.now().plusWeeks(2);

    private LocalDateTime returnDate;

    public Loan(Book book, Patron patron) {
        this.book = book;
        this.patron = patron;
    }
}