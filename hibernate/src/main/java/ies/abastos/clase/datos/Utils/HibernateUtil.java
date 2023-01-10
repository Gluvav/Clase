package ies.abastos.clase.datos.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.mysql.cj.Session;

// Indicar el nombre del paquete correctamente

/**
 * Clase que facilita el trabajo con Hibernate
 * 
 */
public class HibernateUtil {

  private static SessionFactory sessionFactory;
  private static Session session;

  /**
   * Crea la factoria de sesiones
   */
  public static void buildSessionFactory() {

    Configuration configuration = new Configuration();
    configuration.configure();
    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
        configuration.getProperties()).buildServiceRegistry();
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
  }

  /**
   * Abre una nueva sesión
   */
  public static void openSession() {

    session = (Session) sessionFactory.openSession();
  }

  /**
   * Devuelve la sesión actual
   * 
   * @return
   */
  public static Session getCurrentSession() {

    if ((session == null) || (!((org.hibernate.Session) session).isOpen()))
      openSession();

    return session;
  }

  /**
   * Cierra Hibernate
   */
  public static void closeSessionFactory() {

    if (session != null)
      ((SessionFactory) session).close();

    if (sessionFactory != null)
      sessionFactory.close();
  }
}