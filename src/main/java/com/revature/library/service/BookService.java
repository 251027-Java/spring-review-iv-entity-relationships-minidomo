package com.revature.library.service;

import com.revature.library.dto.BookDto;
import com.revature.library.exception.BookNotAvailableException;
import com.revature.library.exception.BookNotFoundException;
import com.revature.library.mapper.BookMapper;
import com.revature.library.model.Book;
import com.revature.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(BookDto.Creation dto) {
        return bookRepository.save(bookMapper.toEntity(dto));
    }

    @Transactional
    public Book checkoutBook(Long bookId) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + " not found"));

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book with id: " + bookId + " is not available");
        }

        book.setAvailable(false);

        return bookRepository.save(book);
    }

    @Transactional
    public Book returnBook(Long bookId) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + " not found"));

        book.setAvailable(true);

        return bookRepository.save(book);
    }
}
