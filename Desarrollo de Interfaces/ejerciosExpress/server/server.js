const bodyParser = require('body-parser');
const express = require('express');
const fs = require('fs');

let app = express();

app.use(bodyParser.json());

app.get('/libros', (req, res) => {
    let fichero = fs.readFileSync('./books.json');
    let libros = JSON.parse(fichero);
    res.send(libros);
});

app.post('/libros', (req, res) => {
    try {
        let nuevo = req.body;
        let fichero = fs.readFileSync('./books.json'); 
        let libros = JSON.parse(fichero);
        libros.push(nuevo);
        fs.writeFileSync('./books.json', JSON.stringify(libros));
        res.send({ok: true});
    } catch (err) {
        console.log(err);
        res.send({ok: false});
    }
});

app.use('/public', express.static(__dirname + '/public'));

app.listen(8080);