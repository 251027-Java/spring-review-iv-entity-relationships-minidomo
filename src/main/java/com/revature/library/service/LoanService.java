package com.revature.library.service;

import com.revature.library.exception.LoanNotFoundException;
import com.revature.library.model.Loan;
import com.revature.library.repository.BookRepository;
import com.revature.library.repository.LoanRepository;
import com.revature.library.repository.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Transactional
    public Loan createLoan(Long bookId, Long patronId) {
        var book = bookRepository.getReferenceById(bookId);
        var patron = patronRepository.getReferenceById(patronId);
        return loanRepository.save(new Loan(book, patron));
    }

    public Loan returnLoan(Long loanId) {
        return loanRepository.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan with id " + loanId + " not found"));
    }

    public List<Loan> getActiveLoans() {
        return loanRepository.findByReturnDateIsNull();
    }

    public List<Loan> getLoansByPatron(Long patronId) {
        return loanRepository.findByPatronId(patronId);
    }
}
