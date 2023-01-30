package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Equipo;

public class _03EliminarEquipo {
    
    public static void main(String[] args) {
        
        Scanner str = new Scanner(System.in);

        System.out.println("Introduce el nombre del equipo a eliminar:");
        String nomeq = str.nextLine();

        deleteEquipo(nomeq);

    }

    public static void deleteEquipo(String nomeq) {

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Equipo equipo = (Equipo) session.get(Equipo.class, nomeq);

            session.delete(equipo);

            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }
        
        System.out.println("Equipo eliminado.");
    }

}
