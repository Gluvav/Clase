package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Equipo;

public class _02bModificarEquipo {

    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);

        System.out.println("Dime el nombre del Equipo:");
        String nomeq = str.nextLine();
        System.out.println("Dime el nombre del nuevo Director:");
        String director = str.nextLine();

        modificarCiclista(nomeq, director);
    }

    public static void modificarCiclista(String nomeq, String director){
        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Equipo equipo = (Equipo) session.get(Equipo.class, nomeq);

            equipo.setDirector(director);

            session.save(equipo);

            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }
    }
    
}
