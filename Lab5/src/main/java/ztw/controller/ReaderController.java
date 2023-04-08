package ztw.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ztw.model.Reader;
import ztw.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/readers")
@CrossOrigin(origins = "http://localhost:3000")
public class ReaderController {

    private ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public ResponseEntity<List<Reader>> getReaders() {
        return ResponseEntity.ok(this.readerService.getReaders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable final int id) {
        return ResponseEntity.ok(this.readerService.getReader(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reader> deleteReader(@PathVariable final int id) {
        return ResponseEntity.ok(this.readerService.deleteReader(id));
    }

    @PostMapping
    public ResponseEntity<Reader> addReader(@RequestBody final Reader reader) {
        return ResponseEntity.status(201).body(this.readerService.addReader(reader));
    }

    @PutMapping
    public ResponseEntity<Void> updateBook(@RequestBody final Reader reader) {
        this.readerService.updateReader(reader);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{readerId}/borrow/{bookId}")
    public ResponseEntity<Void> borrowBook(@PathVariable final int readerId, @PathVariable final int bookId) {
        this.readerService.borrowBook(readerId, bookId);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{readerId}/return/{bookId}")
    public ResponseEntity<Void> returnBook(@PathVariable final int readerId, @PathVariable final int bookId) {
        this.readerService.returnBook(readerId, bookId);
        return ResponseEntity.status(204).build();
    }
}
