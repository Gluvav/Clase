package org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.interfaz;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ciclismo.Equipo;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar;

public class _04EliminarEquipoYCambiarCiclistas {

    public static void main(String[] args) {

        Scanner str = new Scanner(System.in);

        System.out.println("Introduce el nombre del equipo a eliminar:");
        String nomeq = str.nextLine();
        System.out.println("Introduce el nombre del equipo de destino de los ciclistas:");
        String nomeqDestino = str.nextLine();

        deleteChange(nomeq, nomeqDestino);

    }

    public static void deleteChange(String nomeq, String nomeqDestino) {

        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Equipo equipo = (Equipo) session.get(Equipo.class, nomeq);
            Equipo equipoDestino = (Equipo) session.get(Equipo.class, nomeqDestino);

            for (int i = 0; i < equipo.getCiclistas().size(); i++) {
                equipo.getCiclistas().get(i).setEquipo(equipoDestino);
                session.update(equipoDestino);
            }

            session.delete(equipo);

            session.getTransaction().commit();
            session.close();

            System.out.println("Equipo eliminado. y ciclistas traspasados.");

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }

    }

}
