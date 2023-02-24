const express = require('express');
const bodyParser = require('body-parser');
const fs = require('fs');

let app = express();

app.use(bodyParser.json());

app.use('/public', express.static(__dirname + '/public'));

app.get('/games', (req, res) => {
    let fichero = fs.readFileSync('./games.json');
    let games = JSON.parse(fichero);
    res.send(games);
});

app.post('/games', (req, res) => {
    try {
        let newGame = req.body;
        let fichero = fs.readFileSync('./games.json');
        let games = JSON.parse(fichero);
        games.push(newGame);
        //console.log(newGame);
        //console.log(games);
        fs.writeFileSync('./games.json', JSON.stringify(games));
        res.send({ ok: true });
    }
    catch (err) {
        res.send({ ok: false });
    }
});

app.post('/delete', (req, res) => {
    try {
        let game = req.body;
        let fichero = fs.readFileSync('./games.json');
        let games = JSON.parse(fichero);
        games = games.filter(item => item.id !== game.id)
        fs.writeFileSync('./games.json', JSON.stringify(games));
        request.send({ ok: true });
    } catch (err) {
        res.send({ ok: false });
    }
});

app.listen(8080);