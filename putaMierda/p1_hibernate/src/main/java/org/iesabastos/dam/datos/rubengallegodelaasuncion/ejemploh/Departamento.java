package org.iesabastos.dam.datos.rubengallegodelaasuncion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="departamentos")
public class Departamento {

    @Id
    private int dept_NO;
    private String dnombre;
    private String loc;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Empleado> empleados = new ArrayList<Empleado>();

    public Departamento(int dept_NO, String dnombre, String loc) {
        this.dept_NO = dept_NO;
        this.dnombre = dnombre;
        this.loc = loc;
    }

    public Departamento() {
        //
    }

    public int getDept_NO() {
        return dept_NO;
    }

    public void setDept_NO(int dept_NO) {
        this.dept_NO = dept_NO;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    
}
