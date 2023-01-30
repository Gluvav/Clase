const express = require('express');
const bodyParser = require('body-parser');
const fs = require('fs');
const os = require('os');

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

app.get('/fecha', (req, res) => {
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let yyyy = today.getFullYear();
    today = dd + "/" + mm + "/" + yyyy;
    res.send(today)
});

app.get('/usuario', (req, res) => {
    res.send(os.hostname());
});

app.listen(8080);