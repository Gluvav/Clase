
function coinFlip() {
    return new Promise(function (resolve, reject) {
        let rand = Math.floor(Math.random() * 2);
        if (rand == 0) {
            resolve("cara");
        }
        else {
            reject("cruz");
        }
    })
}

coinFlip().then(result => {
    console.log(result);
}).catch(error => {
    console.log(error);
});

function portero(edad) {
    return new Promise(function (resolve, reject) {
        if (edad >= 18) {
            resolve("Puedes pasar.");
        } else {
            reject("No menores.");
        }
    })
}


portero(14).then(result => {
    console.log(result);
}).catch(error => {
    console.log(error);
});
portero(24).then(result => {
    console.log(result);
}).catch(error => {
    console.log(error);
});

function password(pass1, pass2){
    return new Promise(function (resolve, reject) {
        if(pass1 == pass2){
            resolve("Password accepted");
        }else{
            reject("Invalid password");
        }
    })
}

password("caracola", "caracola").then(result => {
    console.log(result);
}).catch(error => {
    console.log(error);
});