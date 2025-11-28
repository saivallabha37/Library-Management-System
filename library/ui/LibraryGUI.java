package library.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import library.model.*;
import library.exception.*;

public class LibraryGUI extends JFrame {
    private Library library;
    private Librarian librarian;
    
    private JTabbedPane tabbedPane;
    private BookPanel bookPanel;
    private MemberPanel memberPanel;
    private TransactionPanel transactionPanel;

    public LibraryGUI(Library library, Librarian librarian) {
        this.library = library;
        this.librarian = librarian;
        
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Create header panel
        JPanel headerPanel = createHeaderPanel();
        
        // Create tabbed pane with three panels
        tabbedPane = new JTabbedPane();
        bookPanel = new BookPanel(library, librarian);
        memberPanel = new MemberPanel(library, librarian);
        transactionPanel = new TransactionPanel(library);
        
        tabbedPane.addTab("Books", new ImageIcon(), bookPanel, "Manage Books");
        tabbedPane.addTab("Members", new ImageIcon(), memberPanel, "Manage Members");
        tabbedPane.addTab("Transactions", new ImageIcon(), transactionPanel, "View Transactions");
        
        // Set layout
        setLayout(new BorderLayout(10, 10));
        add(headerPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 70, 140));
        headerPanel.setPreferredSize(new Dimension(0, 110));
        headerPanel.setLayout(new BorderLayout(20, 0));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        
        // Title
        JLabel titleLabel = new JLabel(library.getName());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Address
        JLabel addressLabel = new JLabel("Location: " + library.getAddress());
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        addressLabel.setForeground(new Color(220, 220, 220));
        addressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(addressLabel);
        
        headerPanel.add(textPanel, BorderLayout.WEST);
        
        return headerPanel;
    }
}

// ==================== BOOK PANEL ====================
class BookPanel extends JPanel {
    private Library library;
    private Librarian librarian;
    private DefaultTableModel tableModel;
    private JTable booksTable;
    
    public BookPanel(Library library, Librarian librarian) {
        this.library = library;
        this.librarian = librarian;
        
        setLayout(new BorderLayout(15, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Left panel with input form
        JPanel leftPanel = createLeftPanel();
        add(leftPanel, BorderLayout.WEST);
        
        // Right panel with table
        JPanel rightPanel = createTablePanel();
        add(rightPanel, BorderLayout.CENTER);
    }
    
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(10, 10));
        leftPanel.setPreferredSize(new Dimension(350, 0));
        
        // Input panel
        JPanel inputPanel = createInputPanel();
        leftPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Button panel
        JPanel buttonPanel = createButtonPanel();
        leftPanel.add(buttonPanel, BorderLayout.CENTER);
        
        return leftPanel;
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 15, 15));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Add New Book"),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setBackground(new Color(240, 240, 240));
        panel.setPreferredSize(new Dimension(350, 180));
        
        JLabel idLabel = new JLabel("Book ID:", JLabel.CENTER);
        idLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField idField = new JTextField(25);
        idField.setFont(new Font("Arial", Font.PLAIN, 13));
        idField.setMargin(new Insets(10, 10, 10, 10));
        idField.setPreferredSize(new Dimension(200, 50));
        
        JLabel titleLabel = new JLabel("Title:", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField titleField = new JTextField(25);
        titleField.setFont(new Font("Arial", Font.PLAIN, 13));
        titleField.setMargin(new Insets(10, 10, 10, 10));
        titleField.setPreferredSize(new Dimension(200, 50));
        
        JLabel authorLabel = new JLabel("Author:", JLabel.CENTER);
        authorLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField authorField = new JTextField(25);
        authorField.setFont(new Font("Arial", Font.PLAIN, 13));
        authorField.setMargin(new Insets(10, 10, 10, 10));
        authorField.setPreferredSize(new Dimension(200, 50));
        
        panel.add(idLabel);
        panel.add(idField);
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(authorLabel);
        panel.add(authorField);
        
        // Store references for access by buttons
        panel.putClientProperty("idField", idField);
        panel.putClientProperty("titleField", titleField);
        panel.putClientProperty("authorField", authorField);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 8, 8));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Actions"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(new Color(240, 240, 240));
        panel.setPreferredSize(new Dimension(350, 160));
        
        JButton addButton = new JButton("Add Book");
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 11));
        
        JButton removeButton = new JButton("Remove Selected");
        removeButton.setBackground(new Color(220, 20, 60));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(new Font("Arial", Font.BOLD, 11));
        
        JButton clearButton = new JButton("Clear Fields");
        clearButton.setBackground(new Color(150, 150, 150));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 11));
        
        addButton.addActionListener(e -> handleAddBook());
        removeButton.addActionListener(e -> handleRemoveBook());
        clearButton.addActionListener(e -> handleClearFields());
        
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(clearButton);
        
        return panel;
    }
    
    private void handleAddBook() {
        JPanel leftPanel = (JPanel) getComponent(0);
        JPanel inputPanel = (JPanel) ((BorderLayout) leftPanel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        
        JTextField idField = (JTextField) inputPanel.getComponent(1);
        JTextField titleField = (JTextField) inputPanel.getComponent(3);
        JTextField authorField = (JTextField) inputPanel.getComponent(5);
        
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            
            if (id <= 0) throw new InvalidIdException("ID must be positive!");
            if (title.isEmpty()) throw new InvalidTitleException("Title cannot be empty!");
            if (author.isEmpty()) throw new InvalidAuthorException("Author cannot be empty!");
            
            librarian.addBook(library, new Book(id, title, author));
            JOptionPane.showMessageDialog(this, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
            idField.setText("");
            titleField.setText("");
            authorField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Book ID must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleRemoveBook() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow != -1) {
            int bookId = (int) tableModel.getValueAt(selectedRow, 0);
            librarian.removeBook(library, bookId);
            JOptionPane.showMessageDialog(this, "Book removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a book to remove!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void handleClearFields() {
        JPanel leftPanel = (JPanel) getComponent(0);
        JPanel inputPanel = (JPanel) ((BorderLayout) leftPanel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        
        JTextField idField = (JTextField) inputPanel.getComponent(1);
        JTextField titleField = (JTextField) inputPanel.getComponent(3);
        JTextField authorField = (JTextField) inputPanel.getComponent(5);
        
        idField.setText("");
        titleField.setText("");
        authorField.setText("");
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Books List"));
        
        // Create table
        tableModel = new DefaultTableModel(
            new String[]{"Book ID", "Title", "Author", "Status"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        booksTable = new JTable(tableModel);
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        booksTable.setRowHeight(25);
        booksTable.getTableHeader().setBackground(new Color(70, 130, 180));
        booksTable.getTableHeader().setForeground(Color.WHITE);
        booksTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(booksTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        refreshTable();
        return panel;
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Book book : library.getBooks()) {
            tableModel.addRow(new Object[]{
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.isAvailable() ? "Available" : "Borrowed"
            });
        }
    }
}

// ==================== MEMBER PANEL ====================
class MemberPanel extends JPanel {
    private Library library;
    private Librarian librarian;
    private DefaultTableModel tableModel;
    private JTable membersTable;
    
    public MemberPanel(Library library, Librarian librarian) {
        this.library = library;
        this.librarian = librarian;
        
        setLayout(new BorderLayout(15, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Left panel with input form
        JPanel leftPanel = createLeftPanel();
        add(leftPanel, BorderLayout.WEST);
        
        // Right panel with table
        JPanel rightPanel = createTablePanel();
        add(rightPanel, BorderLayout.CENTER);
    }
    
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(10, 10));
        leftPanel.setPreferredSize(new Dimension(350, 0));
        
        // Input panel
        JPanel inputPanel = createInputPanel();
        leftPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Button panel
        JPanel buttonPanel = createButtonPanel();
        leftPanel.add(buttonPanel, BorderLayout.CENTER);
        
        return leftPanel;
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 15, 20));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Register New Member"),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setBackground(new Color(240, 240, 240));
        panel.setPreferredSize(new Dimension(350, 180));
        
        JLabel idLabel = new JLabel("Member ID:", JLabel.CENTER);
        idLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField idField = new JTextField(25);
        idField.setFont(new Font("Arial", Font.PLAIN, 13));
        idField.setMargin(new Insets(10, 10, 10, 10));
        idField.setPreferredSize(new Dimension(200, 50));
        
        JLabel nameLabel = new JLabel("Name:", JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField nameField = new JTextField(25);
        nameField.setFont(new Font("Arial", Font.PLAIN, 13));
        nameField.setMargin(new Insets(10, 10, 10, 10));
        nameField.setPreferredSize(new Dimension(200, 50));
        
        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        
        panel.putClientProperty("idField", idField);
        panel.putClientProperty("nameField", nameField);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 8, 8));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Actions"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(new Color(240, 240, 240));
        panel.setPreferredSize(new Dimension(350, 160));
        
        JButton addButton = new JButton("Register Member");
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 11));
        
        JButton removeButton = new JButton("Remove Selected");
        removeButton.setBackground(new Color(220, 20, 60));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(new Font("Arial", Font.BOLD, 11));
        
        JButton clearButton = new JButton("Clear Fields");
        clearButton.setBackground(new Color(150, 150, 150));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 11));
        
        addButton.addActionListener(e -> handleAddMember());
        removeButton.addActionListener(e -> handleRemoveMember());
        clearButton.addActionListener(e -> handleClearFields());
        
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(clearButton);
        
        return panel;
    }
    
    private void handleAddMember() {
        JPanel leftPanel = (JPanel) getComponent(0);
        JPanel inputPanel = (JPanel) ((BorderLayout) leftPanel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        
        JTextField idField = (JTextField) inputPanel.getComponent(1);
        JTextField nameField = (JTextField) inputPanel.getComponent(3);
        
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            
            if (id <= 0) throw new InvalidIdException("ID must be positive!");
            if (name.isEmpty()) throw new InvalidNameException("Name cannot be empty!");
            if (name.length() < 2) throw new InvalidNameException("Name must be at least 2 characters!");
            
            librarian.registerMember(library, new Member(id, name));
            JOptionPane.showMessageDialog(this, "Member registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
            idField.setText("");
            nameField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Member ID must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleRemoveMember() {
        int selectedRow = membersTable.getSelectedRow();
        if (selectedRow != -1) {
            int memberId = (int) tableModel.getValueAt(selectedRow, 0);
            librarian.removeMember(library, memberId);
            JOptionPane.showMessageDialog(this, "Member removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a member to remove!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void handleClearFields() {
        JPanel leftPanel = (JPanel) getComponent(0);
        JPanel inputPanel = (JPanel) ((BorderLayout) leftPanel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        
        JTextField idField = (JTextField) inputPanel.getComponent(1);
        JTextField nameField = (JTextField) inputPanel.getComponent(3);
        
        idField.setText("");
        nameField.setText("");
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Members List"));
        
        // Create table
        tableModel = new DefaultTableModel(
            new String[]{"Member ID", "Name"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        membersTable = new JTable(tableModel);
        membersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        membersTable.setRowHeight(25);
        membersTable.getTableHeader().setBackground(new Color(70, 130, 180));
        membersTable.getTableHeader().setForeground(Color.WHITE);
        membersTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(membersTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        refreshTable();
        return panel;
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Member member : library.getMembers()) {
            tableModel.addRow(new Object[]{
                member.getMemberId(),
                member.getName()
            });
        }
    }
}

// ==================== TRANSACTION PANEL ====================
class TransactionPanel extends JPanel {
    private Library library;
    private DefaultTableModel tableModel;
    private JTable transactionsTable;
    
    public TransactionPanel(Library library) {
        this.library = library;
        
        setLayout(new BorderLayout(15, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Left panel with input form
        JPanel leftPanel = createLeftPanel();
        add(leftPanel, BorderLayout.WEST);
        
        // Right panel with table
        JPanel rightPanel = createTablePanel();
        add(rightPanel, BorderLayout.CENTER);
    }
    
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(10, 10));
        leftPanel.setPreferredSize(new Dimension(350, 0));
        
        // Input panel
        JPanel inputPanel = createInputPanel();
        leftPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Button panel
        JPanel buttonPanel = createButtonPanel();
        leftPanel.add(buttonPanel, BorderLayout.CENTER);
        
        return leftPanel;
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 15, 20));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Borrow/Return Book"),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setBackground(new Color(240, 240, 240));
        panel.setPreferredSize(new Dimension(350, 180));
        
        JLabel bookIdLabel = new JLabel("Book ID:", JLabel.CENTER);
        bookIdLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField bookIdField = new JTextField(25);
        bookIdField.setFont(new Font("Arial", Font.PLAIN, 13));
        bookIdField.setMargin(new Insets(10, 10, 10, 10));
        bookIdField.setPreferredSize(new Dimension(200, 50));
        
        JLabel memberIdLabel = new JLabel("Member ID:", JLabel.CENTER);
        memberIdLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField memberIdField = new JTextField(25);
        memberIdField.setFont(new Font("Arial", Font.PLAIN, 13));
        memberIdField.setMargin(new Insets(10, 10, 10, 10));
        memberIdField.setPreferredSize(new Dimension(200, 50));
        
        panel.add(bookIdLabel);
        panel.add(bookIdField);
        panel.add(memberIdLabel);
        panel.add(memberIdField);
        
        panel.putClientProperty("bookIdField", bookIdField);
        panel.putClientProperty("memberIdField", memberIdField);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 8, 8));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Actions"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(new Color(240, 240, 240));
        panel.setPreferredSize(new Dimension(350, 160));
        
        JButton borrowButton = new JButton("Borrow Book");
        borrowButton.setBackground(new Color(34, 139, 34));
        borrowButton.setForeground(Color.WHITE);
        borrowButton.setFont(new Font("Arial", Font.BOLD, 11));
        
        JButton returnButton = new JButton("Return Book");
        returnButton.setBackground(new Color(220, 20, 60));
        returnButton.setForeground(Color.WHITE);
        returnButton.setFont(new Font("Arial", Font.BOLD, 11));
        
        JButton clearButton = new JButton("Clear Fields");
        clearButton.setBackground(new Color(150, 150, 150));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 11));
        
        borrowButton.addActionListener(e -> handleBorrow());
        returnButton.addActionListener(e -> handleReturn());
        clearButton.addActionListener(e -> handleClearFields());
        
        panel.add(borrowButton);
        panel.add(returnButton);
        panel.add(clearButton);
        
        return panel;
    }
    
    private void handleBorrow() {
        JPanel leftPanel = (JPanel) getComponent(0);
        JPanel inputPanel = (JPanel) ((BorderLayout) leftPanel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        
        JTextField bookIdField = (JTextField) inputPanel.getComponent(1);
        JTextField memberIdField = (JTextField) inputPanel.getComponent(3);
        
        try {
            int bookId = Integer.parseInt(bookIdField.getText().trim());
            int memberId = Integer.parseInt(memberIdField.getText().trim());
            
            library.borrowBook(bookId, memberId);
            JOptionPane.showMessageDialog(this, "Book borrowed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
            bookIdField.setText("");
            memberIdField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleReturn() {
        JPanel leftPanel = (JPanel) getComponent(0);
        JPanel inputPanel = (JPanel) ((BorderLayout) leftPanel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        
        JTextField bookIdField = (JTextField) inputPanel.getComponent(1);
        JTextField memberIdField = (JTextField) inputPanel.getComponent(3);
        
        try {
            int bookId = Integer.parseInt(bookIdField.getText().trim());
            int memberId = Integer.parseInt(memberIdField.getText().trim());
            
            library.returnBook(bookId, memberId);
            JOptionPane.showMessageDialog(this, "Book returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
            bookIdField.setText("");
            memberIdField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleClearFields() {
        JPanel leftPanel = (JPanel) getComponent(0);
        JPanel inputPanel = (JPanel) ((BorderLayout) leftPanel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        
        JTextField bookIdField = (JTextField) inputPanel.getComponent(1);
        JTextField memberIdField = (JTextField) inputPanel.getComponent(3);
        
        bookIdField.setText("");
        memberIdField.setText("");
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Transaction History"));
        
        // Create table
        tableModel = new DefaultTableModel(
            new String[]{"Transaction ID", "Book", "Member", "Borrow Date", "Return Date"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        transactionsTable = new JTable(tableModel);
        transactionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        transactionsTable.setRowHeight(25);
        transactionsTable.getTableHeader().setBackground(new Color(70, 130, 180));
        transactionsTable.getTableHeader().setForeground(Color.WHITE);
        transactionsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        refreshTable();
        return panel;
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Transaction transaction : library.getTransactions()) {
            String[] parts = transaction.toString().split("\\|");
            if (parts.length >= 5) {
                tableModel.addRow(new Object[]{
                    parts[0].trim(),
                    parts[1].trim(),
                    parts[2].trim(),
                    parts[3].trim(),
                    parts[4].trim()
                });
            }
        }
    }
}
