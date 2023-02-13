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
    document.getElementById('game').innerHTML = "<div class='wrapper' id='wrapper'></div>";
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
            let cad = "";
            cad += "<div id='gamePage" + i + "'>";
            cad += "   <h1>" + resu[i].name + "</h1>";
            cad += "   <img src='img/" + resu[i].img + "' height='270' width='208'><br>";
            cad += "   <strong>" + resu[i].launcYear + "</strong><br>";
            cad += "   <strong>" + resu[i].synopsis + "</strong><br>";

            cad += "<button class='btn btn-warning' id='modify'><span class='icon icon-pencil icon-text'></span>";
            cad += "    <font color='#3F3F3D'>Modify game</font>";
            cad += "</button>"
            cad += "<button class='btn btn-negative' id='remove'><span class='icon icon-trash icon-text'></span>"
            cad += "    <font color='#3F3F3D'>Remove game</font>"
            cad += "</button>"

            cad += "</div>";

            document.getElementById('game').innerHTML = cad;



        }).catch(err => {
            console.log("Error : " + err);
        });
        Game.find().then(games => {
            for (let j = 0; j < games.length; j++) {
                if (i == j) {
                    deleteGame(i, games);
                }
            }
        }).catch(err => {
            console.log("Error : " + err);
        });

        
    });
}

function addGame() {
    let cad = "";
    cad += "<div class='addPage'>";
    cad += "    <input type='number' id='id' name='id' placeholder='game id' required>";
    cad += "    <input type='text' id='name' name='name' placeholder='game name' required>";
    cad += "    <input type='text' id='developer' name='developer' placeholder='game developer' required>";
    cad += "    <input type='text' id='synopsis' name='synopsis' placeholder='game synopsis' required>";
    cad += "    <input type='number' id='launchYear' name='launchYear' placeholder='game launch year' required>";
    cad += "    <input type='text' id='img' name='img' placeholder='game img' required>";
    cad += "</div><div class='addPage'>";
    cad += "    <button class='btn btn-positive' id='adding'><span class='icon icon-plus icon-text'></span>";
    cad += "        <font color='#3F3F3D'>Add game</font>";
    cad += "    </button>";
    cad += "</div>";
    document.getElementById('game').innerHTML = cad;

    document.getElementById('adding').addEventListener('click', () => {
        //make promise to save game
        let id = document.getElementById('id').value;
        let name = document.getElementById('name').value;
        let developer = document.getElementById('developer').value;
        let synopsis = document.getElementById('synopsis').value;
        let launchYear = document.getElementById('launchYear').value;
        let img = document.getElementById('img').value;

        if (img.length <= 0) {
            img = "img.jpg";
        }

        let game = new Game({
            id: id,
            name: name,
            developer: developer,
            synopsis: synopsis,
            launcYear: launchYear,
            img: img
        });
        let p = game.save().then(resu => {
            console.log("Game added " + resu);
        }).catch(err => {
            console.log(err);
        });

        Promise.all([p]).then(resu => {
            console.log("Game saved " + resu);
            findAll();
        }).catch(err => {
            console.log(err);
        });

    });
}

function deleteGame(i, json) {
    remove.addEventListener('click', () => {
        Game.findOneAndDelete(json[i].id).then(resu => {
            console.log("Game deleted " + resu);
            findAll();
        }).catch(err => {
            console.log(err);
        });
    });
}

home.addEventListener('click', () => {
    findAll();
});

add.addEventListener('click', () => {
    //load page with unputs to add the game.
    addGame();
});