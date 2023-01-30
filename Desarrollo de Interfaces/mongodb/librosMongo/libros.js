const mongoose = require('mongoose');
const fs = require('fs');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/LibrosTarde', {
    useNewUrlParser: true,
    useUnifiedTopology: true
});

let librosSchema = new mongoose.Schema({
    title: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    author: {
        type: String,
        trim: true,
        minlength: 1
    },
    img: {
        type: String
    }
});

let Libro = mongoose.model('librosTarde', librosSchema);

function showLibros(books) {
    let a = "";

    for (let unid in books) {
        a += "<div>";
        a += "  <img src='./img/" + books[unid].img + "' height='170' width='108'>";
        a += "  <x-label><strong>" + books[unid].title + "</strong></x-label>";
        a += "  <x-label>" + books[unid].author + "</x-label>";
        a += "</div>";
    }

    /*for (let i = 0; i < books.length; i++) {
        a += "<div>";
        a += "  <img src='./img/" + books[i].img + "' height='170' width='108'>";
        a += "  <x-label><strong>" + books[i].title + "</strong></x-label>";
        a += "  <x-label>" + books[i].author + "</x-label>";
        a += "</div>";
    }*/

    document.getElementById('wrapper').innerHTML = a;
}

let searchAll = () => {
    Libro.find().then(result => {
        showLibros(result);
    }).catch(err => {
        console.error(err);
    });
}

searchAll();

let btnBuscarAutor = document.getElementById('btnBuscarAutor');
let btnBuscarTitulo = document.getElementById('btnBuscarTitulo');
let btnAddLibro = document.getElementById('btnAddLibro');
let btnBorrarTitulo = document.getElementById('btnBorrarTitulo');
let todos = document.getElementById('todos');

btnBuscarAutor.addEventListener('click', () => {
    let txtBuscarAutor = document.getElementById('txtBuscarAutor').value;
    if (txtBuscarAutor == "") {
        let notification = document.querySelector('#notification');
        notification.innerHTML = "Debe escribir algo.";
        notification.opened = true;
    } else {
        Libro.find({ author: { $regex: txtBuscarAutor } }).then(resu => {
            console.log(resu);
            showLibros(resu);
        }).catch(err => {
            console.error(err);
        });
    }
});

btnBuscarTitulo.addEventListener('click', () => {
    let txtBuscarTitulo = document.getElementById('txtBuscarTitulo').value;
    if (txtBuscarTitulo == "") {
        let notification = document.querySelector('#notification');
        notification.innerHTML = "Debe escribir algo.";
        notification.opened = true;
    } else {
        Libro.find({ title: { $regex: txtBuscarTitulo } }).then(resu => {
            console.log(resu);
            showLibros(resu);
        }).catch(err => {
            console.error(err);
        });
    }
});

todos.addEventListener('click', () => {
    searchAll();
});

btnAddLibro.addEventListener('click', () => {
    let txtNuevoTitulo = document.getElementById('txtNuevoTitulo').value;
    let txtNuevoAutor = document.getElementById('txtNuevoAutor').value;
    let txtNuevaImagen = document.getElementById('txtNuevaImagen').value;

    if (txtNuevoTitulo == "" || txtNuevoAutor == "" || txtNuevaImagen == "") {
        let notification = document.querySelector('#notification2');
        notification.innerHTML = "Debe escribir algo.";
        notification.opened = true;
    } else {

        let libro = new Libro({
            title: txtNuevoTitulo,
            author: txtNuevoAutor,
            img: txtNuevaImagen
        });
        libro.save().then(resu => {
            let notification = document.querySelector('#notification2');
            notification.innerHTML = "Libro añadido.";
            notification.opened = true;
            searchAll();
        }).catch(err => {
            let notification = document.querySelector('#notification2');
            notification.innerHTML = "No se ha podido añadir el libro.";
            notification.opened = true;
            console.log(err);
        })

    }

});

btnBorrarTitulo.addEventListener('click', () => {
    let txtTituloBorrar = document.getElementById('txtTituloBorrar').value;
    if (txtTituloBorrar == "") {
        let notification = document.querySelector('#notification3');
        notification.innerHTML = "Debe escribir algo.";
        notification.opened = true;
    } else {
        Libro.deleteOne({ title: txtTituloBorrar }).then(resu => {
            console.log(resu);
            searchAll();
            let notification = document.querySelector('#notification2');
            notification.innerHTML = "Libro eliminado con exito.";
            notification.opened = true;
        }).catch(err => {
            console.error(err);
            let notification = document.querySelector('#notification2');
            notification.innerHTML = "Error al eliminar el libro.";
            notification.opened = true;
        });
    }
});