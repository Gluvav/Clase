package Banca;

public class Cuenta {

    private int dinero = 0;
    private int maxDin = 0;
    public Cuenta(int dine, int maxD){ //en el constructor declaramos los valores por defecto que tendra la clase
        this.dinero = dine;
        this.maxDin = maxD;
    }
    //ne ingresar, entra la cantidad a ingresar y el nombre de la persona que va ha ingresar el dinero
    public String ingresar(int ing, String nom){
        //lo añade al dinero y debuelve la cadena.
        dinero = dinero + ing;
        return nom + " ingresa => " + ing + ". Saldo actual: " + verSaldo();
    }
    //igual que con ingresar solo que quitandole el int que entra al dinero.
    public String sacar(int saca, String nom){
        dinero = dinero - saca;
        return nom + " retira => " + saca + ". Saldo actual: " + verSaldo();
    }
    //getters para ver el saldo y el maximo.
    public  int verSaldo(){
        return dinero;
    }
    public int verMaxDin(){
        return maxDin;
    }
}
