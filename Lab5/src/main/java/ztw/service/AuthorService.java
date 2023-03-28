package ztw.service;

import ztw.model.Author;
import ztw.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> getAuthors();

    Author addAuthor(final Author author);

    Author getAuthor(final int id);

    Author deleteAuthor(final int id);

    void updateAuthor(final Author author);
}
