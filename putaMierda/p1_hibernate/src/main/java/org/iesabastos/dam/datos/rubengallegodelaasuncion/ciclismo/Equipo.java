package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="equipo")
public class Equipo {
    
    @Id
    private String nomeq;
    private String director;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private List<Ciclista> ciclistas = new ArrayList<Ciclista>();

    public Equipo() {
        //
    }

    public String getNomeq() {
        return nomeq;
    }

    public void setNomeq(String nomeq) {
        this.nomeq = nomeq;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Ciclista> getCiclistas() {
        return ciclistas;
    }

    public void setCiclistas(List<Ciclista> ciclistas) {
        this.ciclistas = ciclistas;
    }
}
