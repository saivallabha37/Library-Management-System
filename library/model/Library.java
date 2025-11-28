package library.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import library.exception.BookNotAvailableException;

public class Library implements Serializable 
{
    private String name;
    private String address;
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public Library(String name, String address) 
    {
        this.name = name;
        this.address = address;
    }

    public String getName() 
    {
        return name;
    }
    
    public String getAddress() 
    {
        return address;
    }

    
    public void addBook(Book book) 
    {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void removeBook(int id) 
    {
        books.removeIf(b -> b.getId() == id);
        System.out.println("Book removed successfully.");
    }

    public void registerMember(Member member) 
    {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    public void removeMember(int id) 
    {
        members.removeIf(m -> m.getMemberId() == id);
        System.out.println("Member removed successfully.");
    }

    
    public Book findBookById(int id) 
    {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public Member findMemberById(int id) 
    {
        return members.stream().filter(m -> m.getMemberId() == id).findFirst().orElse(null);
    }

    
    public void borrowBook(int bookId, int memberId) throws BookNotAvailableException 
    {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book == null || member == null)
            throw new IllegalArgumentException("Invalid Book or Member ID!");

        book.borrow();
        member.borrowBook(book);

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        transactions.add(new Transaction(transactions.size() + 1, book, member, date));
    }

    public void returnBook(int bookId, int memberId) 
    {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book == null || member == null) 
        {
            System.out.println("Invalid IDs!");
            return;
        }

        book.returnBook();
        member.returnBook(book);

        for (Transaction t : transactions) 
        {
            if (t.toString().contains(book.getTitle()) && t.toString().contains(member.getName())
                    && t.toString().contains("Not Returned")) 
            {
                String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                t.closeTransaction(date);
                break;
            }
        }
    }

    
    public void showAllBooks() 
    {
        if (books.isEmpty()) System.out.println("No books available.");
        else books.forEach(System.out::println);
    }

    public void showAllMembers() 
    {
        if (members.isEmpty()) System.out.println("No members found.");
        else members.forEach(System.out::println);
    }

    public void showAllTransactions() 
    {
        if (transactions.isEmpty()) System.out.println("No transactions found.");
        else transactions.forEach(System.out::println);
    }

    // Getter methods for GUI
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
   
    public void saveData() 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("library.dat"))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully to library.dat");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static Library loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("library.dat"))) {
            Library library = (Library) ois.readObject();
            System.out.println("Data loaded successfully from library.dat");
            return library;
        } catch (Exception e) {
            System.out.println("No previous data found. Creating library with sample data...");
            Library library = new Library("Dr. Sarvepalli Radhakrishnan Library", "Vasavi College of Engineering");
            
            // Add 20 sample books
            library.addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
            library.addBook(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
            library.addBook(new Book(3, "1984", "George Orwell"));
            library.addBook(new Book(4, "Pride and Prejudice", "Jane Austen"));
            library.addBook(new Book(5, "The Catcher in the Rye", "J.D. Salinger"));
            library.addBook(new Book(6, "Jane Eyre", "Charlotte Bronte"));
            library.addBook(new Book(7, "Wuthering Heights", "Emily Bronte"));
            library.addBook(new Book(8, "The Hobbit", "J.R.R. Tolkien"));
            library.addBook(new Book(9, "Dune", "Frank Herbert"));
            library.addBook(new Book(10, "Brave New World", "Aldous Huxley"));
            library.addBook(new Book(11, "The Lord of the Rings", "J.R.R. Tolkien"));
            library.addBook(new Book(12, "Moby Dick", "Herman Melville"));
            library.addBook(new Book(13, "Crime and Punishment", "Fyodor Dostoevsky"));
            library.addBook(new Book(14, "The Odyssey", "Homer"));
            library.addBook(new Book(15, "The Iliad", "Homer"));
            library.addBook(new Book(16, "Hamlet", "William Shakespeare"));
            library.addBook(new Book(17, "Macbeth", "William Shakespeare"));
            library.addBook(new Book(18, "A Tale of Two Cities", "Charles Dickens"));
            library.addBook(new Book(19, "Great Expectations", "Charles Dickens"));
            library.addBook(new Book(20, "Oliver Twist", "Charles Dickens"));
            
            // Add 10 sample members
            library.registerMember(new Member(101, "John Smith"));
            library.registerMember(new Member(102, "Emma Johnson"));
            library.registerMember(new Member(103, "Michael Brown"));
            library.registerMember(new Member(104, "Sarah Davis"));
            library.registerMember(new Member(105, "David Wilson"));
            library.registerMember(new Member(106, "Jennifer Martinez"));
            library.registerMember(new Member(107, "Robert Taylor"));
            library.registerMember(new Member(108, "Lisa Anderson"));
            library.registerMember(new Member(109, "Christopher White"));
            library.registerMember(new Member(110, "Michelle Thomas"));
            
            // Add 30 sample transactions
            try {
                library.borrowBook(1, 101);
                library.borrowBook(2, 102);
                library.borrowBook(3, 103);
                library.borrowBook(4, 104);
                library.borrowBook(5, 105);
                library.borrowBook(6, 106);
                library.borrowBook(7, 107);
                library.borrowBook(8, 108);
                library.borrowBook(9, 109);
                library.borrowBook(10, 110);
                library.borrowBook(11, 101);
                library.borrowBook(12, 102);
                library.borrowBook(13, 103);
                library.borrowBook(14, 104);
                library.borrowBook(15, 105);
                library.borrowBook(16, 106);
                library.borrowBook(17, 107);
                library.borrowBook(18, 108);
                library.borrowBook(19, 109);
                library.borrowBook(20, 110);
                library.borrowBook(1, 102);
                library.borrowBook(2, 103);
                library.borrowBook(3, 104);
                library.borrowBook(4, 105);
                library.borrowBook(5, 106);
                library.borrowBook(6, 107);
                library.borrowBook(7, 108);
                library.borrowBook(8, 109);
                library.borrowBook(9, 110);
                library.borrowBook(10, 101);
            } catch (Exception ex) {
                // Ignore exceptions during initial setup
            }
            
            return library;
        }
    }
}
