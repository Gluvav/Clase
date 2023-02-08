//apuntes para el examen de JS - DI 

//Arrays:
//declaracion
function conNew() {
    let array = new Array();
    array[0] = 13;
    console.log(array[0]);
}
function conCorchete() {
    let array = ["a", "b", "c", "d", "e", "f", "g"];
    console.log(array);
}

//insertar valores
function unshiftPush() {
    let a = [];
    a.push(13);
    a.push("a", "b", "c");
    console.log(a);//muestra 13, a, b, c
    a.unshift(12);
    console.log(a);//muestra 12, 13, a, b, c
}

//eliminar valores
function shiftPop() {
    let a = ["A", "B", "C", "a", "b", "c", "d"];
    console.log(a.pop()); // Imprime y elimina la última posición → "d"
    console.log(a.shift()); // Imprime y elimina la primera posición → "A"
    console.log(a); // Imprime ["B", "C", "a", "b", "c"]
}

//show vectors on screen
function showScreen() {
    let a = [3, 21, 15, 61, 9];
    console.log(a.toString());// Imprime "3,21,15,61,9"
    //join(), por defecto separados por comas, se puede especificar el tipo de separador
    let a2 = [3, 21, 15, 61, 9];
    console.log(a.join()); // Imprime "3,21,15,61,9", igual que toString()
    console.log(a.join(" -#- ")); // Imprime "3 -#- 21 -#- 15 -#- 61 -#- 9"
}

//concadenar
function concadenar() {
    let a = ["a", "b", "c"];
    let b = ["d", "e", "f"];
    let c = a.concat(b);
    console.log(c); // Imprime ["a", "b", "c", "d", "e", "f"]
    console.log(a); // Imprime ["a", "b", "c"] . El array a no ha sido modificado
}
//trocear los vectores slice y splice
//slice
function slice() {
    let a = ["a", "b", "c", "d", "e", "f"];
    let b = a.slice(1, 3); // (posición de inicio → incluida, posición final → excluida)
    console.log(b); // Imprime ["b", "c"]
    console.log(a); // Imprime ["a", "b", "c", "d", "e", "f"]. El array original no es modificado
    console.log(a.slice(3)); // Un parámetro. Devuelve desde la posición 3 al final → ["d", "e", "f"]
}
//splice
function splice() {//.splice(desdeDonde, cuantos);
    let a = ["a", "b", "c", "d", "e", "f"];
    a.splice(1, 3); // Elimina 3 elementos desde la posición 1 ("b", "c", "d")
    console.log(a); // Imprime ["a", "e", "f"]
    a.splice(1, 1, "g", "h"); // Elimina 1 elemento en la posición 1 ("e"), e inserta "g", "h" en esa posición
    console.log(a); // Imprime ["a", "g", "h", "f"]
    a.splice(3, 0, "i"); // En la posición 3, no elimina nada, e inserta "i"
    console.log(a); // Imprime ["a", "g", "h", "i", "f"]
}

//ordenar vectores
function reverse() {
    let a = ["a", "b", "c", "d", "e", "f"];
    a.reverse(); // Hace el reverse del array original
    console.log(a); // Imprime ["f", "e", "d", "c", "b", "a"]
}
function sort() {
    let a = ["Peter", "Anne", "Thomas", "Jen", "Rob", "Alison"];
    a.sort(); // Ordena el array original
    console.log(a); // Imprime ["Alison", "Anne", "Jen", "Peter", "Rob", "Thomas"]
}
function arrayLength() {
    let cadenas = ["Ruben", "Fran", "Patrick", "Zahra", "Paula", "Coraima"];
    let orig = cadenas;
    console.log("Original: " + orig);
    cadenas.sort((a, b) => a.length - b.length);
    console.log("Ordenado :" + cadenas);
}

//buscar en el array
function indexOf() {
    let a = [3, 21, 15, 61, 9, 15];
    console.log(a.indexOf(15)); 
    // Imprime que lo ah encontrado 2 veces
    console.log(a.indexOf(56)); 
    // Imprime -1. No encontrado

}
function lasIndexOf() {
    let a = [3, 21, 15, 61, 9, 15];
    console.log(a.lastIndexOf(15));
    // Imprime la posicion del primer elemento que coincide empezando desde el final, en este caso, 5
}

//buscando con condiciones
function conEvert() {
    let a = [3, 21, 15, 61, 9, 54];
    console.log(a.every(function (num) { 
        // Comprueba si cada número es menor a 100
        return num < 100;
    })); // Imprime true
    console.log(a.every(function (num) { 
        // Comprueba si cada número es par
        return num % 2 == 0;
    })); // Imprime false

}
function conSome() {
    let a = [3, 21, 15, 61, 9, 54];
    console.log(a.some(function (num) { // Comprueba si algún elemento del array es par
        return num % 2 == 0;
    })); // Imprime true
}
//filter
function conFilter() {
    let a = [4, 21, 33, 12, 9, 54];
    console.log(a.filter(function (num) {
        return num % 2 == 0; 
        // Si devuelve true, el elemento 
        //se queda en el array devuelto
    })); // Imprime [4, 12, 54]
}

//formato JSON
function formatoJson() {
    let datos = [
        { nombre: "Nacho", telefono: "966112233", edad: 40 },
        { nombre: "Ana", telefono: "911223344", edad: 35 },
        { nombre: "Mario", telefono: "611998877", edad: 15 },
        { nombre: "Laura", telefono: "633663366", edad: 17 }
    ];
}
//promesas Ejercicios Promesas/arrow_functions.js y masPromesas.js


//modulos en node
function nNode(){
    const ruta = 'C:/\Program Files';
    const fs = require('fs');
    fs.readdirSync(ruta).forEach(fichero => {
        console.log(fichero);
    })
}
nNode();