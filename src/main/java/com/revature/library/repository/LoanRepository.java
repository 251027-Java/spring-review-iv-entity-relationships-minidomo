package com.revature.library.repository;

import com.revature.library.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByReturnDateIsNull();

    List<Loan> findByPatronId(Long patronId);
}
