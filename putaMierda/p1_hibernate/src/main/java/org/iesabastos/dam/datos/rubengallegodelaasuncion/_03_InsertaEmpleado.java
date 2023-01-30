package org.iesabastos.dam.datos.rubengallegodelaasuncion;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;

public class _03_InsertaEmpleado {

    public _03_InsertaEmpleado(int emp_no, String nombre, String oficio, Date fecha_alta, float salario, float comision, int id) {

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
            Departamento depa = (Departamento) session.get(Departamento.class, id);
            empleado.setDepartamento(depa);

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
        new _03_InsertaEmpleado(38, null, null, null, 30, 0, 42);
    }
    
}
