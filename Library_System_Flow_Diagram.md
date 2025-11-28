# Library Management System Flow Diagram

## System Architecture Overview

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                           LIBRARY MANAGEMENT SYSTEM                         │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   LibraryApp    │    │   Librarian     │    │    Library      │
│   (Main Menu)   │◄──►│   (Admin)       │◄──►│   (Data Store)  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Input         │    │   Validation    │    │   Collections   │
│   Validation    │    │   Methods       │    │   Management    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## Main Menu Flow

```
                    ┌─────────────────┐
                    │   Start App     │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │  Display Menu   │
                    │  1-10 Options  │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │  Get User Input │
                    │   (Choice 1-10) │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │ Input Validation│
                    │ (Number Check)  │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │  Process Choice │
                    │   (Switch)     │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │ Choice = 10?    │
                    │   (Exit?)       │
                    └─────────┬───────┘
                              │
                    ┌─────────┴───────┐
                    │       No        │
                    │   (Continue)    │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │   Loop Back     │
                    │   to Menu       │
                    └─────────────────┘
```

## Detailed Operation Flow

### 1. Add Book Flow
```
User Input → Validate ID → Validate Title → Validate Author → Add to Library → Success Message
     │              │              │              │              │
     ▼              ▼              ▼              ▼              ▼
InputMismatch   InvalidId    InvalidTitle   InvalidAuthor   Book Added
Exception       Exception    Exception      Exception       Successfully
```

### 2. Register Member Flow
```
User Input → Validate ID → Validate Name → Add to Library → Success Message
     │              │              │              │
     ▼              ▼              ▼              ▼
InputMismatch   InvalidId    InvalidName    Member Added
Exception       Exception    Exception      Successfully
```

### 3. Borrow Book Flow
```
User Input → Validate IDs → Check Book Exists → Check Availability → Create Transaction
     │              │              │              │              │
     ▼              ▼              ▼              ▼              ▼
InputMismatch   InvalidId    Book Not Found   Book Not       Transaction
Exception       Exception    Error           Available       Created
```

### 4. Return Book Flow
```
User Input → Validate IDs → Check Book Exists → Return Book → Update Transaction
     │              │              │              │              │
     ▼              ▼              ▼              ▼              ▼
InputMismatch   InvalidId    Book Not Found   Book Returned   Transaction
Exception       Exception    Error           Successfully    Updated
```

### 5. Remove Operations Flow
```
User Input → Validate ID → Remove from Collection → Success Message
     │              │              │              │
     ▼              ▼              ▼              ▼
InputMismatch   InvalidId    Item Removed     Success
Exception       Exception    Successfully     Message
```

## Exception Handling Flow

```
                    ┌─────────────────┐
                    │   Any Operation │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │   Try Block     │
                    │   (Operation)   │
                    └─────────┬───────┘
                              │
                    ┌─────────┴───────┐
                    │   Exception?    │
                    └─────────┬───────┘
                              │
                    ┌─────────┴───────┐
                    │       Yes       │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │  Catch Blocks   │
                    │ • InputMismatch  │
                    │ • InvalidId     │
                    │ • InvalidName   │
                    │ • InvalidTitle  │
                    │ • InvalidAuthor │
                    │ • BookNotAvail  │
                    │ • General       │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │ Display Error   │
                    │   Message       │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │ Continue to     │
                    │   Next Loop     │
                    └─────────────────┘
```

## Data Persistence Flow

```
                    ┌─────────────────┐
                    │   AutoSave      │
                    │   Thread        │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │ Every 30 sec    │
                    │ Save to File    │
                    └─────────┬───────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │ library.dat     │
                    │ (Serialized)    │
                    └─────────────────┘
```

## System Components Interaction

```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│ LibraryApp  │    │ Librarian   │    │ Library     │
│             │    │             │    │             │
│ • Menu      │◄──►│ • addBook   │◄──►│ • books     │
│ • Input     │    │ • removeBook│    │ • members   │
│ • Validation│    │ • register  │    │ • transactions│
│ • Exception │    │ • remove    │    │ • borrow    │
│   Handling  │    │   Member    │    │ • return    │
└─────────────┘    └─────────────┘    └─────────────┘
       │                   │                   │
       │                   │                   │
       ▼                   ▼                   ▼
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│ Exceptions  │    │ Validation  │    │ AutoSave    │
│             │    │             │    │             │
│ • InvalidId │    │ • ID Check  │    │ • Thread    │
│ • InvalidName│   │ • Name Check│    │ • Periodic  │
│ • InvalidTitle│  │ • Title Check│   │ • Save      │
│ • InvalidAuthor│ │ • Author Check│  │ • File I/O  │
│ • BookNotAvail│  │             │    │             │
└─────────────┘    └─────────────┘    └─────────────┘
```

## Key Features Highlighted

1. **Input Validation**: All user inputs are validated before processing
2. **Exception Handling**: Comprehensive error handling with user-friendly messages
3. **Data Persistence**: Automatic saving every 30 seconds
4. **Menu-Driven Interface**: Clear navigation with 10 options
5. **CRUD Operations**: Create, Read, Update, Delete for books and members
6. **Transaction Management**: Track borrowing and returning operations
7. **Thread Safety**: AutoSave runs in separate thread
8. **Data Integrity**: Validation ensures data quality

This flow diagram shows how your Library Management System handles user interactions, validates inputs, manages data, and provides robust error handling throughout the application lifecycle.
