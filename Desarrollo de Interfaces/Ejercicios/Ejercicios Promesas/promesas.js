
//EJ1
let datos = [
    { nombre: "Nacho", telefono: "966112233", edad: 40 },
    { nombre: "Ana", telefono: "911223344", edad: 35 },
    { nombre: "Mario", telefono: "611998877", edad: 15 },
    { nombre: "Laura", telefono: "633663366", edad: 17 }
];

//añadir los datos
function nuevaPersona({ nombre, telefono, edad }) {
    datos.push({ nombre, telefono, edad })
}

nuevaPersona({ nombre: "Juan", telefono: "965661564", edad: 60 });
nuevaPersona({ nombre: "Rodolfo", telefono: "910011001", edad: 20 });

for (let i = 0; i < datos.length; i++) {
    console.log(datos[i]);
}

//borrar el dato especificado
function borrarPersona(telefonoBorrar) {
    for (let i = 0; i < datos.length; i++) {
        if (datos[i].telefono == telefonoBorrar) {
            datos.splice(i, 1);
        }
    }
}

borrarPersona("910011001");
console.log(datos);

for (let i = 0; i < datos.length; i++) {
    console.log(datos[i]);
}
