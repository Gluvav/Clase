package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="puerto")
public class Puerto {

    @Id
    private String nompuerto;
    private int altura;
    private char categoria;
    private double pendiente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dorsal")
    private Ciclista ganador;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "netapa")
    private Etapa etapa;

    public Puerto() {
        //
    }

    public String getNompuerto() {
        return nompuerto;
    }

    public void setNompuerto(String nompuerto) {
        this.nompuerto = nompuerto;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public char getCategoria() {
        return categoria;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    public double getPendiente() {
        return pendiente;
    }

    public void setPendiente(double pendiente) {
        this.pendiente = pendiente;
    }

    public Ciclista getGanador() {
        return ganador;
    }

    public void setGanador(Ciclista ganador) {
        this.ganador = ganador;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }
    
}
