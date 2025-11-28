const http = require('http');
const { spawn, execSync } = require('child_process');
const path = require('path');
const fs = require('fs');
const os = require('os');

// Determine platform
const isWindows = os.platform() === 'win32';

// Compile Java code on startup
function compileJava() {
    try {
        execSync('javac -cp . library/*.java library/exception/*.java library/model/*.java library/autosave/*.java library/ui/*.java', {
            cwd: __dirname,
            stdio: 'pipe'
        });
        console.log('Java compilation successful');
    } catch(err) {
        console.warn('Java compilation warning:', err.message);
    }
}

compileJava();

const server = http.createServer((req, res) => {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
    
    if (req.url === '/launch-gui' && req.method === 'GET') {
        try {
            if (isWindows) {
                const gui = spawn('javaw.exe', ['library.LibraryAppGUI'], {
                    cwd: __dirname,
                    detached: true,
                    stdio: 'ignore'
                });
                gui.unref();
            } else {
                const gui = spawn('java', ['library.LibraryAppGUI'], {
                    cwd: __dirname,
                    detached: true,
                    stdio: 'ignore'
                });
                gui.unref();
            }
            
            res.writeHead(200, { 'Content-Type': 'application/json' });
            res.end(JSON.stringify({ status: 'GUI launched successfully' }));
        } catch(err) {
            console.error('Error launching GUI:', err);
            res.writeHead(500, { 'Content-Type': 'application/json' });
            res.end(JSON.stringify({ status: 'Error', error: err.message }));
        }
    }
    else if (req.url === '/launch-cli' && req.method === 'GET') {
        try {
            const cli = spawn(isWindows ? 'java.exe' : 'java', ['library.LibraryApp'], {
                cwd: __dirname,
                stdio: 'pipe'
            });
            
            res.writeHead(200, { 'Content-Type': 'application/json' });
            res.end(JSON.stringify({ status: 'CLI launched successfully' }));
        } catch(err) {
            console.error('Error launching CLI:', err);
            res.writeHead(500, { 'Content-Type': 'application/json' });
            res.end(JSON.stringify({ status: 'Error', error: err.message }));
        }
    }
    else if (req.url === '/' || req.url === '/index.html') {
        try {
            const indexPath = path.join(__dirname, 'index.html');
            const indexContent = fs.readFileSync(indexPath, 'utf-8');
            res.writeHead(200, { 'Content-Type': 'text/html' });
            res.end(indexContent);
        } catch(err) {
            console.error('Error reading index.html:', err);
            res.writeHead(500, { 'Content-Type': 'text/html' });
            res.end('<h1>Error loading page</h1>');
        }
    }
    else {
        res.writeHead(404);
        res.end('Not found');
    }
});

const PORT = process.env.PORT || 5500;
server.listen(PORT, '0.0.0.0', () => {
    console.log('Server running at http://localhost:' + PORT);
    console.log('For Phone/Mobile: Run "ipconfig" to find IPv4, then visit http://[YOUR_IP]:5500');
});
