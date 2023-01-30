const mongoose = require('mongoose');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/contactosTarde', {
    useNewUrlParser: true,
    useUnifiedTopology: true
});

let contactoSchema = new mongoose.Schema({
    nombre: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    telefono: {
        type: String,
        required: true,
        trim: true,
        match: /^\d{9}$/
    },
    edad: {
        type: Number,
        min: 18,
        max: 120
    }
});

let Contacto = mongoose.model('contactosTarde', contactoSchema);

let contacto1 = new Contacto({
    nombre: "Paula",
    telefono: "777555111",
    edad: 25
});

let p1 = contacto1.save().then(resultado => {
    console.log("Contacto añadido: " + resultado);
}).catch(err => {
    console.log("Error: " + err);
});

let p2 = Contacto.find().then(resultado => {
    console.log(resultado);
}).catch(err => {
    console.log("Error: " + err);
});

let p3 = Contacto.find({nombre: "Paula", edad: 25}).then(resultado => {
    console.log(resultado);
}).catch(err => {
    console.log("Error: " + err);
});

let p4 = Contacto.deleteOne({nombre: "Rubén"}).then(resultado => {
    console.log(resultado);
}).catch(err => {
    console.log("Error: " + err);
});

Promise.all([p1, p2, p3, p4]).then(values => {
    mongoose.connection.close();
})