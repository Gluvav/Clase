'use strict';
const fetch = require('node-fetch');
const recurso = "http://127.0.0.1:8080";

makeBooks();

function makeBooks(){
    fetch(recurso + '/libros')
    .then(res => res.json())
    .then(res => makeCad(res))
}

function makeCad(json){
    let cad = "";
    for (let i = 0; i < json.length; i++) {
        cad+= "<div>"
        cad += "<img src='" + recurso + "/public/img/" + json[i].img + "' height='170' width='108'><br>";
        cad += "<label><strong>" + json[i].title + "</strong></label><br>";
        cad += "<label>" + json[i].author + "</label>";
        cad += "</div>";
    }
    document.getElementById('wrapper').innerHTML=cad;
    //return cad;
}

let newBook = {
    "title": "New Book",
    "author": "New Author",
    "img": "0.jpg"
}

let button = document.getElementById('boton');
button.addEventListener("click", () => {
    console.log(newBook);
    addBook();
});

function addBook(){
    fetch(recurso + "/libros", {
    method: 'post',
    body: JSON.stringify(newBook),
    headers: { 'Content-Type': 'application/json'},
})
    .then(res => res.json);

    makeBooks();
}

