let datos = [
    { nombre: "Nacho", telefono: "966112233", edad: 40 },
    { nombre: "Ana", telefono: "911223344", edad: 35 },
    { nombre: "Mario", telefono: "611998877", edad: 15 },
    { nombre: "Laura", telefono: "633663366", edad: 17 }
];

const pers = require("./personas");
for (let i = 0; i < datos.length; i++) {
    console.log(datos[i]);
}
console.log("----------");

pers.nuevaPersona(datos, { nombre: "Juan", telefono: "912246668", edad: 60 }).then(result => {
    console.log("Añadiendo...");
    console.log(result);
}).catch(error => {
    console.log("Error: ", error);
});

pers.nuevaPersona(datos, { nombre: "Rodolfo", telefono: "910011001", edad: 20 }).then(result => {
    console.log("Añadiendo...");
    console.log(result);
}).catch(error => {
    console.log("Error: ", error);
});

for (let i = 0; i < datos.length; i++) {
    console.log(datos[i]);
}


pers.borrarPersona(datos, "910011001").then(result => {
    console.log("Eliminando...");
    console.log(result);
}).catch(error => {
    console.log("Error: " + error)
});

for (let i = 0; i < datos.length; i++) {
    console.log(datos[i]);
}