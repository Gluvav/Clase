package examen_recu;

import java.io.Serializable;

public class Coche implements Serializable {

    String name;
    String color;

    public Coche(String nom, String col){
        this.color = col;
        this.name = nom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
