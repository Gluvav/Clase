const mongoose = require('mongoose');
const fs = require('fs');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/peliculasTarde', {
    useNewUrlParser: true,
    useUnifiedTopology: true
});

let peliculasSchema = new mongoose.Schema({
    id: {
        type: Number,
        required: true,
        minlength: 1
    },
    nombre: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    director: {
        type: String,
        required: true,
        trim: true
    },
    clasificacion: {
        type: String
    }
});

let Pelicula = mongoose.model('peliculasTarde', peliculasSchema);

function showPelis(pelis) {
    let a = "";

    a += "<div>";
    a += "  <table class='table-striped'>";
    a += "      <thead>";
    a += "          <tr>";
    a += "              <td>Id</td>";
    a += "              <td>Nombre</td>";
    a += "              <td>Director</td>";
    a += "              <td>Clasificacion</td>";
    a += "          </tr>";
    a += "      </thead>";
    a += "      <tbody>";

    for (let i in pelis) {
        a += "<tr>";
        a += "<td>" + pelis[i].id + "</td>"
        a += "<td>" + pelis[i].nombre + "</td>"
        a += "<td>" + pelis[i].director + "</td>"
        a += "<td>" + pelis[i].clasificacion + "</td>"
    }

    a += "      </tbody>";
    a += "   </table>";
    a += "</div>";

    document.getElementById("wrapper").innerHTML = a;
    //return a;
}

let findAll = () => {
    Pelicula.find().then(resu => {
        showPelis(resu);
    }).catch(err => {
        console.error(err);
    })
}
findAll();


let drama = document.getElementById('drama');
drama.addEventListener('click', () => {
    Pelicula.find({clasificacion: 'Drama'}).then(resu => {
        showPelis(resu);
    }).catch(err => {
        console.error(err);
    })
});

let accion = document.getElementById('accion');
accion.addEventListener('click', () => {
    Pelicula.find({clasificacion: 'Acción'}).then(resu => {
        showPelis(resu);
    }).catch(err => {
        console.error(err);
    })
});

let comedia = document.getElementById('comedia');
comedia.addEventListener('click', () => {
    Pelicula.find({clasificacion: 'Comedia'}).then(resu => {
        showPelis(resu);
    }).catch(err => {
        console.error(err);
    });
});

let documental = document.getElementById('documental');
documental.addEventListener('click', () => {
    Pelicula.find({clasificacion: 'Documental'}).then(resu => {
        showPelis(resu);
    }).catch(err => {
        console.error(err);
    })
});


//let p1;
/*
peliculas.forEach(pelicula => {
    let peli1 = new Pelicula({
        id: pelicula.id,
        nombre: pelicula.nombre,
        director: pelicula.director,
        clasificacion: pelicula.clasificacion
    });

    p1 = peli1.save().then(resultado => {
        console.log("Peli añadida: " + resultado);
    }).catch(err => {
        console.log("Error : " + err);
    });

});*/
/*
Promise.all([p1]).then(values => {
    mongoose.connection.close();
})*/