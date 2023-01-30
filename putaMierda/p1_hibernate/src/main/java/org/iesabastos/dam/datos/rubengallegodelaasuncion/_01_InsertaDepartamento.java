package org.iesabastos.dam.datos.rubengallegodelaasuncion;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.iesabastos.dam.datos.rubengallegodelaasuncion.Utils.HibernateUtil;

public class _01_InsertaDepartamento {

    public _01_InsertaDepartamento(int dept_NO, String dnombre, String loc) {
        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Departamento departamento = new Departamento();
            departamento.setDept_NO(dept_NO);
            departamento.setDnombre(dnombre);
            departamento.setLoc(loc);

            session.save(departamento);

            session.getTransaction().commit();
            session.close();
            System.out.println("Fin del programa");

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }
    }

    public static void main(String[] args) {
        
        new _01_InsertaDepartamento(42, "Damn Son", "You moms house");
        /*//INSER DESDE MAIN
        try {

            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            Departamento departamento = new Departamento();
            departamento.setDept_NO(69);
            departamento.setDnombre("ANDROID");
            departamento.setLoc("DESPA420");

            session.save(departamento);

            session.getTransaction().commit();
            session.close();
            System.out.println("Fin del programa");

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ha pasado algo chungo :(");
            // TODO: handle exception
        }*/

    }
    
}
