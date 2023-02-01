const express = require('express');
const bodyParser = require('body-parser');
const fs = require('fs');
const os = require('os');


let app = express();
app.use(bodyParser.json());

app.get('/', (req, res) => {
    let fichero = fs.readFileSync('bienvenida.json');
    let bienvenida = JSON.parse(fichero);
    res.send(bienvenida);
});

app.get('/hostname', (req, res) => {
    res.send(os.hostname());
});

app.get('/fichero', (req, res) => {
    res.send(fs.readFileSync('fichero.html'));
})

app.listen(8080);