package org.iesabastos.dam.datos.rubengallegodelaasuncion.ejemploh.interfaz;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ejemploh.interfaz.*;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.ejemploh.Departamento;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;

public class _08_ConsultasHQL {
    
    public static void main(String[] args) {
        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Departamento");
            //Query query = session.createQuery("from Ciclista C WHERE C.nacimiento BETWEEN :min AND :max");
            //query.setParameter("min", "1979-01-01");
            //query.setParameter("max", "1980-12-31");
            ArrayList<Departamento> departamentos = (ArrayList<Departamento>) query.list();

            for (Departamento departamento : departamentos) {
                System.out.println(departamento.getDnombre() + ", " + departamento.getDept_NO() + 
                ", " + departamento.getLoc());
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
    }

}
