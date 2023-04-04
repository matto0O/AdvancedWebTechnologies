package ztw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ztw.model.Book;
import ztw.service.BooksService;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(this.booksService.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable final int id) {
        return ResponseEntity.ok(this.booksService.getBook(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable final int id) {
        return ResponseEntity.ok(this.booksService.deleteBook(id));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody final Book book) {
        return ResponseEntity.status(201).body(this.booksService.addBook(book));
    }

    @PutMapping
    public ResponseEntity<Void> updateBook(@RequestBody final Book book) {
        this.booksService.updateBook(book);
        return ResponseEntity.status(204).build();
    }
}
