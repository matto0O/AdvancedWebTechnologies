package ztw.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    public static int idIterator = 1;
    private int id;
    private String title;
    private Author author;
    private int pages;

    private int readerId;

    public Book(final String title, final Author author, final int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
}
