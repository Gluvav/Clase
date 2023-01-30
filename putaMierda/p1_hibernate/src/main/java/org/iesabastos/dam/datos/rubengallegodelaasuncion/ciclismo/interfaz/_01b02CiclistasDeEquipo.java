package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Ciclista;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Equipo;

public class _01b02CiclistasDeEquipo {

    public _01b02CiclistasDeEquipo(String nomeq) {
        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Equipo equipo = (Equipo) session.get(Equipo.class, nomeq);
            for (int i = 0; i < equipo.getCiclistas().size(); i++) {
                if ((equipo.getCiclistas().get(i).getEquipo().getNomeq()).equals(equipo.getNomeq())) {
                    System.out.println(equipo.getCiclistas().get(i).getNombre());
                }
            }

            session.save(equipo);

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
        new _01b02CiclistasDeEquipo("Amore Vita");
    }

}
