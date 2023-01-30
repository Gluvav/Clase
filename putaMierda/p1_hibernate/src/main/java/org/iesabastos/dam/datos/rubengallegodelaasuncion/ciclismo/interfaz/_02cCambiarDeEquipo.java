package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Ciclista;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Equipo;

public class _02cCambiarDeEquipo {
    
    public static void main(String[] args) {
        
        Scanner str = new Scanner(System.in);
        Scanner num = new Scanner(System.in);

        System.out.println("Dime el dorsal del ciclista:");
        int dorsal = num.nextInt();
        System.out.println("Dime el nombre del nuevo Equipo:");
        String nomeq = str.nextLine();

        switch (cambiarEquipo(dorsal, nomeq)){
            case 0:
                System.out.println("Modificado correctamente.");
                break;
            case 1:
                System.out.println("Error, el ciclista no existe.");
                break;
            case 2:
                System.out.println("Error, el equipo no existe.");
                break;
            case 3:
                System.out.println("Error, el ciclista ya pertenece a ese equipo.");
        }

    }

    public static int cambiarEquipo(int dorsal, String nomeq){
        int err = 0;
        /*
        0->todo ok
        1->no existe el ciclista
        2->no existe el equipo
        3->el ciclista ya pertenece a ese equipo
        */
        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Ciclista");
            ArrayList<Ciclista> ciclistas = (ArrayList<Ciclista>) query.list();

            int a = 0, b = 0, c = 0;
            for (int i = 0; i < ciclistas.size(); i++) {
                if (ciclistas.get(i).getDorsal() == dorsal){
                    a++;
                }
                if ((ciclistas.get(i).getEquipo().getNomeq()).equals(nomeq)){
                    b++;
                }
            }

            if (a == 0){
                return 1;
            } else if (b == 0){
                return 2;
            }

            Ciclista ciclista = (Ciclista) session.get(Ciclista.class, dorsal);
            Equipo equipo = (Equipo) session.get(Equipo.class, nomeq);

            if (equipo.getNomeq().equals(ciclista.getEquipo().getNomeq())){
                return 3;
            }

            ciclista.setEquipo(equipo);

            session.save(ciclista);

            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }

        return err;
    }

}
