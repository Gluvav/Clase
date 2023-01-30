package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Ciclista;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Etapa;

public class _01c02EtapasGanadasPorCiclista {

    public _01c02EtapasGanadasPorCiclista(int dorsal) {
        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Etapa");
            ArrayList<Etapa> etapas = (ArrayList<Etapa>) query.list();

            for (int i = 0; i < etapas.size(); i++){
                if (etapas.get(i).getGanador().getDorsal() == dorsal) {
                    System.out.println("\n" + etapas.get(i).getNetapa());
                    System.out.println("Salida: " + etapas.get(i).getSalida());
                    System.out.println("Llegada: " + etapas.get(i).getLlegada());
                }
            }

            //session.save(etapas);

            /*Ciclista ciclista = (Ciclista) session.get(Ciclista.class, dorsal);

            for (int i = 0; i < ciclista.getEtapasGanadas().size(); i++) {
                if (ciclista.getEtapasGanadas().get(i).getGanador().getDorsal() == dorsal){
                    System.out.println("\n\n\n" + ciclista.getEtapasGanadas().get(i).getNetapa());
                    System.out.println("Salida: " + ciclista.getEtapasGanadas().get(i).getSalida() + "\nLlegada: " + 
                    ciclista.getEtapasGanadas().get(i).getLlegada());
                }
            }

            session.save(ciclista);*/

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
        new _01c02EtapasGanadasPorCiclista(2);
    }
    
}
