package library;

import java.util.*;
import library.model.*;
import library.exception.*;
import library.autosave.*;

public class LibraryApp 
{
    
    // Validation methods
    private static void validateId(int id) throws InvalidIdException 
    {
        if (id <= 0) 
        {
            throw new InvalidIdException("ID must be a positive number!");
        }
    }
    
    private static void validateName(String name) throws InvalidNameException 
    {
        if (name == null || name.trim().isEmpty()) 
        {
            throw new InvalidNameException("Name cannot be empty!");
        }
        if (name.trim().length() < 2) 
        {
            throw new InvalidNameException("Name must be at least 2 characters long!");
        }
        if (!name.matches("^[a-zA-Z\\s]+$")) 
        {
            throw new InvalidNameException("Name can only contain letters and spaces!");
        }
    }
    
    private static void validateTitle(String title) throws InvalidTitleException 
    {
        if (title == null || title.trim().isEmpty()) 
        {
            throw new InvalidTitleException("Title cannot be empty!");
        }
        if (title.trim().length() < 2) 
        {
            throw new InvalidTitleException("Title must be at least 2 characters long!");
        }
    }
    
    private static void validateAuthor(String author) throws InvalidAuthorException 
    {
        if (author == null || author.trim().isEmpty()) 
        {
            throw new InvalidAuthorException("Author name cannot be empty!");
        }
        if (author.trim().length() < 2) 
        {
            throw new InvalidAuthorException("Author name must be at least 2 characters long!");
        }
        if (!author.matches("^[a-zA-Z\\s]+$")) 
        {
            throw new InvalidAuthorException("Author name can only contain letters and spaces!");
        }
    }
    
    public static void main(String[] args) 
    {
        Library library = Library.loadData();
        Librarian librarian = new Librarian(1, "Admin");

        Thread autoSave = new Thread(new AutoSaveTask(library));
        autoSave.start();

        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("\n--- Welcome to the Library Management System ---");
        System.out.println("Library Name: " + library.getName());
        System.out.println("Library Address: " + library.getAddress());

        do 
        {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Remove Book");
            System.out.println("6. Remove Member");
            System.out.println("7. View Books");
            System.out.println("8. View Members");
            System.out.println("9. View Transactions");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            
            try 
            {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1-10.");
                sc.nextLine(); // Clears the invalid input
                choice = 0; 
                continue;
            }

            try 
            {
                switch (choice) 
                {
                    case 1 -> 
                    {
                        try 
                        {
                            System.out.print("Book ID: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            validateId(id);
                            
                            System.out.print("Title: ");
                            String title = sc.nextLine();
                            validateTitle(title);
                            
                            System.out.print("Author: ");
                            String author = sc.nextLine();
                            validateAuthor(author);
                            
                            librarian.addBook(library, new Book(id, title, author));
                            System.out.println("Book added successfully!");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for Book ID.");
                            sc.nextLine(); // Clear the invalid input
                        }
                    }
                    case 2 -> 
                    {
                        try 
                        {
                            System.out.print("Member ID: ");
                            int mid = sc.nextInt();
                            sc.nextLine();
                            validateId(mid);
                            
                            System.out.print("Name: ");
                            String name = sc.nextLine();
                            validateName(name);
                            
                            librarian.registerMember(library, new Member(mid, name));
                            System.out.println("Member registered successfully!");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for Member ID.");
                            sc.nextLine(); 
                        }
                    }
                    case 3 -> 
                    {
                        try 
                        {
                            System.out.print("Book ID: ");
                            int bid = sc.nextInt();
                            validateId(bid);
                            
                            System.out.print("Member ID: ");
                            int mid = sc.nextInt();
                            validateId(mid);
                            
                            library.borrowBook(bid, mid);
                            System.out.println("Book borrowed successfully!");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter valid numbers for Book ID and Member ID.");
                            sc.nextLine(); 
                        }
                    }
                    case 4 -> 
                    {
                        try 
                        {
                            System.out.print("Book ID: ");
                            int bid = sc.nextInt();
                            validateId(bid);
                            
                            System.out.print("Member ID: ");
                            int mid = sc.nextInt();
                            validateId(mid);
                            
                            library.returnBook(bid, mid);
                            System.out.println("Book returned successfully!");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter valid numbers for Book ID and Member ID.");
                            sc.nextLine();
                        } 
                    }
                    case 5 -> 
                    {
                        try 
                        {
                            System.out.print("Book ID to remove: ");
                            int rid = sc.nextInt();
                            validateId(rid);
                            librarian.removeBook(library, rid);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for Book ID.");
                            sc.nextLine();
                        }
                    }
                    case 6 -> 
                    {
                        try 
                        {
                            System.out.print("Member ID to remove: ");
                            int rid = sc.nextInt();
                            validateId(rid);
                            librarian.removeMember(library, rid);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for Member ID.");
                            sc.nextLine();
                        }
                    }
                    case 7 -> library.showAllBooks();
                    case 8 -> library.showAllMembers();
                    case 9 -> library.showAllTransactions();
                    case 10 -> 
                    {
                        autoSave.interrupt();
                        library.saveData();
                        System.out.println("Exiting... All data saved.");
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (BookNotAvailableException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidIdException e) {
                System.out.println("Invalid ID Error: " + e.getMessage());
            } catch (InvalidNameException e) {
                System.out.println("Invalid Name Error: " + e.getMessage());
            } catch (InvalidTitleException e) {
                System.out.println("Invalid Title Error: " + e.getMessage());
            } catch (InvalidAuthorException e) {
                System.out.println("Invalid Author Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        } while (choice != 10);

        sc.close();
    }
}
