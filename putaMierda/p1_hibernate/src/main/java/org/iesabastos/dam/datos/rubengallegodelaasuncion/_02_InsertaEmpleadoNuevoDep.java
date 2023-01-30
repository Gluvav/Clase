package org.iesabastos.dam.datos.rubengallegodelaasuncion;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;

public class _02_InsertaEmpleadoNuevoDep {

    public _02_InsertaEmpleadoNuevoDep(int emp_no, String nombre, String oficio, Date fecha_alta, float salario, float comision, int dept_NO, String dnombre, String dloc) {
        
        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Empleado empleado = new Empleado();
            empleado.setEmp_no(emp_no);
            empleado.setNombre(nombre);
            empleado.setOficio(oficio);
            empleado.setFecha_alta(fecha_alta);
            empleado.setSalario(salario);
            empleado.setComision(comision);
            empleado.setDepartamento(new Departamento(dept_NO, dnombre, dloc));

            session.save(empleado);

            session.getTransaction().commit();
            session.close();
            System.out.println("Fin del programa");

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }

    }

    public static void main(String[] args) {
        new _02_InsertaEmpleadoNuevoDep(420, "Weed", "Dealer", null, 4200, 0, 25, "Weed", "Lab01");
    }
    
}
