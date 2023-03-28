package ztw.service;

import ztw.model.Book;

import java.util.List;

public interface BooksService {

    List<Book> getBooks();

    Book getBook(final int id);

    Book deleteBook(final int id);

    Book addBook(final Book book);

    void updateBook(final Book book);
}
