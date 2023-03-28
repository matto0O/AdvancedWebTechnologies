package ztw.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ztw.model.Author;
import ztw.model.Book;
import ztw.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {
        return ResponseEntity.ok(this.authorService.getAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthors(@PathVariable final int id) {
        return ResponseEntity.ok(this.authorService.getAuthor(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable final int id) {
        return ResponseEntity.ok(this.authorService.deleteAuthor(id));
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody final Author author) {
        return ResponseEntity.status(201).body(this.authorService.addAuthor(author));
    }

    @PutMapping
    public ResponseEntity<Void> updateBook(@RequestBody final Author author) {
        this.authorService.updateAuthor(author);
        return ResponseEntity.status(204).build();
    }
}
