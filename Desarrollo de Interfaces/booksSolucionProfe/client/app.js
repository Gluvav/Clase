'use strict';
const fetch = require('node-fetch');
const recurso = "http://127.0.0.1:8080";

//Get para todos los libros:
fetch(recurso + '/libros')
  .then(res => res.json())
  .then(json => inicio(json));

const inicio = (books => {
  let cadenaDOM = "";
  books.forEach(book => {
    cadenaDOM +=
      `<div>
                
                    <img src="${recurso}/public/${book.img}" height="170" width="108">
                    <br>
                    <label><strong>${book.title}</strong></label>
                    <br>
                    <label>${book.author}</label>
                
            </div>`;
  });
  document.getElementById("wrapper").innerHTML = cadenaDOM;
});

let nuevo = {
  "title": "5672",
  "author": "LALA",
  "img": "9.jpg"
};
//post insertar cliente 
fetch(recurso + '/libros', {
  method: 'post',
  body: JSON.stringify(nuevo),
  headers: { 'Content-Type': 'application/json' },
})
  .then(res => res.json())
  .then(json => console.log(json));