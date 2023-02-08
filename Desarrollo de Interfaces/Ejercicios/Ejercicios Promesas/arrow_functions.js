
//EJ1
let datos = [
    { nombre: "Nacho", telefono: "966112233", edad: 40 },
    { nombre: "Ana", telefono: "911223344", edad: 35 },
    { nombre: "Mario", telefono: "611998877", edad: 15 },
    { nombre: "Laura", telefono: "633663366", edad: 17 }
];

//añadir los datos
/*function nuevaPersona({ nombre, telefono, edad }) {
    datos.push({ nombre, telefono, edad })
}*/
function nuevaPersona(persona) {
    return new Promise(function (resolve, reject) {
        let a = 0;
        for (let i = 0; i < datos.length; i++) {
            if (datos[i].telefono == persona.telefono) {
                a++;
            }
        }
        if (a == 0) {
            datos.push(persona);
            resolve("Añadido: " + JSON.stringify(persona));
        }
        else {
            reject("Telefono duplicado");
        }
    })
}

nuevaPersona({ nombre: "Juan", telefono: "912246668", edad: 60 }).then(result => {
    console.log("Añadiendo...");
    console.log(result);
}).catch(error => {
    console.log("Error: ", error);
});

nuevaPersona({ nombre: "Rodolfo", telefono: "910011001", edad: 20 }).then(result => {
    console.log("Añadiendo...");
    console.log(result);
}).catch(error => {
    console.log("Error: ", error);
});

for (let i = 0; i < datos.length; i++) {
    console.log(datos[i]);
}
console.log("------------")
/*
//borrar el dato especificado
function borrarPersona(telefonoBorrar) {
    for (let i = 0; i < datos.length; i++) {
        if (datos[i].telefono == telefonoBorrar) {
            datos.splice(i, 1);
        }
    }
}
*/

function borrarPersona(telefonoBr) {
    return new Promise(function (resolve, reject) {
        let a;
        for (let i = 0; i < datos.length; i++) {
            if (datos[i].telefono == telefonoBr) {
                a = i;
            }
        }
        if (a >= 0) {
            datos.splice(a, 1);
            resolve("Eliminado.");
        }
        else {
            reject("Numero no encontrado.");
        }
    })
}

borrarPersona("910011001").then(result => {
    console.log("Eliminando...");
    console.log(result);
}).catch(error => {
    console.log("Error: " + error)
});

for (let i = 0; i < datos.length; i++) {
    console.log(datos[i]);
}
console.log("------------")