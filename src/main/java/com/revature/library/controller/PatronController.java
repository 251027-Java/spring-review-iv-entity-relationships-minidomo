package com.revature.library.controller;

import com.revature.library.dto.PatronDto;
import com.revature.library.model.Patron;
import com.revature.library.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @PostMapping
    public ResponseEntity<PatronDto.Own> createPatron(@RequestBody @Valid PatronDto.Creation dto) {
        return ResponseEntity.ok(patronService.createPatron(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDto.Own> findById(@PathVariable Long id) {
        return ResponseEntity.ok(patronService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PatronDto.Own>> findAllPatrons() {
        return ResponseEntity.ok(patronService.findAll());
    }
}
