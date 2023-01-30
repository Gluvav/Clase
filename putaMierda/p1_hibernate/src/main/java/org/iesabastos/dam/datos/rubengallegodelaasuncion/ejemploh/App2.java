package org.iesabastos.dam.datos.rubengallegodelaasuncion;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;

public class App2 {

    public static void main(String[] args) {

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Empleado empleado = new Empleado();
            empleado.setEmp_no(6);
            empleado.setNombre("Ruben");
            empleado.setOficio("Profesor");
            empleado.setFecha_alta(null);
            empleado.setSalario(1200);
            empleado.setComision(0.00f);

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
    
}
