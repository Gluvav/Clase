const express = require('express');
const bodyParser = require('body-parser');
const fs = require('fs');

let app = express();//load express

app.use(bodyParser.json());

app.get('/welcome', (req, res) => {
    res.send('Hola, bienvenido/a');
});
//listado clientes
app.get('/clients', (req, res) => {
    let fichero = fs.readFileSync('./clients.json');
    let clients = JSON.parse(fichero);
    res.send(clients);
});
//listado por dni
app.get('/clients/:dni', (req, res) => {
    let fichero = fs.readFileSync('./clients.json');
    let clientes = JSON.parse(fichero);
    let clients = clientes.filter(client => client.dni == req.params.dni);
    if (clients.length != 0) {
        res.send(clients);
    } else {
        res.send('Error: no clientes encontrados');
    }
});

app.get('/insert', (req, res) => {
    try {
        let newClient = req.body;
        let fichero = fs.readFileSync('./clients.json');
        let clients = JSON.parse(fichero);
        clients.push(newClient);
        fs.writeFileSync('./clients.json', JSON.stringify(clients));
        res.send({ok: true});
    } catch (err) {
        res.send({ok:false});
    }
    
});

app.get('/', (req, res) => {
    res.send('Hola, estas en /');
});

app.listen(8080);