package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ciclista")
public class Ciclista {

    @Id
    private int dorsal;
    private String nombre;
    private Date nacimiento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomeq")
    private Equipo equipo;

    @OneToMany(mappedBy = "netapa", cascade = CascadeType.ALL)
    private List<Etapa> etapasGanadas = new ArrayList<Etapa>();

    @OneToMany(mappedBy = "nompuerto", cascade = CascadeType.ALL)
    private List<Puerto> puertosGanados = new ArrayList<Puerto>();

    public Ciclista() {
        //
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setEdad(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public List<Etapa> getEtapasGanadas() {
        return etapasGanadas;
    }

    public void setEtapasGanadas(List<Etapa> etapasGanadas) {
        this.etapasGanadas = etapasGanadas;
    }

    public List<Puerto> getPuertosGanados() {
        return puertosGanados;
    }

    public void setPuertosGanados(List<Puerto> puertosGanados) {
        this.puertosGanados = puertosGanados;
    }

}
