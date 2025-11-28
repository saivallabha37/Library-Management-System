# Library Management System - Swing GUI

## Overview
The Library Management System now includes a modern Swing-based graphical user interface with three main panels for managing books, members, and transactions.

## Features

### 1. **Header**
- Displays the library name: "Dr. Sarvepalli Radhakrishnan Library"
- Shows the library address for context
- Professional blue color scheme with white text

### 2. **Books Panel**
- **Add New Book Section:**
  - Input fields for Book ID, Title, and Author
  - Validation for all inputs (positive ID, non-empty title and author)
  - Add and Clear buttons
  
- **Books List Table:**
  - Displays all books with columns: Book ID, Title, Author, Status
  - Status shows "Available" or "Borrowed"
  - Refresh button to update the table
  - Remove Selected button to delete books from inventory

### 3. **Members Panel**
- **Register New Member Section:**
  - Input fields for Member ID and Name
  - Validation for all inputs
  - Register and Clear buttons
  
- **Members List Table:**
  - Displays all registered members with: Member ID, Name
  - Refresh button to update the list
  - Remove Selected button to unregister members

### 4. **Transactions Panel**
- **Borrow/Return Book Section:**
  - Input fields for Book ID and Member ID
  - Borrow Book button (green) - initiates a book borrowing transaction
  - Return Book button (red) - marks a book as returned
  
- **Transaction History Table:**
  - Shows all transactions with: Transaction ID, Book Title, Member Name, Status
  - Refresh button to view latest transactions
  - Displays "Not Returned" or return date in status column

## How to Run

### From Command Line:
```powershell
cd "c:\Users\saiva\OneDrive\Desktop\Library Management System"
javac library/*.java library/**/*.java
java library.LibraryAppGUI
```

### Features of the GUI:

1. **User-Friendly Interface**
   - Organized into logical tabs (Books, Members, Transactions)
   - Color-coded buttons for different actions
   - Input validation with error messages
   - Confirmation dialogs for successful operations

2. **Real-Time Updates**
   - Tables automatically refresh after operations
   - Data persists across sessions (auto-saved every 30 seconds)

3. **Error Handling**
   - Validation for all inputs
   - Clear error messages for invalid operations
   - Exception handling for edge cases

4. **Professional Design**
   - Responsive layout that adapts to window resizing
   - Consistent color scheme (blues for primary actions, red for destructive actions, green for constructive actions)
   - Clear typography and spacing

## Color Scheme

- **Header Background:** Navy Blue (#194C8C)
- **Primary Buttons:** Steel Blue (#4682B4)
- **Secondary Buttons:** Gray (#969696)
- **Success Buttons:** Forest Green (#228B22)
- **Danger Buttons:** Crimson Red (#DC143C)
- **Input Background:** Light Gray (#F0F0F0)

## Data Persistence

- Data is automatically saved to `library.dat` file
- Auto-save occurs every 30 seconds via background thread
- On application exit, all changes are saved automatically

## Keyboard & Mouse Navigation

- Click any button to perform its action
- Use Tab to move between input fields
- Select rows in tables to perform row-specific operations
- Use Refresh buttons to sync data from file system

## Notes

- The old console-based LibraryApp.java still works for terminal users
- GUI and console versions share the same data model
- Both versions can be used interchangeably (data is synchronized)
