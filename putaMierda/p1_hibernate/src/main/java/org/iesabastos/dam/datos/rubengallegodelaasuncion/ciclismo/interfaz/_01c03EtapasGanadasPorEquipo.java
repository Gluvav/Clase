package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Ciclista;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Equipo;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Etapa;

public class _01c03EtapasGanadasPorEquipo {

    public _01c03EtapasGanadasPorEquipo(String nomeq) {
        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            /*
             * Query query = session.createQuery("from Etapa");
             * ArrayList<Etapa> etapas = (ArrayList<Etapa>) query.list();
             * 
             * for (int i = 0; i < etapas.size(); i++){
             * if (etapas.get(i).getGanador().getDorsal() == dorsal) {
             * System.out.println("\n" + etapas.get(i).getNetapa());
             * System.out.println("Salida: " + etapas.get(i).getSalida());
             * System.out.println("Llegada: " + etapas.get(i).getLlegada());
             * }
             * }
             */

            Query query = session.createQuery("from Ciclista");
            ArrayList<Ciclista> ciclistas = (ArrayList<Ciclista>) query.list();

            for (int i = 0; i < ciclistas.size(); i++) {
                if (ciclistas.get(i).getEtapasGanadas().size()>0 && (ciclistas.get(i).getEquipo().getNomeq()).equals(nomeq)){
                    System.out.println("-----\n" + ciclistas.get(i).getNombre() + ": " + ciclistas.get(i).getEtapasGanadas().size() + "\n----");
                }
            }
            
            /*
             * for (Etapa a : etapas) {
             * if ((a.getGanador().getEquipo().getNomeq()).equals(nomeq)){
             * System.out.println(a.getGanador().getNombre() + ", etapas ganadas: " +
             * a.getGanador().getEtapasGanadas().size());
             * }
             * }
             */

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
        new _01c03EtapasGanadasPorEquipo("Banesto");
    }

}
