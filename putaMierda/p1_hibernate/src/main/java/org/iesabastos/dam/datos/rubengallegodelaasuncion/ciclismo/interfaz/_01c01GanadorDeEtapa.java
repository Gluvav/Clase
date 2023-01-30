package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Etapa;

public class _01c01GanadorDeEtapa {

    public _01c01GanadorDeEtapa(int netapa){
        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Etapa etapa = (Etapa) session.get(Etapa.class, netapa);

            System.out.println("Dorsal del ganador: " + etapa.getGanador().getDorsal());

            session.save(etapa);

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
        new _01c01GanadorDeEtapa(10);
    }
    
}
