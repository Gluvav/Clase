const { Game } = require('./database/database');
const database = require('./database/database');

let home = document.getElementById('home');
let add = document.getElementById('add');
let modify = document.getElementById('modify');
let remove = document.getElementById('remove');

let game1 = new Game({
    id: 0,
    name: 'Elden Ring',
    developer: 'FromSoftware',
    synopsis: 'Elden Ring is a 2022 action role-playing game developed by FromSoftware and published by Bandai Namco Entertainment. Directed by Hidetaka Miyazaki with worldbuilding provided by fantasy writer George R. R. Martin',
    launcYear: '2022',
    img: '0.jpg'
});

let p1 = game1.save().then(resultado => {
    console.log("Game añadida: " + resultado);
}).catch(err => {
    console.log("Error : " + err);
});
Promise.all([p1]).then(values => {
    database.connection.close();
});