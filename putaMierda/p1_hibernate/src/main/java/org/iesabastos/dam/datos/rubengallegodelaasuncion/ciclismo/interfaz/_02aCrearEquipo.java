package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Equipo;

public class _02aCrearEquipo {

    static Boolean nomeqBool = true;
    static Boolean direcBool = true;

    public static void main(String[] args) {

        Scanner str = new Scanner(System.in);
        Scanner nums = new Scanner(System.in);

        String nomeq;
        while (nomeqBool != false) {
            System.out.println("Introduce el nombre del equipo:");
            nomeq = str.nextLine();
            if (notInDatabase(nomeq) != true) {
                nomeqBool = true;
            } else {
                nomeqBool = false;
            }
        }
        String director;
        while (nomeqBool != false) {
            System.out.println("Introduce el nombre del director:");
            director = str.nextLine();
            if (notInDatabase(director) != true) {
                direcBool = true;
            } else {
                direcBool = false;
            }
        }

        createEquipo(nomeq, director);

    }

    public static boolean notInDatabase(String str) {

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Equipo");
            ArrayList<Equipo> puertos = (ArrayList<Equipo>) query.list();

            for (int i = 0; i < puertos.size(); i++) {
                if ((puertos.get(i).getNomeq()).equals(str)) {
                    System.out.println("Nombre de Equipo ya existe.");
                    return true;
                }
            }

            // session.save(ciclistas);

            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }
        return false;

    }

    public static void createEquipo(String nomeq, String director) {

        HibernateUtil.buildSessionFactory();
        HibernateUtil.openSession();
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();

        Equipo equipo = new Equipo();
        equipo.setNomeq(nomeq);
        equipo.setDirector(director);

        sesion.save(equipo);

        sesion.getTransaction().commit();
        sesion.close();

    }
}
