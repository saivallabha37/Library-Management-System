# Library Management System

A professional Java Swing-based Library Management System with a modern web interface.

## 🚀 Quick Start (Local)

```bash
# 1. Clone the repository
git clone https://github.com/saivallabha37/Library-Management-System
cd Library-Management-System

# 2. Install dependencies
npm install

# 3. Start the server
node server.js

# 4. Open in browser
http://localhost:5500
```

## 📋 Features

- **📖 Book Management** - Add, remove, and track books
- **👥 Member Management** - Register and manage library members  
- **📝 Transactions** - Track book borrowing and returns
- **💾 Data Persistence** - Auto-save to library.dat every 10 seconds
- **🖥️ Professional GUI** - 3 tabbed panels with tables and forms
- **⌨️ CLI Support** - Command-line interface available

## 🌐 Deploy to Cloud (Optional)

To make buttons work from GitHub Pages, deploy the backend to a cloud platform:

### Option 1: Railway (Easiest - Free Tier)
1. Sign up at https://railway.app
2. Create new project from GitHub
3. Select this repository
4. Add environment variable: `PORT=3000`
5. Deploy and get your URL: `https://your-app.railway.app`
6. Update `BACKEND_URL` in index.html with your deployed URL

### Option 2: Render (Free Tier)
1. Sign up at https://render.com
2. Create new Web Service from GitHub
3. Select this repository
4. Build: `npm install`
5. Start: `node server.js`
6. Get your URL: `https://your-app.onrender.com`

## 🏗️ Project Structure

```
Library-Management-System/
├── library/
│   ├── LibraryApp.java          (CLI entry point)
│   ├── LibraryAppGUI.java       (GUI entry point)
│   ├── model/
│   │   ├── Book.java
│   │   ├── Member.java
│   │   ├── Library.java
│   │   ├── Librarian.java
│   │   └── Transaction.java
│   ├── exception/               (Custom exceptions)
│   ├── autosave/               (Auto-save thread)
│   └── ui/
│       └── LibraryGUI.java     (Main GUI implementation)
├── server.js                    (Node.js HTTP server)
├── index.html                   (Web interface)
├── package.json
└── library.dat                  (Persisted data)
```

## 💻 Technology Stack

- **Backend**: Java (Swing GUI), Node.js (HTTP Server)
- **Frontend**: HTML5, CSS3, JavaScript
- **Storage**: Serialized Java objects (.dat file)
- **Data Format**: Binary (efficient and fast)

## 🎨 GUI Features

- **Header**: Library name and location
- **Books Panel**: Add books, view inventory, manage stock
- **Members Panel**: Register members, manage accounts
- **Transactions Panel**: Borrow/return books, track history
- **Real-time Tables**: Live data with refresh capability
- **Form Validation**: All inputs validated before processing

## 📱 Access from Phone

1. Run: `ipconfig` (on Windows)
2. Find your IPv4 Address (e.g., 192.168.1.100)
3. On phone: `http://192.168.1.100:5500`
4. Must be on same WiFi network

## 📝 Sample Data

On first run, the system loads:
- 20 sample books
- 10 sample members
- 30 sample transactions

Data persists automatically in `library.dat`

## 🔧 Commands

```bash
# Start server
node server.js

# Compile Java only
javac -cp . library/*.java library/**/*.java

# Run CLI version
java library.LibraryApp

# Run GUI version
javaw library.LibraryAppGUI

# Find your IP address
ipconfig
```

## 📄 License

MIT License - Feel free to use and modify

## 👤 Author

**Sai Vallabha** - [GitHub Profile](https://github.com/saivallabha37)

---

**Note**: This is a project built with Java Swing for desktop and Node.js for web access. For full functionality, clone the repository and run locally.
