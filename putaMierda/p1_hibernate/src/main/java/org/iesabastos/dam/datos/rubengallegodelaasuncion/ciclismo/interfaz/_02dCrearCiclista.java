package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Ciclista;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Equipo;

public class _02dCrearCiclista {

    public static void main(String[] args) throws ParseException {

        Scanner str = new Scanner(System.in);
        Scanner num = new Scanner(System.in);

        System.out.println("Introduce el numero dorsal del ciclista.");
        int dorsal = num.nextInt();
        System.out.println("Introduce el nombre del ciclista.");
        String nombre = str.nextLine();
        System.out.println("Introduce el nombre del equipo del ciclista.");
        String nomeq = str.nextLine();
        //System.out.println("Fecha de nacimiento del ciclista. (yyyy-mm-dd)");
        //String fnac = str.nextLine();

        switch (crearCiclista(dorsal, nombre, nomeq)){
            case 0:
                System.out.println("\n\n\nCiclista añadido sin problemas.\n\n\n");
                break;
            case 1:
                System.out.println("\n\n\nError, el dorsal introducido y aesta asignado.\n\n\n\n");
                break;
            case 2:
                System.out.println("\n\n\nError, el equipo introducido no existe.\n\n\n");
                break;
        }

    }

    public static int crearCiclista(int dorsal, String nombre, String nomeq){
        /*
        0=>todo ok.
        1=>ciclista ya existe
        2=>equipo no existe
        */

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Ciclista");
            ArrayList<Ciclista> ciclistas = (ArrayList<Ciclista>) query.list();

            Query query2 = session.createQuery("from Equipo");
            ArrayList<Equipo> equipos = (ArrayList<Equipo>) query2.list();

            int a = 0, b = 0;
            for (int i = 0; i < ciclistas.size(); i++) {
                if (ciclistas.get(i).getDorsal() == dorsal){
                    a++;
                }
            }
            for (int i = 0; i < equipos.size(); i++) {
                if (equipos.get(i).getNomeq().equals(nomeq)){
                    b++;
                }
            }

            if (a > 0){
                return 1;
            } else if (b == 0){
                return 2;
            }

            Equipo equipo = (Equipo) session.get(Equipo.class, nomeq);

            Ciclista ciclista = new Ciclista();

            ciclista.setDorsal(dorsal);
            ciclista.setNombre(nombre);
            ciclista.setEquipo(equipo);

            session.save(ciclista);

            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }

        return 0;
    }
    
}
