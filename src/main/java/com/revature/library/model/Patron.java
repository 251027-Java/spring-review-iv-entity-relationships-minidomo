package com.revature.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name is required")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    private String email;

    private LocalDateTime memberSince = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patron")
    @ToString.Exclude
    private List<Loan> loans = new ArrayList<>();

    public Patron(String name, String email) {
        this.name = name;
        this.email = email;
    }
}