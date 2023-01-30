package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Ciclista;

public class _05HQLBasicas {

    public static void main(String[] args) {

        String q1 = query1();
        String q2 = query2();
        String q3 = query3();
        
        System.out.println("Ciclistas nacidos ente 1-1-1979 y 31-12-1980");
        System.out.println(q1);
        System.out.println("Total de ciclistas:");
        System.out.println(q2);
        System.out.println("Total de ciclistas en el equipo Banesto:");
        //cambiado a marvel porque en otra prueba cambie todos los ciclistas de Banesto a marvel
        System.out.println(q3);

    }

    public static String query1(){

        String cad = "";

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Ciclista C WHERE C.nacimiento BETWEEN '1979-01-01' AND '1980-12-31'");
            //Query query = session.createQuery("from Ciclista C WHERE C.nacimiento BETWEEN :min AND :max");
            //query.setParameter("min", "1979-01-01");
            //query.setParameter("max", "1980-12-31");
            ArrayList<Ciclista> ciclistas = (ArrayList<Ciclista>) query.list();

            for (Ciclista ciclista : ciclistas){
                cad += "\n" + ciclista.getDorsal() + ", " + ciclista.getNombre();
            }

            //session.save(ciclista);

            session.getTransaction().commit();
            session.close();

            //System.out.println(cad);

            return cad;

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
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

            //Query query = session.createQuery("from Ciclista C WHERE C.nacimiento BETWEEN '1979-01-01' AND '1980-12-31'");
            Query query = session.createQuery("from Ciclista");
            //query.setParameter("min", "1979-01-01");
            //query.setParameter("max", "1980-12-31");
            ArrayList<Ciclista> ciclistas = (ArrayList<Ciclista>) query.list();

            int num = 0;
            for (Ciclista ciclista : ciclistas){
                num++;
            }

            //session.save(ciclista);

            session.getTransaction().commit();
            session.close();

            //System.out.println(cad);

            return cad+num;

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }

        return cad;

    }

    public static String query3(){

        String cad = "";

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Ciclista WHERE nomeq = 'marvel'");
            ArrayList<Ciclista> ciclistas = (ArrayList<Ciclista>) query.list();

            int i = 0;
            for (Ciclista ciclista : ciclistas){
                i++;
            }

            //session.save(ciclista);

            session.getTransaction().commit();
            session.close();

            //System.out.println(cad);

            return cad+i;

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }

        return cad;

    }

}
