package ztw.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ztw.model.Author;
import ztw.model.Book;
import ztw.service.AuthorService;
import ztw.service.BooksService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BooksServiceImpl implements BooksService {

    private Map<Integer, Book> booksRepo = new HashMap<>();

    private final AuthorService authorService;

    public BooksServiceImpl(final AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public List<Book> getBooks() {
        return booksRepo.values().stream().toList();
    }

    @Override
    public Book getBook(final int id) {
        if(!this.booksRepo.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Book with given id: %d not found", id));
        }
        return this.booksRepo.get(id);
    }

    @Override
    public Book deleteBook(final int id) {
        final Book bookToDelete = getBook(id);
        this.booksRepo.remove(id);
        return bookToDelete;
    }

    @Override
    public Book addBook(final Book book) {
        final Author author = authorService.getAuthor(book.getAuthor().getId());
        book.setAuthor(author);
        book.setId(Book.idIterator++);
        booksRepo.put(book.getId(), book);
        return book;
    }

    @Override
    public void updateBook(final Book book) {
        validateBookToUpdate(book);
        this.booksRepo.put(book.getId(), book);
    }

    private void validateBookToUpdate(final Book book) {
        getBook(book.getId());
        final Author author = this.authorService.getAuthor(book.getAuthor().getId());
        book.setAuthor(author);
    }
}
