const lodash = require('lodash');

let datos = [
    { nombre: "Nacho", telefono: "966112233", edad: 40 },
    { nombre: "Ana", telefono: "911223344", edad: 35 },
    { nombre: "Mario", telefono: "611998877", edad: 15 },
    { nombre: "Laura", telefono: "633663366", edad: 17 }
];

let datosArray = new Array;

for (let i = 0; i < datos.length; i++) {
    datosArray.push(Object.values(datos[i]));
}
for (let i = 0; i < datosArray.length; i++) {
    console.log(lodash.join(datosArray[i], ','));
}
//console.log(lodash.join(datosArray, ','));