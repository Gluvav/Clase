let espacio = "*"
console.log("Diagonal Inversa N=20");
for (let i = 0; i<20; i++){
    for (let j = 20; j>i; j--){
        let a = " "
        espacio = a+espacio
    }
    console.log(espacio)
    espacio = "*"
}