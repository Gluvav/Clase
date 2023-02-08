"use strict"

//ap1
function howLong(string1, string2) {
    if (string1.length > string2.length) {
        console.log(string1);
    }
    else if (string2.length > string1.length) {
        console.log(string2);
    }
    else {
        console.log("Error, no string detected, please input a string.")
    }
}
howLong("hola", "adios");

//ap2
function multi(howMany, wichNumber) {
    for (let i = 0; i < howMany; i++) {
        console.log(wichNumber);
        wichNumber *= 2;
    }
}
multi(4, 6);

//ap3
function manyTimes(string, char) {
    if (char.length != 1) {
        console.log("Character to search is not 1 character long")
    }
    else {
        let numTimes = 0;
        for (let i = 0; i < string.length; i++) {
            if (string.charAt(i).valueOf() === char.valueOf()) {
                numTimes++;
            }
        }
        console.log("Aparece " + numTimes);
    }
}
manyTimes("caracola", "a");

//ap4
function impuesto(nombre, precio, impuesto) {
    if (nombre.length == 0) {
        nombre = "Generic product";
    }
    else if (precio <= 0) {
        precio = 100;
    }
    else if (impuesto <= 0) {
        impuesto = 21;
    }
    else {
        if (isNaN(precio) || isNaN(impuesto)) {
            console.log("Numero no valido");
        }
        else {
            String(nombre);
            parseInt(precio);
            parseInt(impuesto);
            let a = precio * (impuesto / 100);
            precio = precio + a;
            console.log(nombre + "-  Total: " + precio + " - Impuestos: " + a);
        }
    }
}
impuesto("Zapatillas Nike", 235, 21);

//ap5
let strings = (str1, str2) => {
    let posi = str1.toLowerCase().search(str2.toLowerCase());
    if (posi > 0) {
        console.log("String encontrada en " + posi + ".");
    }
    else
        console.log("No se ha encontrado la string.");
}
strings("zapatillas", "til");

//ap6
function arrys() {
    let arry1 = [1, 2, 3, 4];
    console.log(arry1);

    arry1.unshift(-1, 0);
    arry1.push(5, 6);
    console.log(arry1);

    arry1.splice(3, 3);
    console.log(arry1);

    let a = arry1[arry1.length - 1];
    arry1.push(a);
    arry1[arry1.length - 1] = arry1[arry1.length - 1] + 1;
    console.log(arry1);
    console.log(arry1.join("=>"));
}
arrys();

//ap7
function medias() {
    let sumNotas = 0;
    let numNotas = 0;
    for (let i = 1; i < arguments.length; i++) {
        sumNotas += arguments[i];
        numNotas++;
    }
    let media = sumNotas / numNotas;
    console.log("Nota final de " + arguments[0] + ": " + media);
}
medias("Pepe", 4.5, 5, 9, 10);

//ap8
function ordenar() {
    let cadenas = ["Ruben", "Fran", "Patrick", "Zahra", "Paula", "Coraima"];
    let orig = cadenas;
    console.log("Original: " + orig);
    cadenas.sort((a, b) => a.length - b.length);
    console.log("Ordenado :" + cadenas);
}
ordenar();

//ap9
function arraySeparado() {
    let arraCifras = ["123", "34", "52"];
    let separado1 = Array.from(arraCifras[0]);
    let sumador1 = parseInt(separado1[0]) + parseInt(separado1[1]) + parseInt(separado1[2]);
    let separado2 = Array.from(arraCifras[1]);
    let sumador2 = parseInt(separado2[0]) + parseInt(separado2[1]);
    let separado3 = Array.from(arraCifras[2]);
    let sumador3 = parseInt(separado3[0]) + parseInt(separado3[1]);
    let arraFinal = [sumador1, sumador2, sumador3]

    console.log(arraCifras);
    console.log(arraFinal);
}
arraySeparado();

//ap10
function areaTriangulo(ang1, ang2, grado) {
    let seno = (grado * 3.1416) / 180;
    let final = 0.5 * ang1 * ang2 * seno;
    console.log(final);
}
areaTriangulo(15, 15, 80);

//ap11
function fecha(amd) {

    let fecha = new Date(amd);
    let araryDias = ["Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"];
    let arrayMeses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre",
        "Nobiembre", "Diciembre"];
    console.log(araryDias[fecha.getDay()] + ", " + fecha.getDate() +
        " de " + arrayMeses[fecha.getMonth()] + " de " + fecha.getFullYear());
}
fecha("2000-12-24");

//ap12
function fechaApi(intl) {
    let fecha = new Date(intl);
    console.log(new Intl.DateTimeFormat('es-ES', { dateStyle: 'full' }).format(fecha))
}
fechaApi("2000-12-24");