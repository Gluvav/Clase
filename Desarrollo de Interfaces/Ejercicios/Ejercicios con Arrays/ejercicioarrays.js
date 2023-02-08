let datos = [
    { nombre: "Nacho", telefono: "966112233", edad: 40 },
    { nombre: "Ana", telefono: "911223344", edad: 35 },
    { nombre: "Mario", telefono: "611998877", edad: 15 },
    { nombre: "Laura", telefono: "633663366", edad: 17 }
];
datos.push(
    { nombre: "Pedro", telefono: "611944444", edad: 25 },
    { nombre: "Julia", telefono: "633232323", edad: 37 }
);

for (let i = 0; i < datos.length; i++) {
    console.log(datos[i]);
}
console.log("--- Ordenados por edad: ---")
//console.log(datos[0].nombre);
let origDatos = datos;
for (let i = 0; i < datos.length; i++) {
    datos.sort((a, b) => a.edad - b.edad);
    console.log(datos[i]);
}
console.log("--- Ordenados por nombre: ---")
for (let i = 0; i < datos.length; i++) {
    datos.sort((a, b) => {
        const nameA = a.nombre.toUpperCase(); // ignore upper and lowercase
        const nameB = b.nombre.toUpperCase(); // ignore upper and lowercase
        if (nameA < nameB) {
            return -1;
        }
        if (nameA > nameB) {
            return 1;
        }
        return 0;
    });
    console.log(datos[i])
}
console.log("--- Mayores de 30: ---")
let more30 = new Array;
for (let i = 0; i < datos.length; i++) {
    if (datos[i].edad >= 30) {
        more30.push(datos[i]);
        console.log(more30[i]);
    }
}