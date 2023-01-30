package org.iesabastos.dam.datos.rubengallegodelaasuncion;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;

public class _04_ListadoDep {

    public _04_ListadoDep() {

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Empleado");
            ArrayList<Empleado> empleados = (ArrayList<Empleado>) query.list();

            for (Empleado em : empleados) {
                if (em.getDepartamento().getDept_NO() == 10) {
                    System.out.println(em.getEmp_no() + ", " + em.getNombre());
                }
            }

            // session.save(ciclista);

            session.getTransaction().commit();
            session.close();

            // System.out.println(cad);

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }

    }

    public static void main(String[] args) {
        new _04_ListadoDep();
    }

}
