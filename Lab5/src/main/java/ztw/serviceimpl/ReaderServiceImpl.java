package ztw.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ztw.controller.ReaderController;
import ztw.model.Author;
import ztw.model.Book;
import ztw.model.Reader;
import ztw.service.BooksService;
import ztw.service.ReaderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReaderServiceImpl implements ReaderService {

    private Map<Integer, Reader> readerRepo = new HashMap<>();

    private BooksService booksService;

    public ReaderServiceImpl(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public List<Reader> getReaders() {
        return this.readerRepo.values().stream().toList();
    }

    @Override
    public Reader getReader(final int id) {
        if(!this.readerRepo.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Reader with given id: %d not found", id));
        }
        return readerRepo.get(id);
    }

    @Override
    public Reader addReader(final Reader reader) {
        reader.setId(Reader.idIterator++);
        this.readerRepo.put(reader.getId(), reader);
        return reader;
    }

    @Override
    public Reader deleteReader(final int id) {
        final Reader readerToDelete = getReader(id);
        this.readerRepo.remove(readerToDelete);
        return readerToDelete;
    }

    @Override
    public void updateReader(final Reader reader) {
        getReader(reader.getId());

        this.readerRepo.put(reader.getId(), reader);
    }

    @Override
    public void borrowBook(final int readerId, final int bookId) {
        final Book book = this.booksService.getBook(bookId);
        final Reader reader = getReader(readerId);

        if(reader.getBooks().contains(book)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reader has already borrowed this book");
        }
        else if(book.getReaderId() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book is not available at the moment");
        }

        reader.getBooks().add(book);
        book.setReaderId(reader.getId());
    }

    @Override
    public void returnBook(final int readerId, final int bookId) {
        final Book book = this.booksService.getBook(bookId);
        final Reader reader = getReader(readerId);

        if(!reader.getBooks().contains(book) || book.getReaderId() != reader.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reader has not borrowed this book");
        }

        reader.getBooks().remove(book);
        book.setReaderId(0);
    }
}
