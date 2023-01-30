package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Equipo;

public class _02aCrearEquipo {

    static Boolean nomeqBool = false;
    static Boolean direcBool = false;

    public static void main(String[] args) {

        Scanner scstr = new Scanner(System.in);
        //Scanner nums = new Scanner(System.in);

        String nomeq="";
        while (nomeqBool != true) {
            System.out.println("Introduce el nombre del equipo:");
            nomeq = scstr.nextLine();
            if (notInDatabase(nomeq) == true) {
                nomeqBool = true;
            } else {
                nomeqBool = false;
            }
        }
        String director2="";
        while (direcBool != true) {
            System.out.println("Introduce el nombre del director:");
            director2 = scstr.nextLine();
            if (notInDatabase(director2) == true) {
                direcBool = true;
            } else {
                direcBool = false;
            }
        }
        createEquipo(nomeq, director2);
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
                    return false;
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
        return true;

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
