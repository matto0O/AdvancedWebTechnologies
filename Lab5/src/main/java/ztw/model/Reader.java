package ztw.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Reader {

    public static int idIterator = 1;
    private int id;
    private String firstName;
    private String lastName;

    private List<Book> books;

    public Reader(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = new ArrayList<>();
    }
}
