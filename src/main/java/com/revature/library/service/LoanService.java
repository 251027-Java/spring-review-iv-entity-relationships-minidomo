package com.revature.library.service;

import com.revature.library.dto.LoanDto;
import com.revature.library.exception.LoanNotFoundException;
import com.revature.library.mapper.LoanMapper;
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
    private final LoanMapper loanMapper;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, PatronRepository patronRepository, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.loanMapper = loanMapper;
    }

    @Transactional
    public LoanDto.Own createLoan(Long bookId, Long patronId) {
        var book = bookRepository.getReferenceById(bookId);
        var patron = patronRepository.getReferenceById(patronId);
        return loanMapper.toDto(loanRepository.save(new Loan(book, patron)));
    }

    public LoanDto.Own returnLoan(Long loanId) {
        return loanRepository.findById(loanId).map(loanMapper::toDto).orElseThrow(() -> new LoanNotFoundException("Loan with id " + loanId + " not found"));
    }

    public List<LoanDto.Own> getActiveLoans() {
        return loanRepository.findByReturnDateIsNull().stream().map(loanMapper::toDto).toList();
    }

    public List<LoanDto.Own> getLoansByPatron(Long patronId) {
        return loanRepository.findByPatronId(patronId).stream().map(loanMapper::toDto).toList();
    }
}
