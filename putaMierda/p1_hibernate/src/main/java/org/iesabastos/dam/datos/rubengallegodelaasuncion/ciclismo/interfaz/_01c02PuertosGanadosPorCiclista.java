package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Puerto;

public class _01c02PuertosGanadosPorCiclista {

    public _01c02PuertosGanadosPorCiclista(int dorsal) {

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Puerto");
            ArrayList<Puerto> puertos = (ArrayList<Puerto>) query.list();

            for (int index = 0; index < puertos.size(); index++) {
                if (puertos.get(index).getGanador().getDorsal() == dorsal){
                    System.out.println(puertos.get(index).getNompuerto() + ", " + puertos.get(index).getAltura() + ", " + puertos.get(index).getCategoria());
                }
            }

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
        new _01c02PuertosGanadosPorCiclista(1);
    }
    
}
