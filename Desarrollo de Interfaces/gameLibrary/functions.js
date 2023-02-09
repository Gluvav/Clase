const { Game } = require('./database/database');
const database = require('./database/database');

let home = document.getElementById('home');
let add = document.getElementById('add');
let modify = document.getElementById('modify');
let remove = document.getElementById('remove');

findAll();

/*
let game1 = new Game({
    id: 0,
    name: 'Elden Ring',
    developer: 'FromSoftware',
    synopsis: 'Elden Ring is a 2022 action role-playing game developed by FromSoftware and published by Bandai Namco Entertainment. Directed by Hidetaka Miyazaki with worldbuilding provided by fantasy writer George R. R. Martin',
    launcYear: '2022',
    img: '4.jpg'
});

let p1 = game1.save().then(resultado => {
    console.log("Game added: " + resultado);
}).catch(err => {
    console.log("Error : " + err);
});

let game2 = new Game({
    id: 1,
    name: 'Dark Souls',
    developer: 'FromSoftware',
    synopsis: 'Dark Souls is a series of action role-playing games created by Hidetaka Miyazaki of FromSoftware and published by Bandai Namco Entertainment.',
    launcYear: '2011',
    img: '1.jpg'
});

let p2 = game2.save().then(resultado => {
    console.log("Game added: " + resultado);
}).catch(err => {
    console.log("Error : " + err);
});

let game3 = new Game({
    id: 2,
    name: 'Dark Souls 2',
    developer: 'FromSoftware',
    synopsis: 'Elden Ring is a 2022 action role-playing game developed by FromSoftware and published by Bandai Namco Entertainment. Directed by Hidetaka Miyazaki with worldbuilding provided by fantasy writer George R. R. Martin',
    launcYear: '2022',
    img: '2.jpg'
});

let p3 = game3.save().then(resultado => {
    console.log("Game added: " + resultado);
}).catch(err => {
    console.log("Error : " + err);
});


Promise.all([p1, p2, p3]).then(values => {
    console.log("Promesa resuelta. " + values)
});
*/

function findAll() {
    Game.find().then(resu => {
        printAll(resu)
    }).catch(err => {
        console.log("Error : " + err);
    })
}

function printAll(games) {
    let cad = "";
    //for (const game in games) {
    for (let i = 0; i < games.length; i++) {
        cad += "<div class='test" + i + "'>";
        cad += "    <img src='img/" + games[i].img + "' height='170' width='108'><br>";
        cad += "    <label><strong>" + games[i].name + "</strong></label>";
        cad += "    <label><strong>" + games[i].launcYear + "</strong></label>";
        cad += "</div>";
    }

    document.getElementById('wrapper').innerHTML = cad;

    for (let i = 0; i < games.length; i++) {
        showGame(i)
    }
}

function showGame(i) {
    document.getElementsByClassName('test' + i)[0].addEventListener('click', () => {
        Game.find().then(resu => {
            let cad = "<h1 class='title'>" + resu[i].name + "</h1>";
            cad += "    <img src='img/" + resu[i].img + "' height='170' width='108'><br>";
            cad += "    <p><label><strong>" + resu[i].launcYear + "</strong></label></p>";
            cad += "    <p><label>" + resu[i].synopsis + "</label></p>";
            document.getElementById('game').innerHTML = cad;
        }).catch(err => {
            console.log("Error : " + err);
        })

    })
}

home.addEventListener('click', () => {
    findAll();
})