package com.revature.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime memberSince = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patron")
    @ToString.Exclude
    private List<Loan> loans = new ArrayList<>();

    public Patron(String name, String email) {
        this.name = name;
        this.email = email;
    }
}