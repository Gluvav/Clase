package ies.abastos.clase.datos;

public class fabricas {
    
    private int cod;
    private String nombre;


    public fabricas(int cod, String nombre) {
        this.cod = cod;
        this.nombre = nombre;
    }


    public int getCod() {
        return this.cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
