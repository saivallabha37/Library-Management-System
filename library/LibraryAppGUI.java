package library;

import library.model.*;
import library.autosave.*;
import library.ui.LibraryGUI;
import javax.swing.SwingUtilities;

public class LibraryAppGUI {
    public static void main(String[] args) {
        System.out.println("=== Library Management System Starting ===");
        Library library = Library.loadData();
        System.out.println("Total Books: " + library.getBooks().size());
        System.out.println("Total Members: " + library.getMembers().size());
        System.out.println("Total Transactions: " + library.getTransactions().size());
        
        Librarian librarian = new Librarian(1, "Admin");

        // Start auto-save thread
        Thread autoSave = new Thread(new AutoSaveTask(library));
        autoSave.setDaemon(true);
        autoSave.start();
        System.out.println("Auto-save thread started (saves every 10 seconds)");

        // Launch GUI in EDT
        SwingUtilities.invokeLater(() -> {
            LibraryGUI gui = new LibraryGUI(library, librarian);
            System.out.println("GUI loaded successfully");
            
            // Add shutdown hook to save data on exit
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                library.saveData();
                System.out.println("=== Data saved on exit ===");
            }));
        });
    }
}
