package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "etapa")
public class Etapa {
    
    @Id
    private int netapa;
    private int km;
    private String salida;
    private String llegada;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="dorsal")
    private Ciclista ganador;

    @OneToMany(mappedBy = "nompuerto", cascade = CascadeType.ALL)
    private List<Puerto> puertos = new ArrayList<Puerto>();

    public Etapa() {
        //
    }

    public int getNetapa() {
        return netapa;
    }

    public void setNetapa(int netapa) {
        this.netapa = netapa;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getLlegada() {
        return llegada;
    }

    public void setLlegada(String llegada) {
        this.llegada = llegada;
    }

    public Ciclista getGanador() {
        return ganador;
    }

    public void setGanador(Ciclista ciclista) {
        this.ganador = ciclista;
    }

    public List<Puerto> getPuertos() {
        return puertos;
    }

    public void setPuertos(List<Puerto> puertos) {
        this.puertos = puertos;
    }
    
}
