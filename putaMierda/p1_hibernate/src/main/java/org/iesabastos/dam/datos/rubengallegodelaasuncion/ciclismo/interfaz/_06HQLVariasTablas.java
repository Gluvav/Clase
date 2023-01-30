package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Equipo;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Etapa;

public class _06HQLVariasTablas {

    public static void main(String[] args) {
        
        String q1 = query1();
        String q2 = query2();

        System.out.println(q1);
        System.out.println(q2);

    }

    public static String query1(){

        String cad = "";

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Etapa");
            ArrayList<Etapa> etapas = (ArrayList<Etapa>) query.list();

            for (Etapa etapa : etapas){
                cad += "\nCiudad de salida: " + etapa.getSalida() + "\nCiudad de llegada: " + etapa.getLlegada() + "\nNombre ciclista ganador: " + etapa.getGanador().getNombre();
            }

            //session.save(ciclista);

            session.getTransaction().commit();
            session.close();

            return cad;

            //System.out.println(cad);

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
        }

        return cad;

    }
    
    public static String query2(){
        
        String cad = "";

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Equipo");
            ArrayList<Equipo> equipos = (ArrayList<Equipo>) query.list();

            for (Equipo equipo : equipos){
                cad += "\nNombre del equipo: " + equipo.getNomeq() + "\nDirector: " + equipo.getDirector() + "\nCiclistas del equipo: " + equipo.getCiclistas().size();
            }

            //session.save(ciclista);

            session.getTransaction().commit();
            session.close();

            //System.out.println(cad);

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }

        return cad;
    }

}
