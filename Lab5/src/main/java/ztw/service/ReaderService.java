package ztw.service;

import ztw.model.Author;
import ztw.model.Reader;

import java.util.List;

public interface ReaderService {

    List<Reader> getReaders();

    Reader addReader(final Reader reader);

    Reader getReader(final int id);

    Reader deleteReader(final int id);

    void updateReader(final Reader reader);

    void borrowBook(final int readerId, final int bookId);

    void returnBook(final int readerId, final int bookId);
}
