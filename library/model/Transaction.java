package library.model;
import java.io.Serializable;
public class Transaction implements Serializable {
    private int transactionId;
    private Book book;
    private Member member;
    private String borrowDate;
    private String returnDate;
    public Transaction(int id, Book book, Member member, String borrowDate)
    
    {
        this.transactionId = id;
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
    }
    public void closeTransaction(String returnDate)
    {
        this.returnDate = returnDate;
    }
    @Override
    public String toString() {
        return transactionId + " | " + book.getTitle() + " | " + member.getName() + " | "
                + borrowDate + " | " + (returnDate == null ? "Not Returned" : returnDate);
    }
}
