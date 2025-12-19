package com.revature.library.controller;

import com.revature.library.dto.LoanDto;
import com.revature.library.model.Loan;
import com.revature.library.service.LoanService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {
    private static final Logger LOG = LoggerFactory.getLogger(LoanController.class);
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/loans")
    public ResponseEntity<Loan> createLoan(@RequestBody @Valid LoanDto.Creation data) {
        return ResponseEntity.ok(loanService.createLoan(data.getBookId(), data.getPatronId()));
    }

    @PutMapping("/loans/{id}/return")
    public ResponseEntity<Loan> returnLoan(@PathVariable long id) {
        return ResponseEntity.ok(loanService.returnLoan(id));
    }

    @GetMapping("/loans/active")
    public ResponseEntity<List<Loan>> activeLoan() {
        return ResponseEntity.ok(loanService.getActiveLoans());
    }

    @GetMapping("/patrons/{id}/loans")
    public ResponseEntity<List<Loan>> patronLoans(@PathVariable long id) {
        return ResponseEntity.ok(loanService.getLoansByPatron(id));
    }
}
