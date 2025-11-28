package library.model;

import java.io.Serializable;

public class Member implements Serializable {
    private int memberId;
    private String name;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }

    public void borrowBook(Book book) {
        System.out.println(name + " borrowed: " + book.getTitle());
    }

    public void returnBook(Book book) {
        System.out.println(name + " returned: " + book.getTitle());
    }

    @Override
    public String toString() {
        return memberId + " | " + name;
    }
}
