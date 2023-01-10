package ies.abastos.clase.datos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="pedidos")
public class pedidos implements Serializable{

    @Column
    private int num;
    @Column
    private int codfabrica;
    @Column
    private char codarticulo;
    @Column
    private Date fecha;
    @Column
    private int unidades;
    @Column
    private int urgente;
    @Column
    private int servido;

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCodfabrica() {
        return this.codfabrica;
    }

    public void setCodfabrica(int codfabrica) {
        this.codfabrica = codfabrica;
    }

    public char getCodarticulo() {
        return this.codarticulo;
    }

    public void setCodarticulo(char codarticulo) {
        this.codarticulo = codarticulo;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getUnidades() {
        return this.unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getUrgente() {
        return this.urgente;
    }

    public void setUrgente(int urgente) {
        this.urgente = urgente;
    }

    public int getServido() {
        return this.servido;
    }

    public void setServido(int servido) {
        this.servido = servido;
    }
    
}
