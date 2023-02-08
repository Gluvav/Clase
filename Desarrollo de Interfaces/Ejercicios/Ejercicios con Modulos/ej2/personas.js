function nuevaPersona(datosN, persona) {
    let datos = datosN;
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
////////////////////////////////

function borrarPersona(datosN, telefonoBr) {
    let datos = datosN;
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

module.exports={
    nuevaPersona: nuevaPersona,
    borrarPersona: borrarPersona
}