const { app, BrowserWindow, dialog } = require('electron');

function createWindow() {
    let mainWindow = new BrowserWindow({
        width: 1080,
        height: 720,
        webPreferences: {
            nodeIntegration: true,
            contextIsolation: false
        }
    });

    //quita menú por defecto de chromium
    mainWindow.setMenu(null);
    mainWindow.loadFile('xel.html');
    // Open the DevTools.
    mainWindow.webContents.openDevTools();
}

app.whenReady().then(createWindow);
app.allowRendererProcessReuse = true;