package com.revature.library.controller;

import com.revature.library.dto.BookDto;
import com.revature.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto.Own>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto.Own> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookDto.Own> addBook(@RequestBody @Valid BookDto.Creation dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(dto));
    }

    @PutMapping("/{id}/checkout")
    public ResponseEntity<BookDto.Own> checkoutBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.checkoutBook(id));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BookDto.Own> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.returnBook(id));
    }
}
