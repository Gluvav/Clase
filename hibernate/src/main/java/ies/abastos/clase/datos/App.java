package ies.abastos.clase.datos;

import org.hibernate.Session;

import ies.abastos.clase.datos.Utils.HibernateUtil;

public class App {
    public static void main(String[] args) {

        fabricas f = new fabricas(1234, "PerroFlautas");

        HibernateUtil.buildSessionFactory();
        HibernateUtil.openSession();

        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();

        /* - */

        sesion.save(f);
        sesion.getTransaction().commit();
        sesion.close();
    }
    
}
