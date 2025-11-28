package library.model;

import java.io.Serializable;

public class Librarian implements Serializable {
    private int employeeId;
    private String name;

    public Librarian(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
    }

    public void removeBook(Library library, int bookId) {
        library.removeBook(bookId);
    }

    public void registerMember(Library library, Member member) {
        library.registerMember(member);
    }

    public void removeMember(Library library, int memberId) {
        library.removeMember(memberId);
    }
}
