package ztw.serviceimpl;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ztw.model.Author;
import ztw.model.Book;
import ztw.service.AuthorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private Map<Integer, Author> authorRepo = new HashMap<>();

    @Override
    public List<Author> getAuthors() {
        return this.authorRepo.values().stream().toList();
    }

    @Override
    public Author getAuthor(final int id) {
        if(!this.authorRepo.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Author with given id: %d not found", id));
        }
        return authorRepo.get(id);
    }

    @Override
    public Author addAuthor(final Author author) {
        author.setId(Author.idIterator++);
        this.authorRepo.put(author.getId(), author);
        return author;
    }

    @Override
    public Author deleteAuthor(final int id) {
        final Author authorToDelete = getAuthor(id);
        this.authorRepo.remove(authorToDelete);
        return authorToDelete;
    }

    @Override
    public void updateAuthor(final Author author) {
        getAuthor(author.getId());

        this.authorRepo.put(author.getId(), author);
    }
}
