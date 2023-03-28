package ztw;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ztw.model.Author;
import ztw.model.Book;
import ztw.model.Reader;
import ztw.service.AuthorService;
import ztw.service.BooksService;
import ztw.service.ReaderService;

@Component
public class DataInitializer {

    private AuthorService authorService;
    private BooksService booksService;
    private ReaderService readerService;

    public DataInitializer(AuthorService authorService, BooksService booksService, ReaderService readerService) {
        this.authorService = authorService;
        this.booksService = booksService;
        this.readerService = readerService;
    }

    @PostConstruct
    private void init() {
        final Author author1 = new Author("Henryk", "Sienkiewicz");
        final Author author2 = new Author("Stanisław", "Reymont");

        this.authorService.addAuthor(author1);
        this.authorService.addAuthor(author2);

        this.booksService.addBook(new Book("Potop", author1, 936));
        this.booksService.addBook(new Book("Wesele", author2, 150));

        this.readerService.addReader(new Reader("Kacper", "Wojcicki"));
        this.readerService.addReader(new Reader("Mateusz", "Woźniak"));
    }
}
