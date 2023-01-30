package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Puerto;

public class _01c01GanadorDePuerto {
    
    public _01c01GanadorDePuerto(String nomPuerto) {

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Puerto puerto = (Puerto) session.get(Puerto.class, nomPuerto);

            System.out.println(puerto.getGanador().getNombre());

            // session.save(ciclistas);

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
        new _01c01GanadorDePuerto("Angliru");
    }
}
