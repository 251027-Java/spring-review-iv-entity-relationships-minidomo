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

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto.Own> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    public BookDto.Own findById(Long id) {
        return bookRepository.findById(id).map(bookMapper::toDto).orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));
    }

    public BookDto.Own addBook(BookDto.Creation dto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(dto)));
    }

    @Transactional
    public BookDto.Own checkoutBook(Long bookId) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + " not found"));

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book with id: " + bookId + " is not available");
        }

        book.setAvailable(false);

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    public BookDto.Own returnBook(Long bookId) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + " not found"));

        book.setAvailable(true);

        return bookMapper.toDto(bookRepository.save(book));
    }
}
