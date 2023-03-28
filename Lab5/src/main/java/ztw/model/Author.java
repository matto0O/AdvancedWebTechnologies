package ztw.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {

    public static int idIterator = 1;
    private int id;
    private String firstName;
    private String lastName;

    public Author(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
