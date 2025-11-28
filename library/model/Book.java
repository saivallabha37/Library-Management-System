package library.model;

import java.io.Serializable;
import library.exception.BookNotAvailableException;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    public void borrow() throws BookNotAvailableException {
        if (!isAvailable)
            throw new BookNotAvailableException("Book '" + title + "' is currently unavailable!");
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + (isAvailable ? "Available" : "Borrowed");
    }
}
