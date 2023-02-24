'use strict';
const fetch = require('node-fetch');
const recurso = "http://127.0.0.1:8080";

fetch(recurso + '/games')
    .then(res => res.json())
    .then(json => inicio(json));

const inicio = (games => {
    let cadenaDOM = "";
    document.getElementById('game').innerHTML = "<div class='wrapper' id='wrapper'></div>";
    for (let i = 0; i < games.length; i++) {
        cadenaDOM += `
            <div class="test` + i + `">
                <img src="${recurso}/public/img/${games[i].img}" height="170" width="108"><br>
                <label><strong>${games[i].name}</strong></label>
                <label><strong>${games[i].developer}</strong></label>
            </div>
        `
    }
    document.getElementById('wrapper').innerHTML = cadenaDOM;

    for (let i = 0; i < games.length; i++) {
        showGame(i, games);
    }
});

function showGame(i, games) {
    document.getElementsByClassName('test' + i)[0].addEventListener('click', () => {

        let cadenaDOM = "";
        cadenaDOM += `
            <div id="gamePage">
                <h1>${games[i].name}</h1>
                <img src="${recurso}/public/img/${games[i].img}" height="270" width="208"><br>
                <strong>${games[i].launchYear}</strong><br>
                <strong>${games[i].synopsis}</strong><br><br><br>
                
                <button class='btn btn-negative' id='remove'><span class='icon icon-trash icon-text'></span>
                    <font color='#3F3F3D'>Delete game</font>
                </button>
            </div>
        `

        document.getElementById('game').innerHTML = cadenaDOM;

        document.getElementById('remove').addEventListener('click', () => {
            //delete game
            fetch(recurso + '/delete', {
                method: 'post',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(games[i]),
            })
                .then(res => res.json())
                .then(data => console.log(data))
                .catch(err => console.error(err));
            fetch(recurso + '/games')
                .then(res => res.json())
                .then(json => inicio(json));
        });

    });
}

document.getElementById('home').addEventListener('click', () => {
    fetch(recurso + '/games')
        .then(res => res.json())
        .then(json => inicio(json));
});

document.getElementById('add').addEventListener('click', () => {
    //
    let cad = "";
    cad += "<div class='addPage'>";
    cad += "    <input type='number' id='id' name='id' placeholder='game id' required>";
    cad += "    <input type='text' id='name' name='name' placeholder='game name' required>";
    cad += "    <input type='text' id='developer' name='developer' placeholder='game developer' required>";
    cad += "    <input type='text' id='synopsis' name='synopsis' placeholder='game synopsis' required>";
    cad += "    <input type='number' id='launchYear' name='launchYear' placeholder='game launch year' required>";
    cad += "    <input type='text' id='img' name='img' placeholder='game img'>";
    cad += "</div><div class='addPage'>";
    cad += "    <button class='btn btn-positive' id='adding'><span class='icon icon-plus icon-text'></span>";
    cad += "        <font color='#3F3F3D'>Add game</font>";
    cad += "    </button>";
    cad += "</div>";
    document.getElementById('game').innerHTML = cad;

    document.getElementById('adding').addEventListener('click', () => {

        let id = document.getElementById('id').value;
        let idInt = parseInt(id);
        let name = document.getElementById('name').value;
        let developer = document.getElementById('developer').value;
        let synopsis = document.getElementById('synopsis').value;
        let launchYear = document.getElementById('launchYear').value;
        let launch = parseInt(launchYear);
        let img = document.getElementById('img').value;

        if (img.length >= 0) {
            img = "img.jpg";
        }

        let newGame = {
            "id": idInt,
            "name": name,
            "developer": developer,
            "synopsis": synopsis,
            "launchYear": launch,
            "img": img
        }
        console.log(JSON.stringify(newGame));

        fetch(recurso + '/games', {
            method: 'post',
            body: JSON.stringify(newGame),
            headers: { 'Content-Type': 'application/json' },
        }).then(res => res.json()).then(json => console.log(json));

        fetch(recurso + '/games')
            .then(res => res.json())
            .then(json => inicio(json));
    });
    //
});